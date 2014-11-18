package gen_objs;

import game_engine.GameEntity;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestCircle extends GameEntity {

	@Override
	public void draw(Graphics2D g2d) {
		Color temp = g2d.getColor();
		g2d.setColor(Color.BLACK);
		g2d.fillOval((int) position.x, (int) position.y, 64, 64);
		g2d.setColor(temp);
	}

}
