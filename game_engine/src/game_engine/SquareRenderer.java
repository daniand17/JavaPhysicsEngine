package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class SquareRenderer extends Renderer {

	private Vector2 size;

	public SquareRenderer(Vector2 sizeDimensions) {
		size = sizeDimensions;
	}

	@Override
	public void render(Graphics2D g2d, Vector2 renderPos) {
		g2d.setColor(Color.blue);
		g2d.drawRect((int) Math.round(renderPos.x), (int) Math.round(renderPos.y), (int) size.x,
				(int) size.y);

	}
}
