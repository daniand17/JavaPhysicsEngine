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

	protected Vector2 offset;
	protected Shape shape;

	private Vector2 size;

	// Get the width and height dimensions of this renderer
	public abstract Vector2 getSize();

	// Set the width and height dimensions of this renderer
	public Vector2 setSize() {
		return size;
	}

	/**
	 * The render method is called by the class implementing this abstract
	 * class. Takes a Graphics2D object and a position in which to render the
	 * object.
	 */
	abstract void render(Graphics2D g2d, Vector2 renderPos);

	/**
	 * This method is to be used only when the rendering loop is run separately
	 * from the main game update logic loop
	 * 
	 * @param g2d
	 *            the graphics context to draw to
	 */
	void renderObject(Graphics2D g2d) {
		// The interpolated vector to render at
		Vector2 interpPos = getTransform().getPosition();

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

	public static Renderer createRenderer(Renderers rendererType, GameObject attachedGO,
			Transform attachedTransform) {

		// The default size
		Vector2 size = new Vector2(32, 32);
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
