package game_engine;

public abstract class GameEntity implements IEntity, PhysicsEntity {

	public Transform transform = new Transform();
	public RigidBody rigidbody = new RigidBody();

	protected Vector3 prevPos;

	public GameEntity() {
		this(0f, 0f, 0f);
	}

	public GameEntity(float x, float y, float z) {
		transform = new Transform(new Vector3(x, y, z));
		prevPos = transform.position;
	}

	public void updatePhysics(double t, double dt) {
		if ( rigidbody != null ) {

			// Allows implementing class to do physics stuff
			fixedUpdate();
			prevPos = transform.position;
			transform.position = rigidbody.integratePositionFromVelocity(t, dt, transform.position);
		}
	}

	public void fixedUpdate() {
		// Unused. Call this in the class implementing GameEntity
	}
}
