package graphics;

import game_engine.Component;
import game_engine.GameObject;
import game_engine.Transform;
import game_engine.Vector2;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public abstract class Renderer extends Component {

	public enum Renderers {
		SQUARE_2D, ELLIPSE_2D
	}

	private Vector2 previous;
	private Vector2 current;
	protected Vector2 offset;
	protected Shape shape;

	public abstract Vector2 getSize();

	/**
	 * The render method is called by the class implementing this abstract
	 * class. Takes a Graphics2D object and a position in which to render the
	 * object.
	 */
	abstract void render(Graphics2D g2d, Vector2 renderPos);

	/**
	 * This method does the interpolation for the rendering of the object given
	 * an alpha value, and a graphics2D object. Calls the implementing class'
	 * render method to render the object.
	 */
	void renderObject(Graphics2D g2d, double alpha) {
		if ( previous != null && current != null ) {
			// Calculate the vector to render at by taking the current pos and
			// the previous pos
			Vector2 cRenderPos = new Vector2(current.x * alpha, current.y * alpha);
			Vector2 pRenderPos = new Vector2(previous.x * (1 - alpha), previous.y * (1 - alpha));
			// The interpolated vector to render at
			Vector2 interpPos = cRenderPos.add(pRenderPos);

			// Get the rotation instance
			AffineTransform rotateTransform = AffineTransform.getRotateInstance(getTransform()
					.getRotation(), interpPos.x, interpPos.y);
			AffineTransform translateTransform = AffineTransform.getTranslateInstance(interpPos.x
					- offset.x, interpPos.y - offset.y);

			Shape temp = translateTransform.createTransformedShape(shape);
			temp = rotateTransform.createTransformedShape(temp);
			// Render the object
			render(g2d, interpPos);
			g2d.draw(temp);
		}
	}

	/**
	 * This method is used to update the position of an object for interpolation
	 * between two points
	 * 
	 * @param prevPos
	 * @param position
	 */
	public void updateRendererPositions(Vector2 prevPos, Vector2 position) {
		// TODO fix so this isn't public
		previous = prevPos;
		current = position;
	}

	public static Renderer createRenderer(Renderers rendererType, GameObject attachedGO,
			Transform attachedTransform) {

		Vector2 size = new Vector2(64, 64);
		switch (rendererType) {
		case SQUARE_2D:
			SquareRenderer sqr = new SquareRenderer(size);
			sqr.transform = attachedTransform;
			sqr.setGameObject(attachedGO);
			return sqr;
		case ELLIPSE_2D:
			EllipseRenderer ell = new EllipseRenderer(size);
			ell.transform = attachedTransform;
			ell.setGameObject(attachedGO);
			return ell;
		}
		return null;
	}
}
