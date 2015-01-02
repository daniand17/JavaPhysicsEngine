package game_engine;

import java.util.List;

public abstract class GameObject {

	// All game objects have a transform starting at 0, 0
	public Transform transform = new Transform();
	// If this object has a rigidbody, it will partake in physics updates.
	public Rigidbody2D rigidbody;
	// This object will be rendered if this is not null
	public Renderer renderer;
	public Collider collider;

	/**
	 * The empty constructor used to create a game entity
	 */
	public GameObject() {

	}

	void initializeComponentReferences() {
		if ( rigidbody != null )
			rigidbody.initializeComponentReferences(this, transform);
		if ( renderer != null )
			renderer.initializeComponentReferences(this, transform);
		if ( collider != null )
			collider.initializeComponentReferences(this, transform);
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

		Vector2 prevPos = transform.position;
		// Allows implementing class to do physics stuff
		physicsUpdate(); // Get control inputs prior to physics calculation
		// Store previous position for use in the renderer

		Vector2[] physicsResults = Physics.integrateState(t, dt, prevPos, rigidbody);

		// FIXME can change this once physics is fixed
		transform.position = physicsResults[0];
		rigidbody.velocity = physicsResults[1];
		// position.
		// Give the renderer the old and new positions for rendering of the
		// obj
		if ( renderer != null )
			// FIXME this might become an issue later on if we want to
			// interpolate positions of non-rigidbody objects that are
			// moving
			renderer.updateRendererPositions(prevPos, transform.position);
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
		collider.collisionsResolvedThisFrame = true;

		// Iterate through the list of colliding objects
		for (GameObject obj : collidingObjects)
			// Checks to see if this collisions was already resolved
			if ( !obj.collider.collisionsResolvedThisFrame ) {
				if ( Physics.collided(this.collider, obj.collider) )
					Physics.resolveCollision(this.collider, obj.collider);
			}
	}

	/**
	 * This method is used primarily to do physics based things if you want them
	 * to occur this physics update (ie adding forces etc)
	 */
	abstract public void physicsUpdate();

	/**
	 * This method is called right after the physics updates. Used for other
	 * game logic based things once all collisions are resolved etc.
	 */
	abstract public void update();

	/**
	 * Called upon instantiation of a game object before all physics updates and
	 * logic updates. Used primarily to generate references and such before game
	 * logic takes place.
	 */
	abstract public void start();
}
