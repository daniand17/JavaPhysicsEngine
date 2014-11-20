package game_engine;

import java.awt.Graphics2D;

public interface IEntity {

	public Transform transform = new Transform();

	public void render(Graphics2D g2d, double alpha);

}
