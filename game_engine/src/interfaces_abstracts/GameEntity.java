package interfaces_abstracts;

import game_engine.Physics;
import game_engine.Vector2;
import components.RigidBody;
import components.Transform;

public abstract class GameEntity implements PhysicsEntity, CanUpdate {

	// All game objects have a transform starting at 0, 0
	public Transform transform = new Transform();
	// If this object has a rigidbody, it will partake in physics updates.
	public RigidBody rigidbody;
	// This object will be rendered if this is not null
	public Renderer renderer;

	/**
	 * The empty constructor used to create a game entity
	 */
	public GameEntity() {

	}

	public void updatePhysics(double t, double dt) {
		if ( rigidbody != null ) {
			// Allows implementing class to do physics stuff
			fixedUpdate(); // Get control inputs prior to physics calculation
			// TODO: I think fixedUpdate() is where collision checking should occur,
			// so that post-contact velocities are updated prior to the next physics call
			
			// Store previous position for use in the renderer
			Vector2 prevPos = rigidbody.position.scale(1d);
			
			// TODO: Ideally, we could just instantiate the Physics object once, per
			// GameEntity, then call integrateRB() at each step. That would have 
			// to be done in the constructor, however, and I'm not sure how the constructor works
			// in this class, i.e. at what point the GameEntity knows whether or 
			// not it has a rigidbody. This works for now.
			
			new Physics(dt, rigidbody);
			update(); // check for screen wraparound 

			transform.position = rigidbody.position.scale(1d); // Get new position.
			// Give the renderer the old and new positions for rendering of the
			// obj
			if ( renderer != null )
				// FIXME this might become an issue later on if we want to
				// interpolate positions of non-rigidbody objects that are
				// moving
				renderer.updateRendererPositions(prevPos, transform.position);
		}
	}

	/**
	 * This method is used primarily to do physics based things if you want them
	 * to occur this physics update (ie adding forces etc)
	 */
	abstract public void fixedUpdate();

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
