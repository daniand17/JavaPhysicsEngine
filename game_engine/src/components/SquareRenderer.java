package components;

import game_engine.Vector3;

import java.awt.Color;
import java.awt.Graphics2D;

import abstracts.Renderer;

public class SquareRenderer extends Renderer {

	@Override
	public void render(Graphics2D g2d, Vector3 renderPos) {
		g2d.setColor(Color.blue);
		g2d.drawRect((int) Math.round(renderPos.x), (int) Math.round(renderPos.y), 64, 64);
	}
}
