package components;

import game_engine.Physics;
import game_engine.Quaternion;
import game_engine.Vector3;

public class RigidBody {

	public Quaternion rotation; // TODO (Joe) implement this..not sure yet how
								// to do it in this class

	public Vector3 velocity; // The velocity of this rigidbody.
	public float mass; // The mass of this rigidbody
	public float drag; // The drag of this rigidbody (parasitic drag)
	public float gravityScale; // The scale of acceleration due to gravity

	/**
	 * The only public constructor. Creates a new velocity vector with no
	 * velocity, initializes the mass to 5 (kg), the drag to 0.001, and the
	 * gravity scale as defined in the Physics class.
	 */
	public RigidBody() {
		velocity = new Vector3();
		mass = 5f;
		drag = 0.001f;
		gravityScale = Physics.gravity;
	}

	/**
	 * This method implements Runge-Kutta 4 (RK4) numerical integration to find
	 * a new position in 3D space given a velocity, current time, and time
	 * interval since last physics update.
	 * 
	 * @param t
	 *            the current time
	 * @param dt
	 *            the interval of the integral to evaluate
	 * @param startPos
	 *            the starting position
	 * @return the new position after RK4 integration
	 */
	public Vector3 integratePositionFromVelocity(double t, double dt,
			Vector3 startPos) {

		// Uses RK4 (runge-kutta) integration to determine velocity.
		Vector3[] a = evaluate(t, dt, startPos, this.velocity);
		Vector3[] b = evaluate(t, dt * 0.5f, a[0], a[1]);
		Vector3[] c = evaluate(t, dt * 0.5f, b[0], b[1]);
		Vector3[] d = evaluate(t, dt, c[0], c[1]);

		// Evaluate the new position vector
		float dxdt = 1.0f / 6.0f * (a[0].x + 2.0f * (b[0].x + c[0].x) + d[0].x);
		float dydt = 1.0f / 6.0f * (a[0].y + 2.0f * (b[0].y + c[0].y) + d[0].y);
		float dzdt = 1.0f / 6.0f * (a[0].z + 2.0f * (b[0].z + c[0].z) + d[0].z);
		// Evaluate the new velocity vector
		float dvxdt = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
		float dvydt = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);
		float dvzdt = 1.0f / 6.0f * (a[1].z + 2.0f * (b[1].z + c[1].z) + d[1].z);

		// Set the new velocity of this rigidbody
		velocity = new Vector3(dvxdt, dvydt, dvzdt);
		// Return the new position to the caller method
		return new Vector3(dxdt, dydt, dzdt);
	}

	/**
	 * Integrates over the interval dt using velocity as the change in position
	 * 
	 * @param t
	 *            the time to start the integration
	 * @param dt
	 *            the time interval to integrate over
	 * @param pos
	 *            the current position
	 * @param vel
	 *            the velocity
	 * @return an array containing the position and new velocity of this
	 *         integration
	 */
	private Vector3[] evaluate(double t, double dt, Vector3 pos, Vector3 vel) {
		pos.x += vel.x * (float) dt;
		pos.y += vel.y * (float) dt;
		pos.z += vel.z * (float) dt;

		Vector3 newVel = universeForces(vel, dt);
		Vector3[] tmp = { pos, newVel };
		return tmp;
	}

	/**
	 * This method factors in environmental forces such as gravity and drag
	 * 
	 * @param vel
	 *            the current velocity
	 * @param dt
	 *            the time to integrate over
	 * @return the vector corresponding to the new velocity due to drag and
	 *         gravity
	 */
	public Vector3 universeForces(Vector3 vel, double dt) {
		Vector3 v = new Vector3(vel.x, vel.y, vel.z);
		Vector3 grav = new Vector3(Vector3.forward.x, Vector3.forward.y,
				Vector3.forward.z);

		// Add gravity to the current velocity vector
		grav.scale(gravityScale * (float) dt);
		v = v.add(grav);

		// Simple form of parasitic drag. Scales velocity some percentage
		v.x *= 1 - drag;
		v.y *= 1 - drag;
		v.z *= 1 - drag;
		return v;
	}
}
