package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestCircle extends GameEntity {

	public TestCircle() {

		rigidbody.drag = 0.01f;

	}

	@Override
	public void render(Graphics2D g2d, double alpha) {
		Color temp = g2d.getColor();
		g2d.setColor(Color.BLACK);
		g2d.fillOval((int) transform.position.x, (int) transform.position.y, 64, 64);
		g2d.setColor(temp);
	}

}
