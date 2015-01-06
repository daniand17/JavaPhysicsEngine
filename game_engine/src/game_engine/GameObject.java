package game_engine;

import java.util.List;

public abstract class GameObject implements IGameObject {

	public String name = "GameObject";
	// All game objects have a transform starting at 0, 0
	private Transform transform = new Transform();
	// If this object has a rigidbody, it will partake in physics updates.
	protected Rigidbody2D rigidbody;
	// This object will be rendered if this is not null
	protected Renderer renderer;
	protected Collider collider;

	@Override
	public void addComponent(Component newComponent) {

		if ( newComponent instanceof Rigidbody2D )
			this.rigidbody = (Rigidbody2D) newComponent;
		else if ( newComponent instanceof BoxCollider2D )
			this.collider = (BoxCollider2D) newComponent;
		else if ( newComponent instanceof EllipseCollider2D )
			this.collider = (EllipseCollider2D) newComponent;
		else if ( newComponent instanceof SquareRenderer )
			this.renderer = (SquareRenderer) newComponent;
	}

	void initializeComponentReferences() {
		if ( transform != null )
			transform.initializeComponentReferences(this, transform);
		if ( rigidbody != null )
			rigidbody.initializeComponentReferences(this, getTransform());
		if ( renderer != null )
			renderer.initializeComponentReferences(this, getTransform());
		if ( collider != null )
			collider.initializeComponentReferences(this, getTransform());
	}

	@Override
	public void onCollision(Collider other) {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * This method is used primarily to do physics based things if you want them
	 * to occur this physics update (ie adding forces etc)
	 */
	@Override
	public void physicsUpdate() {
		// TODO this is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * This is a package-access method that is used to resolve collisions
	 * involving this GameObject. It is called only if this object has a
	 * collider attached to it. It is handed a list of objects in which
	 * collisions may occur
	 * 
	 * @param collidingObjects
	 *            the list of objects that MIGHT collide with this game object
	 */
	void resolveCollisions(List<GameObject> collidingObjects) {
		getCollider().collisionsResolvedThisFrame = true;

		if ( collidingObjects.size() == 0 )
			return;

		// Iterate through the list of colliding objects
		for (GameObject obj : collidingObjects)
			// Checks to see if this collisions was already resolved
			if ( !obj.getCollider().collisionsResolvedThisFrame ) {
				Physics.resolveCollision(this.getCollider(), obj.getCollider());
				// if ( Physics.collided(this.getCollider(), obj.getCollider())
				// )

			}
	}

	/**
	 * Called upon instantiation of a game object before all physics updates and
	 * logic updates. Used primarily to generate references and such before game
	 * logic takes place.
	 */
	@Override
	public void start() {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * This method is called right after the physics updates. Used for other
	 * game logic based things once all collisions are resolved etc.
	 */
	@Override
	public void update() {
		// TODO this is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * This method is called by the game thread only if there is a rigidbody
	 * attached. It is used to update the position of the GameObject using
	 * physics by passing the position of the transform and the rigidbody to the
	 * Physics class for integration. The rigidbody is a necessary parameter to
	 * pass because it contains both the mass and drag of the object, in
	 * addition to velocity.
	 * 
	 * @param t
	 *            the current time
	 * @param dt
	 *            the time step to integrate over
	 */
	void updatePhysics(double t, double dt) {

		// Obtain prior position and rotation
		Vector2 prevPos = getTransform().getPosition();
		double prevTheta = getTransform().getRotation();

		physicsUpdate(); // Check for control inputs, gravity, collisions

		// Perform integration from current to updates state
		Vector2[] physicsResults = Physics
				.integrateState(t, dt, prevPos, prevTheta, getRigidbody());

		// Update transform position and rotation
		transform.setPosition(physicsResults[0]);
		transform.setRotation(physicsResults[1].x);

		// Update renderer position and rotation
		if ( renderer != null )
			// FIXME turn on rotations
			renderer.updateRendererPositions(prevPos, getTransform().getPosition());
		// render.updateRendererRotation(prevTheta,
		// getTransform().getRotation());

		// Reset the forces on the object
		rigidbody.setForce(Vector2.right(), 0);
		rigidbody.setTorque(0);
	}

	/**
	 * Returns the collider attached to this object
	 * 
	 * @return the collider
	 */
	public Collider getCollider() {
		return collider;
	}

	/**
	 * Returns the renderer attached to this object
	 * 
	 * @return the renderer
	 */
	public Renderer getRenderer() {
		return renderer;
	}

	/**
	 * Returns the rigidbody attached to this object
	 * 
	 * @return the rigidbody
	 */
	public Rigidbody2D getRigidbody() {
		return rigidbody;
	}

	/**
	 * @return the transform
	 */
	public Transform getTransform() {
		return transform;
	}
}
