package game_engine;

import java.awt.Graphics2D;

public interface IEntity {

	public Transform transform = null;

	public void fixedUpdate();

	public void draw(Graphics2D g2d);

}
