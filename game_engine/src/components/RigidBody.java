package components;

import game_engine.Physics;
import game_engine.Quaternion;
import game_engine.Vector2;

public class RigidBody {

	public Quaternion rotation; // TODO (Joe) implement this..not sure yet how
								// to do it in this class

	public Vector2 velocity; // The velocity of this rigidbody.
	public float mass; // The mass of this rigidbody
	public float drag; // The drag of this rigidbody (parasitic drag)
	public float gravityScale; // The scale of acceleration due to gravity

	/**
	 * The only public constructor. Creates a new velocity vector with no
	 * velocity, initializes the mass to 5 (kg), the drag to 0.001, and the
	 * gravity scale as defined in the Physics class.
	 */
	public RigidBody() {
		velocity = new Vector2();
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
	public Vector2 integratePositionFromVelocity(double t, double dt, Vector2 startPos) {

		// Uses RK4 (runge-kutta) integration to determine velocity.
		Vector2[] a = evaluate(t, dt, startPos, this.velocity);
		Vector2[] b = evaluate(t, dt * 0.5f, a[0], a[1]);
		Vector2[] c = evaluate(t, dt * 0.5f, b[0], b[1]);
		Vector2[] d = evaluate(t, dt, c[0], c[1]);

		// Evaluate the new position vector
		double dxdt = 1.0f / 6.0f * (a[0].x + 2.0f * (b[0].x + c[0].x) + d[0].x);
		double dydt = 1.0f / 6.0f * (a[0].y + 2.0f * (b[0].y + c[0].y) + d[0].y);

		// Evaluate the new velocity vector
		double dvxdt = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
		double dvydt = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);

		// Set the new velocity of this rigidbody
		velocity = new Vector2(dvxdt, dvydt);
		// Return the new position to the caller method
		return new Vector2(dxdt, dydt);
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
	private Vector2[] evaluate(double t, double dt, Vector2 pos, Vector2 vel) {
		pos.x += vel.x * (float) dt;
		pos.y += vel.y * (float) dt;

		Vector2 newVel = universeForces(vel, dt);
		Vector2[] tmp = { pos, newVel };
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
	public Vector2 universeForces(Vector2 vel, double dt) {
		Vector2 v = new Vector2(vel.x, vel.y);
		Vector2 grav = new Vector2(Vector2.forward.x, Vector2.forward.y);

		// Add gravity to the current velocity vector
		grav.scale(gravityScale * (float) dt);
		v = v.add(grav);

		// Simple form of parasitic drag. Scales velocity some percentage
		v.x *= 1 - drag;
		v.y *= 1 - drag;
		return v;
	}
}
