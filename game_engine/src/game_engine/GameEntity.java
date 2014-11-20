package game_engine;

public abstract class GameEntity implements PhysicsEntity {

	public Transform transform = new Transform();
	public RigidBody rigidbody = new RigidBody();
	public Renderer renderer;

	public GameEntity() {
		this(0f, 0f, 0f);
	}

	public GameEntity(float x, float y, float z) {
		transform = new Transform(new Vector3(x, y, z));
	}

	public void updatePhysics(double t, double dt) {
		if ( rigidbody != null ) {
			// Allows implementing class to do physics stuff
			fixedUpdate();
			Vector3 prevPos = transform.position;
			transform.position = rigidbody.integratePositionFromVelocity(t, dt, transform.position);
			// Updates the positions that were just calculated so the renderer can interpolate
			renderer.updateRendererPositions(prevPos, transform.position);
		}
	}

	public void fixedUpdate() {
		// Unused. Call this in the class implementing GameEntity
	}
}
