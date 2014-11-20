package game_engine;

import java.awt.event.KeyEvent;

public class TestRect extends GameEntity {

	private float accel = 1f;
	boolean debug = true;

	public void start() {
		// This function is used for calling things that you want to happen after the object is
		// instantiated, but after the constructor
	}

	public TestRect() {
		// Sets up the rigidbody and renderer for this component
		rigidbody = new RigidBody();
		renderer = new SquareRenderer();
	}

	public void fixedUpdate() {

		float xV = 0f;
		float yV = 0f;
		float zV = 0f;

		if ( Input.getKeyDown(KeyEvent.VK_S) )
			yV = accel;
		if ( Input.getKeyDown(KeyEvent.VK_W) )
			yV += -accel;
		if ( Input.getKeyDown(KeyEvent.VK_A) )
			xV = -accel;
		if ( Input.getKeyDown(KeyEvent.VK_D) )
			xV += accel;
		// Sets the velocity given input
		rigidbody.velocity = new Vector3(rigidbody.velocity.x + xV, rigidbody.velocity.y + yV,
				rigidbody.velocity.z + zV);
		// Wraparound on the x axis
		if ( transform.position.x < 0 )
			transform.position = new Vector3(800, transform.position.y, transform.position.z);
		else if ( transform.position.x > 800 )
			transform.position = new Vector3(transform.position.x % 800, transform.position.y,
					transform.position.z);
		// Wraparound on the y axis
		if ( transform.position.y < 0 )
			transform.position = new Vector3(transform.position.x, 600, transform.position.z);
		else if ( transform.position.y > 600 )
			transform.position = new Vector3(transform.position.x, transform.position.y % 600,
					transform.position.z);
	}

	public void update() {
		// This function is for doing things you want done outside the physics loop, and more often
		// than a physics step

	}
}
