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

	/**
	 * The empty constructor used to create a game entity
	 */
	public GameObject() {

	}

	void initializeComponentReferences() {
		if ( getRigidbody() != null )
			getRigidbody().initializeComponentReferences(this, getTransform());
		if ( getRenderer() != null )
			getRenderer().initializeComponentReferences(this, getTransform());
		if ( getCollider() != null )
			getCollider().initializeComponentReferences(this, getTransform());
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

		Vector2 prevPos = getTransform().position;
		// Allows implementing class to do physics stuff
		physicsUpdate(); // Get control inputs prior to physics calculation
		// Store previous position for use in the renderer

		Vector2[] physicsResults = Physics.integrateState(t, dt, prevPos, getRigidbody());

		// FIXME can change this once physics is fixed
		getTransform().position = physicsResults[0];
		getRigidbody().velocity = physicsResults[1];
		// position.
		// Give the renderer the old and new positions for rendering of the
		// obj
		if ( getRenderer() != null )
			// FIXME this might become an issue later on if we want to
			// interpolate positions of non-rigidbody objects that are
			// moving
			getRenderer().updateRendererPositions(prevPos, getTransform().position);
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

		// Iterate through the list of colliding objects
		for (GameObject obj : collidingObjects)
			// Checks to see if this collisions was already resolved
			if ( !obj.getCollider().collisionsResolvedThisFrame ) {
				if ( Physics.collided(this.getCollider(), obj.getCollider()) )
					Physics.resolveCollision(this.getCollider(), obj.getCollider());
			}
	}

	/**
	 * This method is used primarily to do physics based things if you want them
	 * to occur this physics update (ie adding forces etc)
	 */
	public void physicsUpdate() {
		// TODO this is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * This method is called right after the physics updates. Used for other
	 * game logic based things once all collisions are resolved etc.
	 */
	public void update() {
		// TODO this is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * Called upon instantiation of a game object before all physics updates and
	 * logic updates. Used primarily to generate references and such before game
	 * logic takes place.
	 */
	public void start() {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	public void onCollision(Collider other) {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
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
	 * Returns the renderer attached to this object
	 * 
	 * @return the renderer
	 */
	public Renderer getRenderer() {
		return renderer;
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
	 * @return the transform
	 */
	public Transform getTransform() {
		return transform;
	}
}
