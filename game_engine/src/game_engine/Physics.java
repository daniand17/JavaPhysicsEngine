package game_engine;

public class Physics {

	public static double gravity = 9.81f;
	public static Vector2 gravityVector = new Vector2(0, gravity);

	/**
	 * This method is called by a rigidbody to update its position given a time,
	 * timestep, current position, and velocity
	 * 
	 * @param t
	 *            the current time
	 * @param dt
	 *            the timestep to integrate over
	 * @param position
	 *            the starting position
	 * @param velocity
	 *            the velocity of the object to integrate
	 * @return the new position of the object after this timestep
	 */
	public static Vector2 integratePositionFromVelocity(double t, double dt, Vector2 position,
			Vector2 velocity) {
		// TODO (Joe) Call the specific integration method you want to use here
		return rk4Integration(t, dt, position, velocity);
	}

	private static Vector2 rk4Integration(double t, double dt, Vector2 position, Vector2 velocity) {
		// Uses RK4 (runge-kutta) integration to determine velocity.
		Vector2[] a = evaluateRK4Derivative(t, dt, position, velocity);
		Vector2[] b = evaluateRK4Derivative(t, dt * 0.5f, a[0], a[1]);
		Vector2[] c = evaluateRK4Derivative(t, dt * 0.5f, b[0], b[1]);
		Vector2[] d = evaluateRK4Derivative(t, dt, c[0], c[1]);

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
	private static Vector2[] evaluateRK4Derivative(double t, double dt, Vector2 pos, Vector2 vel) {
		pos.x += vel.x * (float) dt;
		pos.y += vel.y * (float) dt;

		Vector2 newVel = addGravityToVelocity(vel, dt);
		Vector2[] tmp = { pos, newVel };
		return tmp;
	}

	/**
	 * This method factors in gravity and eventually drag
	 * 
	 * @param vel
	 *            the current velocity
	 * @param dt
	 *            the time to integrate over
	 * @return the vector corresponding to the new velocity due to drag and
	 *         gravity
	 */
	public static Vector2 addGravityToVelocity(Vector2 vel, double dt) {
		// The gravity vector scaled to the amt of accel this timestep
		// FIXME not sure if this is right
		Vector2 gravityThisStep = gravityVector.scale(dt);
		return vel.add(gravityThisStep);
	}
}
