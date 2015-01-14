package physics;

import game_engine.Component;
import game_engine.Transform;
import game_engine.Vector2;

public class Rigidbody2D extends Component {

	public Vector2 velocity; // The velocity of this Rigidbody.
	private double mass; // The mass of this RigidBody
	private double inertia = 1; // The inertia of this Rigidbody
	private double drag; // The linear, viscous drag coefficient of this
							// Rigidbody
	private double angularDrag; // Viscous angular drag coefficient (linear)

	// Package access fields used in the physics class
	Vector2 force; // Force acting on the RigidBody for the next physics
	double torque; // Angular force component acting on this RigidBody
	// update
	private double angularSpeed; // Rotational speed; positive CW
	private double gravityScale = 1;

	/**
	 * Default public constructor. Creates a new velocity vector with no
	 * velocity, initializes the mass to 5 (kg), the drag to 0.1, and the
	 * gravity scale as defined in the Physics class.
	 */
	public Rigidbody2D(Transform trans) {

		this.name = "Rigidbody2D";

		this.transform = trans;
		if ( velocity == null )
			velocity = Vector2.zero();
		setMass(1d);
		setInertia(1000d);
		setDrag(1);
		setAngularDrag(5d);
		setForce(new Vector2(1d, 0d), 0d);
	}

	public void updateRigidbodyPhysics(double t, double dt) {

		// Obtain prior position and rotation
		Vector2 prevPos = getTransform().getPosition();
		double prevTheta = getTransform().getRotation();

		Vector2[] physicsResults = Physics.integrateState(t, dt, prevPos, prevTheta, this);

		// Update transform position and rotation
		transform.setPosition(physicsResults[0]);
		transform.setRotation(physicsResults[1].x);

		// Reset the forces on the object
		setForce(Vector2.right(), 0);
		setTorque(0);
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
	public Rigidbody2D(double mass, double zeta, double angularDrag, double inertia) {
		this.setMass(mass);
		this.setInertia(inertia);
		this.setDrag(zeta);
		this.setAngularDrag(angularDrag);
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	/**
	 * Method to SET a force on the object, wiping out any other forces
	 * currently active.
	 * 
	 * @param direction
	 *            Direction of the force. Should be a unit vector, but will be
	 *            divided by its magnitude just in case. (Don't supply 0
	 *            vectors!)
	 * 
	 * @param amount
	 *            Magnitude of the desired force.
	 */
	private void setForce(Vector2 direction, double amount) {
		// Don't supply a zero value for direction
		force = direction.scale(amount / direction.norm());

	}

	/**
	 * Method to SET a torque on the object, wiping out any other amount
	 * 
	 * @param torque
	 *            Magnitude of desired torque. Positive CW.
	 * 
	 */
	private void setTorque(double torque) {
		this.torque = torque;
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

		// Checks for zero vector and returns without adding force
		if ( direction.x == 0 && direction.y == 0 )
			return;
		force = force.add(direction.scale(amount / direction.norm()));
	}

	/**
	 * Method to ADD a torque to an object, in addition to any other torque
	 * currently present.
	 * 
	 * @param torque
	 *            Amount of torque to add. Positive CW.
	 */
	public void addTorque(double torque) {
		this.torque += torque;
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
	 * Sets the inertia to always be something slightly greater than zero since
	 * nothing is inertia-less
	 * 
	 * @param inertia
	 *            the inertia to set
	 */
	public void setInertia(double inertia) {

		// TODO eventually auto-calc this based on the attached collider, and
		// make this method package access
		if ( inertia > 0 )
			this.inertia = inertia;
		else
			this.inertia = 0.01;
	}

	/**
	 * Get the double representation of the inertia of this rigidbody
	 * 
	 * @return the inertia
	 */
	public double getInertia() {
		return inertia;
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

	/**
	 * @return the angularSpeed
	 */
	public double getAngularSpeed() {
		return angularSpeed;
	}

	/**
	 * @param angularSpeed
	 *            the angularSpeed to set
	 */
	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}

	/**
	 * @return the gravityScale
	 */
	public double getGravityScale() {
		return gravityScale;
	}

	/**
	 * @param gravityScale
	 *            the gravityScale to set
	 */
	public void setGravityScale(double gravityScale) {
		this.gravityScale = gravityScale;
	}
}
