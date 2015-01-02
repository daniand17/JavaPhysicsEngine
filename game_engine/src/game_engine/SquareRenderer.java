package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class SquareRenderer extends Renderer {

	private Vector2 size; // The size of this SquareRenderer
	private Rectangle2D.Double shape;

	public SquareRenderer(Vector2 sizeDimensions) {
		// Calls the Renderer constructor
		super();
		setSize(sizeDimensions);

		// Create the shape that will be rendered
		shape = new Rectangle2D.Double();
		shape.width = sizeDimensions.x;
		shape.height = sizeDimensions.y;

		imageToRender = makeImageFromShape();
	}

	@Override
	void render(Graphics2D g2d, Vector2 renderPos) {
		AffineTransform aT = g2d.getTransform();

		// Get the shape
		shape.x = renderPos.x;
		shape.y = renderPos.y;

		// Get the rotation of the transform
		double rotation = this.getTransform().getRotation();

		affineTransform.rotate(rotation);
		affineTransform.translate(renderPos.x, renderPos.y);

		g2d.setColor(Color.BLUE);

		g2d.drawImage(imageToRender, affineTransform, null);
		g2d.draw(shape);

		g2d.setTransform(aT);

	}

	/**
	 * Returns the Vector2 representation of the size of this renderer
	 * 
	 * @return the size
	 */
	public Vector2 getSize() {
		return size;
	}

	/**
	 * Sets the size of this square renderer. Any dimensions that are less than
	 * 0 will be set to zero.
	 * 
	 * @param size
	 *            the size to set
	 */
	public void setSize(Vector2 size) {
		if ( size.x < 0 )
			size.x = 0;

		if ( size.y < 0 )
			size.y = 0;

		this.size = size;
	}

	@Override
	protected BufferedImage makeImageFromShape() {

		return null;
	}
}
