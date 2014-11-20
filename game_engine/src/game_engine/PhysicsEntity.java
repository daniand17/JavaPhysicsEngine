package game_engine;

public interface PhysicsEntity {

	void updatePhysics(double t, double dt);

	public void fixedUpdate();
}
