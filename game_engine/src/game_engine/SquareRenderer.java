package game_engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class SquareRenderer extends Renderer {

	private Vector2 size; // The size of this SquareRenderer

	public SquareRenderer(Vector2 size) {
		this.className = "SquareRenderer";
		setSize(size);
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
