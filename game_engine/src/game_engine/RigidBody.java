package game_engine;

public class RigidBody {
	public Quaternion rotation;

	public Vector3 velocity;
	public float mass = 5f;
	public float drag = .001f;
	public float gravityScale = 9.81f;

	public RigidBody() {

		velocity = new Vector3();
	}

	public Vector3 integratePositionFromVelocity(double t, double dt, Vector3 startPos) {

		// Uses RK4 (runge-kutta) integration to determine velocity.
		Vector3[] a = evaluate(t, dt, startPos, this.velocity);
		Vector3[] b = evaluate(t, dt * 0.5f, a[0], a[1]);
		Vector3[] c = evaluate(t, dt * 0.5f, b[0], b[1]);
		Vector3[] d = evaluate(t, dt, c[0], c[1]);

		float dxdt = 1.0f / 6.0f * (a[0].x + 2.0f * (b[0].x + c[0].x) + d[0].x);
		float dydt = 1.0f / 6.0f * (a[0].y + 2.0f * (b[0].y + c[0].y) + d[0].y);
		float dzdt = 1.0f / 6.0f * (a[0].z + 2.0f * (b[0].z + c[0].z) + d[0].z);

		float dvxdt = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
		float dvydt = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);
		float dvzdt = 1.0f / 6.0f * (a[1].z + 2.0f * (b[1].z + c[1].z) + d[1].z);

		velocity = new Vector3(dvxdt, dvydt, dvzdt);
		return new Vector3(dxdt, dydt, dzdt);
	}

	private Vector3[] evaluate(double t, double dt, Vector3 pos, Vector3 vel) {
		pos.x += vel.x * (float) dt;
		pos.y += vel.y * (float) dt;
		pos.z += vel.z * (float) dt;

		Vector3 newVel = universeForces(vel, dt);
		Vector3[] tmp = { pos, newVel };
		return tmp;

	}

	public Vector3 universeForces(Vector3 vel, double dt) {
		Vector3 v = new Vector3(vel.x, vel.y, vel.z);
		Vector3 grav = new Vector3(Vector3.forward.x, Vector3.forward.y, Vector3.forward.z);

		grav.scale(gravityScale * (float) dt);
		v = v.add(grav);

		// Simple form of parasitic drag. Scales velocity some percentage
		v.x *= 1 - drag;
		v.y *= 1 - drag;
		v.z *= 1 - drag;
		return v;
	}
}
