package game_engine;

public class Physics {

	private static double gravity = 9.81d;
	private static Vector2 gravityVector = new Vector2(0d, gravity);
	private static double dt = 0.1;

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

	static Vector2[] integrateState(double t, double dt, Vector2 position, Rigidbody2D rigidbody) {
		Physics.dt = dt;
		return rk4Integration(t, dt, position, rigidbody);
	}

	/**
	 * Classical 4th-order Runge-Kutta method. Operates on a fixed timestep dt
	 * and the position/velocity state and parameters of the associated
	 * rigidbody.
	 * 
	 */

	private static Vector2[] rk4Integration(double t, double dt, Vector2 position,
			Rigidbody2D rigidbody) {

		Vector2[] a = evaluatePosition(0d, position, rigidbody.velocity, rigidbody);
		Vector2[] b = evaluatePosition(0.5d, a[0], a[1], rigidbody);
		Vector2[] c = evaluatePosition(0.5d, b[0], b[1], rigidbody);
		Vector2[] d = evaluatePosition(1d, c[0], c[1], rigidbody);

		// Evaluate the new position vector
		position.x = 1.0d / 6.0d * (a[0].x + 2.0d * (b[0].x + c[0].x) + d[0].x);
		position.y = 1.0d / 6.0d * (a[0].y + 2.0d * (b[0].y + c[0].y) + d[0].y);

		// Evaluate the new velocity vector
		rigidbody.velocity.x = 1.0f / 6.0f * (a[1].x + 2.0f * (b[1].x + c[1].x) + d[1].x);
		rigidbody.velocity.y = 1.0f / 6.0f * (a[1].y + 2.0f * (b[1].y + c[1].y) + d[1].y);

		Vector2[] retVal = { position, rigidbody.velocity };
		return retVal;
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

		double b1 = 35d / 384d, b2 = 0d, b3 = 500d / 1113d, b4 = 125d / 192d, b5 = -2187d / 684d, b6 = 11d / 84d;
		double c2 = 1d / 5d, c3 = 3d / 10d, c4 = 4d / 5d, c5 = 8d / 9d, c6 = 1d, c7 = 1d;
		double a21 = 1d / 5d, a31 = 3d / 40d, a32 = 9d / 40d, a41 = 44d / 45d, a42 = -56d / 15d, a43 = 32d / 9d, a51 = 19372d / 6561d, a52 = -25360d / 2187d, a53 = 64448d / 6561d, a54 = -212d / 729d, a61 = 9017d / 3168d, a62 = -355d / 33d, a63 = 46732d / 5247d, a64 = 49d / 176d, a65 = -5103d / 18656d;
		double b1s = 5179d / 57600d, b2s = 0, b3s = 7571d / 16695d, b4s = 393d / 640d, b5s = -92097d / 339200d, b6s = 187d / 2100d;
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
	 * @return retVals a Vector2 array containing the position and velocity from
	 *         each evaluation
	 */
	private static Vector2[] evaluatePosition(double factor, Vector2 pos, Vector2 vel,
			Rigidbody2D rigidbody) {

		// Method 1 //
		// This feels slower and is probably tougher to read, but is the
		// "better"
		// way of doing things with respect to OOD guidelines.
		/*
		 * pos = pos.add(vel.scale(dt*factor));
		 * 
		 * Vector2 acc = vel.scale(-RB.zeta/RB.mass); acc =
		 * acc.add(RB.force.scale(1/RB.mass)); acc = acc.add(gravityVector);
		 */
		// Add in gravity vector vel = vel.add(acc.scale(dt*factor));

		// Method 2 //
		// This feels faster and is more concise, but reflects "poor" OOD
		// principles (we shouldn't be accessing these fields directly)

		pos.x += vel.x * dt * factor;
		pos.y += vel.y * dt * factor;
		vel.x += dt
				* factor
				* ((rigidbody.force.x - rigidbody.getDrag() * vel.x) / rigidbody.getMass() + gravityVector.x);
		vel.y += dt
				* factor
				* ((rigidbody.force.y - rigidbody.getDrag() * vel.y) / rigidbody.getMass() + gravityVector.y);

		Vector2[] retVals = { pos, vel };
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
		// TODO We will eventually implement this method. Currently I am unsure
		// where to place collision
		// resolution in terms of physics updates, but I have some ideas. I will
		// need to get the quadtree up and running, though this is not
		// necessarily as important. In the mean time we could do the naive
		// implementation in which every object is checked agianst every other
		// object. Once I get the quadtree going, this bottleneck would be
		// removed.

		// Simple rectangular collision (naive implementation)
		boolean collided = false;

		if ( col1 instanceof BoxCollider2D ) {
			// If col2 is a BoxCollider
			if ( col2 instanceof BoxCollider2D ) {
				if ( col1.getBoundedArea().intersects(col2.getPosition().x, col2.getPosition().y,
						col2.size.x, col2.size.y) )
					collided = true;
				else
					collided = false;
			}
			else if ( col2 instanceof CircleCollider2D ) {
				// TODO col1 is a BoxCollider and col2 is a CircleCollider
			}
		}
		else if ( col1 instanceof CircleCollider2D ) {
			// If col2 is a box collider
			if ( col2 instanceof BoxCollider2D ) {
				// TODO col1 is a CircleCollider and col2 is a BoxCollider
			}
			else if ( col2 instanceof CircleCollider2D ) {
				// TODO Both Colliders are CircleColliders
			}
		}
		return collided;
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
	static void resolveCollision(Collider col1, Collider col2) {

		// These methods are called so that certain high-level scripting
		// behaviors can be obtained by knowing when a collision has occured
		// with another collider, and performing actions as a result of that
		// collision (ie destroying an object when it has been hit--user
		// determined)
		col1.getGameObject().onCollision(col2);
		col2.getGameObject().onCollision(col1);

		// TODO (Joe) Implement the remainder of this method
		// The rigidbodies can be gotten as follows:
		Rigidbody2D col1_rb = col1.getGameObject().getRigidbody();
		Rigidbody2D col2_rb = col2.getGameObject().getRigidbody();

	}
}
