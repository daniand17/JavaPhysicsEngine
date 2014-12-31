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
			fixedUpdate();
			Vector2 prevPos = transform.position;
			// Get the new position and velocity by calling the physics
			// integration giving position and velocity
			Vector2[] newPosAndVel = Physics.integratePositionFromVelocity(t, dt,
					transform.position, rigidbody.velocity);
			// Update the position and velocity using the return values
			transform.position = newPosAndVel[0];
			rigidbody.velocity = newPosAndVel[1];
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
	public void fixedUpdate() {
		// Unused. Call this in the class implementing GameEntity
	}

	/**
	 * This method is called right after the physics updates. Used for other
	 * game logic based things once all collisions are resolved etc
	 */
	public void update() {
		// Unused. Call this in the class implementing GameEntity
	}

	/**
	 * Called upon instantiation of a game object before all physics updates and
	 * logic updates. Used primarily to generate references and such before game
	 * logic takes place.
	 */
	public void start() {
		// Unused. Call this in the class implementing GameEntity
	}

}
