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
		return Physics.integratePositionFromVelocity(t, dt, startPos, velocity);

	}
}
