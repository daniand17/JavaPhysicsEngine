package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class SquareRenderer extends Renderer {

	@Override
	public void render(Graphics2D g2d, Vector3 renderPos) {
		g2d.setColor(Color.blue);
		g2d.drawRect((int) Math.round(renderPos.x), (int) Math.round(renderPos.y), 64, 64);
	}
}
