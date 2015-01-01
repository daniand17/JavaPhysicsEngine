package components;

import game_engine.Vector2;

public class RigidBody {

	public Vector2 velocity; // The velocity of this rigidbody.
	public float mass; // The mass of this rigidbody

	/**
	 * The only public constructor. Creates a new velocity vector with no
	 * velocity, initializes the mass to 5 (kg), the drag to 0.001, and the
	 * gravity scale as defined in the Physics class.
	 */
	public RigidBody() {
		velocity = new Vector2();
		mass = 5f;
	}

	//public void addForce(Vector2 direction, double amount) {
	//	return direction.scale(amount);
	//}
}
