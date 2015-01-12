package game_engine;

import java.awt.Graphics2D;

import physics.Collider;

public interface IGameObject {

	public void start();

	public void onCollision(Collider other);

	public void physicsUpdate();

	public void update();

	public void onGUI(Graphics2D g2d);

}
