package game_engine;

public abstract class GameEntity implements IEntity, PhysicsEntity {

	public Transform transform;
	public RigidBody rigidbody;

	public GameEntity() {
		transform = new Transform(new Vector3(0f, 0f, 0f));
	}

	public GameEntity(float x, float y, float z) {
		transform = new Transform(new Vector3(x, y, z));
	}

	public void updatePhysics(double dt) {
		if ( rigidbody != null ) {
			/*
			 * TODO this is where the update of movement occurs/is integrated. See the RK4
			 * integrator should work something like:
			 */
			transform.position.x += rigidbody.velocity.x * dt;
			transform.position.y += rigidbody.velocity.y * dt;
			transform.position.z += rigidbody.velocity.z * dt;

		}
		// Call fixedUpdate so the implementing class can do stuff in the physics update
		fixedUpdate();
	}

	public void fixedUpdate() {
		// Unused. Call this in the class implementing GameEntity
	}
}
