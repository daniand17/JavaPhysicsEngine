package objects;

import game_engine.Display;
import game_engine.Input;
import game_engine.ObjectManager;
import game_engine.Vector2;
import interfaces_abstracts.GameEntity;

import java.awt.event.KeyEvent;

import components.RigidBody;
import components.SquareRenderer;
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
public class PlayerController extends GameEntity {

	boolean debug = true;
	double gain = 100.0d;
	private Vector2 xVec = new Vector2(1d, 0d);
	private Vector2 yVec = new Vector2(0d, 1d);
	
	@Override
	/**
	 * Initialize rigid body and rendere objects.
	 */
	public void start() {
		rigidbody = new RigidBody();
		renderer = new SquareRenderer();
	}

	@Override
	/**
	 * Check for control inputs. Currently monitors for "key down"
	 * events on W, A, S, and D.
	 */
	public void fixedUpdate() {
		rigidbody.setForce(xVec, 0d);
		if ( Input.getKeyDown(KeyEvent.VK_W) ){
			rigidbody.addForce(yVec, -gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_S) ){
			rigidbody.addForce(yVec, gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_D) ){
			rigidbody.addForce(xVec, gain);
		}
		if ( Input.getKeyDown(KeyEvent.VK_A) ){
			rigidbody.addForce(xVec, -gain);
		}
		
	}
	
	@Override
	/** 
	 * Performs screen wraparound operations for the associated rigidbody.
	 */
	public void update() {
		
		if ( transform.position.y > Display.SIZE.height ){
			rigidbody.position.y = 0;}
		else if ( transform.position.y < 0 ){
			rigidbody.position.y = Display.SIZE.height;}
		
		if ( transform.position.x > Display.SIZE.width ){
			rigidbody.position.x = 0;}
		else if ( transform.position.x < 0 ){
			rigidbody.position.x = Display.SIZE.width;}
		}		
}


