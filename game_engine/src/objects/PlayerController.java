package objects;

import game_engine.BoxCollider2D;
import game_engine.Collider;
import game_engine.Debug;
import game_engine.Display;
import game_engine.GameObject;
import game_engine.Input;
import game_engine.Rigidbody2D;
import game_engine.SquareRenderer;
import game_engine.Vector2;

import java.awt.event.KeyEvent;

/**
 * 
 * Basic player controller class for initial testing.
 * 
 * @field debug: Currently non-functional
 * 
 * @field gain: Control gain on the force. Arbitrary value used to "feel" nice
 * 
 * @field xVec: Control direction associated with left/right movement
 * 
 * @field yVec: Control direction associated with up/down movement
 */
public class PlayerController extends GameObject {

	boolean debug = true;
	double gain = 100.0d;
	private Vector2 xVec = new Vector2(1d, 0d);
	private Vector2 yVec = new Vector2(0d, 1d);

	@Override
	/**
	 * Initialize rigid body and rendered objects.
	 */
	public void start() {
		// The name of this game object
		this.name = "PlayerController";
		rigidbody = new Rigidbody2D();
		renderer = new SquareRenderer(new Vector2(64f, 64f));
		collider = new BoxCollider2D(new Vector2(64f, 64f));

	}

	@Override
	/**
	 * Check for control inputs. Currently monitors for "key down"
	 * events on W, A, S, and D.
	 */
	public void physicsUpdate() {
		rigidbody.setForce(xVec, 0d);
		if ( Input.getKeyDown(KeyEvent.VK_W) ) {
			rigidbody.addForce(yVec, -gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_S) ) {
			rigidbody.addForce(yVec, gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_D) ) {
			rigidbody.addForce(xVec, gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_A) ) {
			rigidbody.addForce(xVec, -gain);
		}
	}

	@Override
	/** 
	 * Performs screen wraparound operations for the associated rigidbody.
	 */
	public void update() {

		if ( transform.position.y > Display.SIZE.height ) {
			transform.position.y = 0;
		}
		else if ( transform.position.y < 0 ) {
			transform.position.y = Display.SIZE.height;
		}

		if ( transform.position.x > Display.SIZE.width ) {
			transform.position.x = 0;
		}
		else if ( transform.position.x < 0 ) {
			transform.position.x = Display.SIZE.width;
		}
	}

	public void onCollision(Collider other) {
		Debug.log(name, "Collided with: " + other.gameObject.name);
	}
}
