package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class SquareRenderer extends Renderer {

	private Vector2 size; // The size of this SquareRenderer

	public SquareRenderer(Vector2 sizeDimensions) {
		setSize(sizeDimensions);
	}

	@Override
	void render(Graphics2D g2d, Vector2 renderPos) {
		g2d.setColor(Color.blue);
		g2d.drawRect((int) Math.round(renderPos.x), (int) Math.round(renderPos.y),
				(int) getSize().x, (int) getSize().y);

	}

	/**
	 * Returns the Vector2 representation of the size of this renderer
	 * 
	 * @return the size
	 */
	private Vector2 getSize() {
		return size;
	}

	/**
	 * Sets the size of this square renderer. Any dimensions that are less than
	 * 0 will be set to zero.
	 * 
	 * @param size
	 *            the size to set
	 */
	private void setSize(Vector2 size) {
		if ( size.x < 0 )
			size.x = 0;

		if ( size.y < 0 )
			size.y = 0;

		this.size = size;
	}
}
