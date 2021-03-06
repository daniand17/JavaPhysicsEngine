package graphics;

import game_engine.Vector2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * This class represents a rendered square in game, and implements the Renderer
 * abstract class
 * 
 * @author andrew
 *
 */
public class SquareRenderer extends Renderer {

	private Vector2 size; // The size of this SquareRenderer

	public SquareRenderer(Vector2 size) {
		this.name = "SquareRenderer";
		setSize(size);
		// Sets the offset of the square to be half the dimensions, to adjust
		// for how Java renders squares as the top left corner, and where we'd
		// like it to be in game, namely the transform should be the center of
		// the square to simplify physics
		offset = new Vector2(size.x * 0.5, size.y * 0.5);

		// Create the shape that will be rendered
		shape = new Rectangle2D.Double(0, 0, size.x, size.y);
	}

	/**
	 * Returns the Vector2 representation of the size of this renderer
	 * 
	 * @return the size
	 */
	@Override
	public Vector2 getSize() {
		return new Vector2(size.x, size.y);
	}

	@Override
	void render(Graphics2D g2d, Vector2 renderPos) {
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(BasicStroke.CAP_SQUARE));
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
