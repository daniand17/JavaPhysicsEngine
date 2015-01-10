package objects;

import game_engine.Debug;
import game_engine.GameObject;
import game_engine.Input;
import game_engine.Vector2;
import graphics.Display;
import graphics.EllipseRenderer;
import graphics.Renderer;
import graphics.Renderer.Renderers;

import java.awt.event.KeyEvent;

import physics.Collider;
import physics.Collider.Colliders;
import physics.Rigidbody2D;

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

	private double gain = 250;

	@Override
	/**
	 * Initialize rigid body and rendered objects.
	 */
	public void start() {
		// The name of this game object
		this.name = "PlayerController";
		rigidbody = new Rigidbody2D(getTransform());
		renderer = Renderer.createRenderer(Renderers.ELLIPSE_2D, this, getTransform());

		collider = Collider.createCollider(Colliders.ELLIPSE_2D, this, this.getTransform());

		// Sets initial rotational characteristics
		rigidbody.setAngularDrag(1);
		rigidbody.setInertia(1000d);

		rigidbody.setGravityScale(0);
	}

	@Override
	/**
	 * Check for control inputs. Currently monitors for "key down"
	 * events on W, A, S, and D.
	 */
	public void physicsUpdate() {

		if ( Input.getKeyDown(KeyEvent.VK_W) ) {
			getRigidbody().addForce(Vector2.up(), gain);
		}

		if ( Input.getKeyDown(KeyEvent.VK_S) ) {
			getRigidbody().addForce(Vector2.down(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_D) ) {
			getRigidbody().addForce(Vector2.right(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_A) ) {
			getRigidbody().addForce(Vector2.left(), gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_Q) ) {
			getRigidbody().addTorque(-5 * gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_E) ) {
			getRigidbody().addTorque(5 * gain);
		}
	}

	@Override
	/** 
	 * Performs screen wraparound operations for the associated rigidbody.
	 */
	public void update() {
		Vector2 pos = getTransform().getPosition();

		if ( pos.y > Display.SIZE.height )
			pos.y = 0;
		if ( pos.x > Display.SIZE.width )
			pos.x = 0;
		if ( pos.x < 0 )
			pos.x = Display.SIZE.width;
		if ( pos.y < 0 )
			pos.y = Display.SIZE.height;

	}

	@Override
	public void onCollision(Collider other) {

	}
}
