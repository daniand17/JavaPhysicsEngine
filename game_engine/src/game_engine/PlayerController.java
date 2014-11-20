package game_engine;

import java.awt.event.KeyEvent;

public class PlayerController extends GameEntity {

	boolean debug = true;
	float accel = 2.0f;

	public void start() {
		rigidbody = new RigidBody();
		rigidbody.drag = .001f;
		renderer = new SquareRenderer();
	}

	public void fixedUpdate() {

		if ( Input.getKeyDown(KeyEvent.VK_W) )
			rigidbody.velocity.y -= accel;

		if ( Input.getKeyDown(KeyEvent.VK_S) )
			rigidbody.velocity.y += accel;

		if ( Input.getKeyDown(KeyEvent.VK_D) )
			rigidbody.velocity.x += accel;

		if ( Input.getKeyDown(KeyEvent.VK_A) )
			rigidbody.velocity.x -= accel;

		if ( transform.position.x > Engine.displayDims.x )
			transform.position.x = 0;
		else if ( transform.position.x < 0 )
			transform.position.x = Engine.displayDims.x;
	}

	public void update() {
		// Wraparound
		if ( transform.position.y > Engine.displayDims.y )
			transform.position.y = 0;
		else if ( transform.position.y < 0 )
			transform.position.y = Engine.displayDims.y;
	}

}
