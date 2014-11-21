package game_engine;

public abstract class GameEntity implements PhysicsEntity, CanUpdate {

	public Transform transform = new Transform();
	public RigidBody rigidbody;
	public Renderer renderer;

	public GameEntity() {

	}

	public void updatePhysics(double t, double dt) {
		if ( rigidbody != null ) {
			// Allows implementing class to do physics stuff
			fixedUpdate();
			Vector3 prevPos = transform.position;
			transform.position = rigidbody.integratePositionFromVelocity(t, dt, transform.position);
			// Updates the positions that were just calculated so the renderer can interpolate
			if ( renderer != null )
				renderer.updateRendererPositions(prevPos, transform.position);
		}
	}

	public void fixedUpdate() {
		// Unused. Call this in the class implementing GameEntity
	}

	public void update() {

	}

	public void start() {

	}

}
