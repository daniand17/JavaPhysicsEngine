package game_engine;

public class RigidBody extends Component {

	public Vector2 velocity; // The velocity of this RigidBody.
	public double mass; // The mass of this RigidBody
	public double zeta; // The drag coefficient of this RigdBody
	public Vector2 force; // Force acting on the RigidBody for the next physics
							// update

	/**
	 * Default public constructor. Creates a new velocity vector with no
	 * velocity, initializes the mass to 5 (kg), the drag to 0.001, and the
	 * gravity scale as defined in the Physics class.
	 */
	public RigidBody() {
		if ( velocity == null )
			velocity = new Vector2();
		mass = 1d;
		zeta = 0.1d;
		setForce(new Vector2(1d, 0d), 0d);
	}

	/**
	 * Paramaterized public constructor. Creates a new rigid body with specified
	 * velocity and mass.
	 * 
	 * @param Vector2
	 *            initVelocity The initial velocity of the rigidbody
	 * 
	 * @param initMass
	 *            The initial mass of the rigidbody
	 */
	public RigidBody(Vector2 velocity, double mass, double zeta) {
		this.velocity = velocity;
		this.mass = mass;
		this.zeta = zeta;
	}

	/**
	 * Method to SET a force on the object, wiping out any other forces
	 * currently active.
	 * 
	 * @param direction
	 *            Direction of the force. Should be a unit vector, but will be
	 *            divided by its magnitude
	 * 
	 * @param amount
	 *            Magnitude of the desired force.
	 */
	public void setForce(Vector2 direction, double amount) {
		// Don't supply a zero value for direction
		force = direction.scale(amount / direction.norm());
	}

	/**
	 * Method to ADD a force to an object, in addition to any other forces
	 * currently present.
	 * 
	 * @param direction
	 *            Direction of the force. Should be a unit vector, but will be
	 *            divided by its magnitude
	 * 
	 * @param amount
	 *            Magnitude of the desired force.
	 */
	public void addForce(Vector2 direction, double amount) {
		// Don't supply a zero value for direction
		force = force.add(direction.scale(amount / direction.norm()));
	}
}
