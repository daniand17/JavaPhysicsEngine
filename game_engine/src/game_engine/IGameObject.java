package game_engine;

import java.awt.Graphics2D;

import physics.Collider;

/**
 * This interface represents the required methods of a GameObject in this
 * engine.
 * 
 * @author andrew
 *
 */
public interface IGameObject {

	/**
	 * The start function is called at the beginning of the game loop, after an
	 * object has been instantiated in the previous frame
	 */
	public void start();

	/**
	 * This method is called if there is a collider attached to the game object,
	 * and a collision occurs with that collider and another collider
	 * 
	 * @param other
	 *            the collider that collided with this collider.
	 */
	public void onCollision(Collider other);

	/**
	 * The fixed timestep update. This is called within the physics loop at
	 * fixed time intervals. This method should be reserved for adding forces to
	 * a rigidbody, and any other high-level physics interactions between
	 * objects
	 */
	public void physicsUpdate();

	/**
	 * This function is called outside the physics loop, in the main game logic
	 * update loop. It can be called more often than the physics loop and should
	 * be used for non-physics based updates to the game object
	 */
	public void update();

	/**
	 * This function is called by the rendering loop and should be used only to
	 * draw GUI components necessary for this game object.
	 * 
	 * @param g2d
	 */
	public void onGUI(Graphics2D g2d);

}
