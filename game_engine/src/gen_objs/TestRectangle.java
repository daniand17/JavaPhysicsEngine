package gen_objs;

import game_engine.GameEntity;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestRectangle extends GameEntity {

	public TestRectangle() {
	}

	public TestRectangle(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g2d) {
		Color temp = g2d.getColor();
		g2d.setColor(Color.blue);
		g2d.fillRect((int) position.x, (int) position.y, 64, 64);
		g2d.setColor(temp);
	}
}
