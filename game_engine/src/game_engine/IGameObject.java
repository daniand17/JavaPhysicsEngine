package game_engine;

import physics.Collider;


public interface IGameObject {
	
	public void start();
	
	public void onCollision(Collider other);
	
	public void physicsUpdate();
	
	public void update();

}
