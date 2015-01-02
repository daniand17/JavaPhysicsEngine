package game_engine;

public interface IGameObject {
	
	public void start();
	
	public void onCollision(Collider other);
	
	public void physicsUpdate();
	
	public void update();
	
	public void addComponent(Component newComponent);

}
