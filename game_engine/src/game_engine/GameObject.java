package game_engine;

import graphics.Renderer;
import physics.Collider;
import physics.Rigidbody2D;

public abstract class GameObject implements IGameObject {

	public String name = "GameObject";
	// All game objects have a transform starting at 0, 0
	private Transform transform = new Transform();
	// If this object has a rigidbody, it will partake in physics updates.
	protected Rigidbody2D rigidbody;
	// This object will be rendered if this is not null
	protected Renderer renderer;
	protected Collider collider;

	public void onCollision(Collider other) {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
	}

	/**
	 * This method is used primarily to do physics based things if you want them
	 * to occur this physics update (ie adding forces etc)
	 */
	public void physicsUpdate() {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
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
	public void update() {
		// This is called here so that this method doesn't need to be
		// implemented in an inheriting class
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
