package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class SquareRenderer extends Renderer {

	private Vector2 size; // The size of this SquareRenderer
	private Rectangle2D.Double shape;

	public SquareRenderer(Vector2 sizeDimensions) {
		setSize(sizeDimensions);
		this.className = "SquareRenderer";

		// Create the shape that will be rendered
		shape = new Rectangle2D.Double(-sizeDimensions.x * 0.5, -sizeDimensions.y * 0.5,
				sizeDimensions.x, sizeDimensions.y);
	}

	@Override
	void render(Graphics2D g2d, Vector2 renderPos) {
		// Translate the object to its new position
		g2d.translate(renderPos.x, renderPos.y);
		// Rotate the object
		double rotation = this.getTransform().getRotation();
		g2d.rotate(rotation);
		g2d.setColor(Color.BLUE);
		g2d.draw(shape);
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
}
