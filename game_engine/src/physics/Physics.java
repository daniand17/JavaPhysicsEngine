package physics;

import game_engine.Debug;
import game_engine.GameObject;
import game_engine.Vector2;

import java.awt.geom.Area;

import objects.TestRect;

public class Physics {

	private static final double GRAVITY = 9.81d;
	private static Vector2 gravityVector = new Vector2(0d, GRAVITY);
	private static double dt = 0.01;
	private static double b1 = 35d / 384d, b2 = 0d, b3 = 500d / 1113d, b4 = 125d / 192d,
			b5 = -2187d / 684d, b6 = 11d / 84d;
	private static double c2 = 1d / 5d, c3 = 3d / 10d, c4 = 4d / 5d, c5 = 8d / 9d, c6 = 1d,
			c7 = 1d;
	private static double a21 = 1d / 5d, a31 = 3d / 40d, a32 = 9d / 40d, a41 = 44d / 45d,
			a42 = -56d / 15d, a43 = 32d / 9d, a51 = 19372d / 6561d, a52 = -25360d / 2187d,
			a53 = 64448d / 6561d, a54 = -212d / 729d, a61 = 9017d / 3168d, a62 = -355d / 33d,
			a63 = 46732d / 5247d, a64 = 49d / 176d, a65 = -5103d / 18656d;
	private static double b1s = 5179d / 57600d, b2s = 0, b3s = 7571d / 16695d, b4s = 393d / 640d,
			b5s = -92097d / 339200d, b6s = 187d / 2100d;
	// The name of this class
	public static final String NAME = "Physics";

	// being passed to the integrateState() method, because I am also using it
	// in the
	// resolveCollision() method.

	/**
	 * This method is called by a RigidBody to update its position given a time,
	 * timestep, current position, and velocity
	 * 
	 * @param dt
	 *            the timestep to integrate over
	 * @param rigidbody
	 *            rigidbody associated with this physics object, contains mass,
	 *            drag, position, and velocity information
	 */

	public static Vector2[] integrateState(double t, double dt, Vector2 position, double theta,
			Rigidbody2D rigidbody) {
		Physics.dt = dt;
		return rk4Integration(t, dt, position, theta, rigidbody);
	}

	/**
	 * Classical 4th-order Runge-Kutta method. Operates on a fixed timestep dt
	 * and the position/velocity state and parameters of the associated
	 * rigidbody.
	 * 
	 */

	private static Vector2[] rk4Integration(double t, double dt, Vector2 position, double theta,
			Rigidbody2D rigidbody) {
		Vector2 rot = new Vector2(theta, rigidbody.angularSpeed);
		Vector2[] a = evaluatePosition(0d, position, rigidbody.velocity, rot, rigidbody);
		Vector2[] b = evaluatePosition(0.5d, a[0], a[1], a[2], rigidbody);
		Vector2[] c = evaluatePosition(0.5d, b[0], b[1], b[2], rigidbody);
		Vector2[] d = evaluatePosition(1d, c[0], c[1], c[2], rigidbody);

		// Evaluate the new position vector
		position.x = 1.0d / 6.0d * (a[0].x + 2.0d * (b[0].x + c[0].x) + d[0].x);
		position.y = 1.0d / 6.0d * (a[0].y + 2.0d * (b[0].y + c[0].y) + d[0].y);

		// Evaluate the new velocity vector
		rigidbody.velocity.x = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
		rigidbody.velocity.y = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);

		// Evaluate the new rotation vector
		rot.x = 1.0d / 6.0d * (a[2].x + 2.0d * (b[2].x + c[2].x) + d[2].x);
		rigidbody.angularSpeed = 1.0d / 6.0d * (a[2].y + 2.0d * (b[2].y + c[2].y) + d[2].y);

		// The x field of rot contains the updated rotation
		Vector2[] retVals = { position, rot };

