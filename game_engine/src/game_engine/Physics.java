package game_engine;
import components.RigidBody;
public class Physics {

	private static double gravity = 9.81d;
	private static Vector2 gravityVector = new Vector2(0d, gravity);
	private RigidBody RB;
	private double dt;
	
	/**
	 * This method is called by a RigidBody to update its position given a time,
	 * timestep, current position, and velocity
	 * 
	 * @param dt
	 *          the timestep to integrate over
	 * @param rigidbody
	 * 			rigidbody associated with this physics object, contains mass, drag, position, and 
	 * 			velocity information
	 */
	public Physics(double dt, RigidBody rigidbody){
		this.dt = dt;
		this.RB = rigidbody;
		integrateRB();
	}
	
	private void integrateRB() {
		// This method is just a wrapper for different integration techniques. Later, we may 
		// want to switch between methods on-the-fly based on some measure of error in the
		// integration.
		rk4Integration();
		//return RB;
	}

	/**
	 * This function evaluates the new position over a timestep from a velocity,
	 * starting position, and a start time.
	 * 
	 * @param t
	 *            the start time
	 * @param dt
	 *            the time to integrate over
	 * @param position
	 *            the starting position
	 * @param velocity
	 *            the velocity of the rigidbody
	 * @return the new position after integration
	 */
	private void rk4Integration() {
		// Uses the classical Runge-Kutta method (RK4) to update velocity and position.
		Vector2[] a = evaluatePosition(0d, RB.position, RB.velocity);
		Vector2[] b = evaluatePosition(0.5d, a[0], a[1]);
		Vector2[] c = evaluatePosition(0.5d, b[0], b[1]);
		Vector2[] d = evaluatePosition(1d, c[0], c[1]);

		// Evaluate the new position vector
		RB.position.x = 1.0d / 6.0d * (a[0].x + 2.0d * (b[0].x + c[0].x) + d[0].x);
		RB.position.y = 1.0d / 6.0d * (a[0].y + 2.0d * (b[0].y + c[0].y) + d[0].y);

		// Evaluate the new velocity vector
		RB.velocity.x = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
		RB.velocity.y = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);
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
	private Vector2[] evaluatePosition(double factor, Vector2 pos, Vector2 vel) {
		
		// Method 1 //
		// This feels slower and is probably tougher to read, but is the "better"
		// way of doing things with respect to OOD guidelines. 
		/*
		pos = pos.add(vel.scale(dt*factor));
		
		Vector2 acc = vel.scale(-RB.zeta/RB.mass); 
		acc = acc.add(RB.force.scale(1/RB.mass));
		acc = acc.add(gravityVector); // Add in gravity vector
		vel = vel.add(acc.scale(dt*factor));
		*/
		// Method 2 //
		// This feels faster and is more concise, but reflects "poor" OOD 
		// principles (we shouldn't be accessing these fields directly)
		
		pos.x += vel.x*dt*factor;
		pos.y += vel.y*dt*factor;
		vel.x += dt*factor*((RB.force.x - RB.zeta*vel.x)/RB.mass + gravityVector.x);
		vel.y += dt*factor*((RB.force.y - RB.zeta*vel.y)/RB.mass + gravityVector.y);
		
		Vector2[] retVals  = {pos, vel};
		return retVals;
	}


}
