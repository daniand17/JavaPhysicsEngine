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

	public void updatePhysics(double t, double dt) {
		if (rigidbody != null) {
			// Allows implementing class to do physics stuff
			fixedUpdate();
			// Uses RK4 (runge-kutta) integration to determine velocity.
			Vector3[] a = evaluate((float) t, (float) dt, transform.position,
					rigidbody.velocity);
			Vector3[] b = evaluate((float) t, (float) dt * 0.5f, a[0], a[1]);
			Vector3[] c = evaluate((float) t, (float) dt * 0.5f, b[0], b[1]);
			Vector3[] d = evaluate((float) t, (float) dt, c[0], c[1]);

			float dxdt = 1.0f / 6.0f * (a[0].x + 2.0f * (b[0].x + c[0].x) + d[0].x);
			float dydt = 1.0f / 6.0f * (a[0].y + 2.0f * (b[0].y + c[0].y) + d[0].y);
			float dzdt = 1.0f / 6.0f * (a[0].z + 2.0f * (b[0].z + c[0].z) + d[0].z);

			float dvxdt = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
			float dvydt = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);
			float dvzdt = 1.0f / 6.0f * (a[1].z + 2.0f * (b[1].z + c[1].z) + d[1].z);

			transform.position = new Vector3(dxdt, dydt, dzdt);
			rigidbody.velocity = new Vector3(dvxdt, dvydt, dvzdt);
		}
	}

	public void fixedUpdate() {
		// Unused. Call this in the class implementing GameEntity
	}

	private Vector3[] evaluate(float t, float dt, Vector3 pos, Vector3 vel) {
		pos.x = pos.x + vel.x * dt;
		pos.y = pos.y + vel.y * dt;
		pos.z = pos.z + vel.z * dt;

		Vector3[] tmp = { pos, vel };
		return tmp;

	}
}