		return retVals;
	}

	/**
	 * Dormand-Prince 4th/5th order embedded Runge-Kutta method. Selected
	 * coefficients minimize error in the 5th order evaluation. Operates on a
	 * fixed timestep dt and the position/velocity state and parameters of the
	 * associated rigidbody.
	 * 
	 * In the current "debug" iteration, the estimated error is printed to
	 * console.
	 * 
	 */
	@SuppressWarnings("unused")
	private void DOPRI5(Rigidbody2D RB) {
		// TODO: * Put these constants somewhere they don't get defined at each
		// integration call

		// Vector2[] k1 = evaluatePosition(0d, RB.transform.position,
		// RB.velocity);
		// Vector2[] k2 = evaluatePosition(c2, RB.transform.position +
		// k1[0].scale(a21), RB.velocity
		// + k1[1].scale(a21));
		// Vector2[] k3 = evaluatePosition(c3, RB.transform.position + a31 *
		// k1[0] + a32 * k2[0],
		// RB.velocity + a21 * k1[1] + a32 * k2[1]);
		// Vector2[] k4 = evaluatePosition(c4, RB.transform.position + a41 *
		// k1[0] + a42 * k2[0] + a43
		// * k3[0], RB.velocity + a41 * k1[1] + a42 * k2[1] + a43 * k3[1]);
		// Vector2[] k5 = evaluatePosition(c5, RB.transform.position + a51 *
		// k1[0] + a52 * k2[0] + a53
		// * k3[0] + a54 * k4[0], RB.velocity + a51 * k1[1] + a52 * k2[1] + a53
		// * k3[1] + a54
		// * k4[1]);
		// Vector2[] k6 = evaluatePosition(c6, RB.transform.position + a61 *
		// k1[0] + a62 * k2[0] + a63
		// * k3[0] + a64 * k4[0] + a65 * k5[0], RB.velocity + a61 * k1[1] + a62
		// * k2[1] + a63
		// * k3[1] + a64 * k4[1] + a65 * k5[0]);

		// Evaluate the new position vector
		// RB.position.x += RB.position.y +=

		// Evaluate the new velocity vector
		// RB.velocity.x += RB.velocity.y +=
	}

	/**
	 * 
	 * @param factor
	 *            The factor on the timestep dt required by the integration
	 *            methd in use
	 * @param pos
	 *            Position to evaluate at
	 * @param vel
	 *            Velocity to evaulate at
	 * @param rot
	 *            Vector of angular position (x) and velocity (y) in radians
	 * 
	 * @return retVals a Vector2 array containing the position, velocity, and
	 *         rotation from each evaluation
	 */
	private static Vector2[] evaluatePosition(double factor, Vector2 pos, Vector2 vel, Vector2 rot,
			Rigidbody2D rigidbody) {

		// Position updates
		pos.x += vel.x * dt * factor;
		pos.y += vel.y * dt * factor;
		rot.x += rot.y * dt * factor;

		// Velocity updates
		vel.x += dt
				* factor
				* ((rigidbody.force.x - rigidbody.getDrag() * vel.x) / rigidbody.getMass() + gravityVector.x
						* rigidbody.gravityScale);
		vel.y += dt
				* factor
				* ((rigidbody.force.y - rigidbody.getDrag() * vel.y) / rigidbody.getMass() + gravityVector.y
						* rigidbody.gravityScale);
		rot.y += dt * factor * (rigidbody.torque - rigidbody.getAngularDrag() * rot.y)
				/ rigidbody.getInertia();

		Vector2[] retVals = { pos, vel, rot };
		return retVals;
	}

	/**
	 * Package-access method used to determine whether two objects have collided
	 * with each other this frame.
	 * 
	 * @param col1
	 *            the first collider
	 * @param col2
	 *            the second collider
	 * @return boolean representing whether these objects have collided
	 */
	static boolean collided(Collider col1, Collider col2) {

		// Gets the areas of the shapes defining the colliders
		Area col1_area = col1.getBoundedArea();
		Area col2_area = col2.getBoundedArea();
		// This turns col1_area into area of interesection
		col1_area.intersect(col2_area);
		// Not sure how accurate of a solution this is in finding the impact
		// point

		// If the intersection area is empty, then colliders are not touching
		if ( !col1_area.isEmpty() )
			return true;
		else
			return false;
	}

	/**
	 * This is a package-access method used to resolve a collision that has
	 * occurred between two colliders. This method adjusts the rigidbody
	 * velocity, adds an impulse force, spin, etc. It also calls the
	 * onCollision() methods for the gameObjects involved in the collision if
	 * the game designer chooses to perform certain high-level behaviors or
	 * actions based on this collision.
	 * 
	 * @param col1
	 * @param col2
	 */
	public static void resolveCollision(Collider col1, Collider col2) {

		double e = 0.85; // Basic default value, this should later depend
		// on the RB's in collision

		// Get the areas of the shapes defining the colliders
		Area col1_area = col1.getBoundedArea();
		Area col2_area = col2.getBoundedArea();

		// Turn col1_area into area of intersection between the two objects
		col1_area.intersect(col2_area);

		// Collision check 1: If the intersection area is empty, no collision
		// takes place
		if ( col1_area.isEmpty() ) {
			return;
		}

		// Collision check 2: If the area is intersected but the bodies are
		// headed "away"
		// from each other, no collision takes place. Otherwise, they will stick
		// together or worse.
		Rigidbody2D col1_rb = col1.getGameObject().getRigidbody();
		Rigidbody2D col2_rb = col2.getGameObject().getRigidbody();

		Vector2 v1pre = col1_rb.velocity;
		Vector2 v2pre = col2_rb.velocity;
		Vector2 r1 = col1.positionInWorldSpace();
		Vector2 r2 = col2.positionInWorldSpace();

		// Determine the line of contact using the relative position between the
		// two objects
		Vector2 rho = r2.sub(r1); // Line of contact

		// These are the non-contact conditions:
		// 1) The velocity vectors are headed away from each other
		boolean nonContactCondition1 = (v1pre.dot(rho) < 0) && (v2pre.dot(rho) > 0);
		// 2) The velocity vectors are in the same direction, but the one in the
		// lead is moving away
		// more quickly than the other
		boolean nonContactCondition2 = (Math.signum(v1pre.dot(rho)) == Math.signum(v2pre.dot(rho)))
				&& (v1pre.dot(rho) < v2pre.dot(rho));

		// If either condition is true, return
		if ( nonContactCondition1 || nonContactCondition2 ) {
			return;
		}

		// Now we've verified contact, so call the onCollission() methods of
		// each object
		col1.getGameObject().onCollision(col2);
		col2.getGameObject().onCollision(col1);

		// Obtain other useful quantities

		Vector2 impactPoint = new Vector2(col1_area.getBounds2D().getCenterX(), col1_area
				.getBounds2D().getCenterY());

		// Call the appropriate math-handling method (this allows more
		// flexibility if we add
		// different collision types).
		rigidBodyCollision(col1_rb, col2_rb, r1, r2, rho, v1pre, v2pre, impactPoint);
	}

	private static void rigidBodyCollision(Rigidbody2D col1_rb, Rigidbody2D col2_rb, Vector2 r1,
			Vector2 r2, Vector2 rho, Vector2 v1pre, Vector2 v2pre, Vector2 impactPoint) {

		double e = 0.85d;
		// 1) Transform the velocities to a coordinate system with the X-axis
		// aligned on the LOC
		double theta = rho.angle(); // Angle from x-axis to line of contact
		v1pre = v1pre.rotate(theta);
		v2pre = v2pre.rotate(theta);

		// 2) Conserve momentum and energy to obtain the post-impact velocities.
		// The y-direction velocities are not modified. The x-direction
		// velocities must
		// be determined by solving a system of 2 equations to conserve momentum
		// and restitution
		double gamma = 1 + e;
		double lambda = col2_rb.getMass() / col1_rb.getMass();
		double v2x = (gamma * v1pre.x + v2pre.x * (lambda - e)) / (1 + lambda);
		double v1x = v2x + e * (v2pre.x - v1pre.x);

		Vector2 v1post = new Vector2(v1x, v1pre.y).rotate(-theta);
		Vector2 v2post = new Vector2(v2x, v2pre.y).rotate(-theta);

		col1_rb.velocity = v1post;
		col2_rb.velocity = v2post;

		// 4) Use the velocities and the prior angular velocity to compute the
		// new angular velocity
		Debug.log(NAME, "Object 1 Position: " + r1);
		Debug.log(NAME, "Object 2 Position: " + r2);
		Debug.log(NAME, "Midpoint between 2 objects: " + r1.add(rho.scale(0.5d)));
		Debug.log(NAME, "Reported impact Point: " + impactPoint);
		v1pre = v1pre.rotate(-theta);
		v2pre = v2pre.rotate(-theta); // Need these back
		// System.out.println(rho + "," + v1pre + ", " + v1post + ", " +
		// col1_rb.getMass() / (2d*col1_rb.getInertia())
		// *rho.cross(v1pre.sub(v1post)));
		Vector2 rho1 = r1.sub(impactPoint);
		Vector2 rho2 = r2.sub(impactPoint);
		col1_rb.angularSpeed += col1_rb.getMass() / (2d * col1_rb.getInertia())
				* (rho1.cross(v1pre.sub(v1post)));
		col2_rb.angularSpeed += col2_rb.getMass() / (2d * col2_rb.getInertia())
				* (rho2.cross(v2pre.sub(v2post)));
		// System.out.println(col1_rb.angularSpeed);
	}

	static void resolveGravity(GameObject obj1, GameObject obj2) {
		// TODO this method will behave similar to resolveCollision, calculating
		// the gravitational
		// force from one object to another. I'm not sure what type of object it
		// will accept, however.
	}

}
