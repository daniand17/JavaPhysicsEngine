package game_engine;

public class Rigidbody2D extends Component {

	public Vector2 velocity; // The velocity of this Rigidbody.
	private double mass; // The mass of this RigidBody
	private double drag; // The drag coefficient of this Rigidbody
	Vector2 force; // Force acting on the RigidBody for the next physics
					// update
	public double angularSpeed;
	private double angularDrag;

	/**
	 * Default public constructor. Creates a new velocity vector with no
	 * velocity, initializes the mass to 5 (kg), the drag to 0.001, and the
	 * gravity scale as defined in the Physics class.
	 */
	public Rigidbody2D() {
		if ( velocity == null )
			velocity = new Vector2();
		setMass(1d);
		setDrag(0.1d);
		setForce(new Vector2(1d, 0d), 0d);
	}

	/**
	 * Paramaterized public constructor. Creates a new rigid body with specified
	 * mass and drag
	 * 
	 * @param mass
	 *            the mass of this rigidbody
	 * @param zeta
	 *            the drag of this rigidbody
	 */
	public Rigidbody2D(double mass, double zeta) {
		this.setMass(mass);
		this.setDrag(zeta);
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
	void setForce(Vector2 direction, double amount) {
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

	public void addTorque(VectorN axis) {
		// TODO (Joe) implement this method

		// use transform.rotation = result of physics integration called in this
		// method
		// We will likely need to discuss some more architectural aspects of
		// this since I'm not sure yet how we should be doing integration for
		// this
	}

	/**
	 * Get the double representation of the mass of this rigidbody
	 * 
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Sets the mass to always be something slightly greater than zero since
	 * nothing is massless
	 * 
	 * @param mass
	 *            the mass to set
	 */
	public void setMass(double mass) {
		if ( mass > 0 )
			this.mass = mass;
		else
			this.mass = 0.01;
	}

	/**
	 * Gets the double representation of the drag of this rigidbody
	 * 
	 * @return the drag
	 */
	public double getDrag() {
		return drag;
	}

	/**
	 * Sets the drag of this rigidbody. If a negative number is supplied then it
	 * sets the drag to zero
	 * 
	 * @param drag
	 *            the drag to set
	 */
	public void setDrag(double drag) {
		if ( drag >= 0 )
			this.drag = drag;
		else
			this.drag = 0;
	}

	/**
	 * @return the angularDrag
	 */
	public double getAngularDrag() {
		return angularDrag;
	}

	/**
	 * @param angularDrag
	 *            the angularDrag to set
	 */
	public void setAngularDrag(double angularDrag) {
		if ( angularDrag >= 0 )
			this.angularDrag = angularDrag;
		else
			angularDrag = 0;
	}
}
