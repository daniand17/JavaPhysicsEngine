package graphics;

import game_engine.Component;
import game_engine.GameObject;
import game_engine.Transform;
import game_engine.Vector2;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * This abstract class defines what a renderer is and any methods which require
 * implementation in the inheriting classes. Contains also some back-end code
 * that is general to all renderers such as translation and rotation.
 * 
 * @author andrew
 *
 */
public abstract class Renderer extends Component {

	// Enum containing the possible renderers available in this engine.
	public enum Renderers {
		SQUARE_2D, ELLIPSE_2D
	}

	protected Vector2 offset; // The offset of the renderer to account for
								// transforms being at different locations on
								// the object than the coordinate system of
								// where the object is rendered at
	protected Shape shape; // The shape of this renderer

	private Vector2 size; // The dimensions of this renderer

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

	/**
	 * This method creates and returns a renderer to be shown on screen.
	 * 
	 * @param rendererType
	 *            the type of renderer to create
	 * @param attachedGO
	 *            the game object to attach the renderer to
	 * @param attachedTransform
	 *            the transform to atttach the renderer to
	 * @return the generated renderer with all references set
	 */
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
