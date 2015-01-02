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

	private boolean debug = true;
	private double gain = 100.0d;

	@Override
	/**
	 * Initialize rigid body and rendered objects.
	 */
	public void start() {
		// The name of this game object
		this.name = "PlayerController";
		rigidbody = new Rigidbody2D();
		renderer = new SquareRenderer(new Vector2(64d, 64d));
		collider = new BoxCollider2D(new Vector2(64d, 64d));
		// Sets initial rotational characteristics
		rigidbody.setAngularDrag(1);
		rigidbody.setInertia(1);
		rigidbody.gravityScale = 0;
	}

	@Override
	/**
	 * Check for control inputs. Currently monitors for "key down"
	 * events on W, A, S, and D.
	 */
	public void physicsUpdate() {
		if ( Input.getKeyDown(KeyEvent.VK_W) ) {
			getRigidbody().addForce(getTransform().up(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_S) ) {
			getRigidbody().addForce(getTransform().down(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_D) ) {
			getRigidbody().addForce(getTransform().right(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_A) ) {
			getRigidbody().addForce(getTransform().left(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_Q) ) {
			getRigidbody().addTorque(-gain * .1);
		}
		if ( Input.getKeyDown(KeyEvent.VK_E) ) {
			getRigidbody().addTorque(gain * .1);
		}
	}

	@Override
	/** 
	 * Performs screen wraparound operations for the associated rigidbody.
	 */
	public void update() {

		if ( getTransform().position.y > Display.SIZE.height ) {
			getTransform().position.y = 0;
		}
		else if ( getTransform().position.y < 0 ) {
			getTransform().position.y = Display.SIZE.height;
		}

		if ( getTransform().position.x > Display.SIZE.width ) {
			getTransform().position.x = 0;
		}
		else if ( getTransform().position.x < 0 ) {
			getTransform().position.x = Display.SIZE.width;
		}
	}

	@Override
	public void onCollision(Collider other) {
		Debug.log(name, "Collided with: " + other.getGameObject().name);
	}
}
