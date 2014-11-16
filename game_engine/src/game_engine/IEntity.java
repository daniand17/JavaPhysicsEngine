package game_engine;

import java.awt.Graphics2D;

public interface IEntity {

	public Vector2 getPosition();

	public void setPosition(Vector2 vec);

	public void update();

	public void draw(Graphics2D g2d);

}
