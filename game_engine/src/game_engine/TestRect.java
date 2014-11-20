package game_engine;

import java.awt.event.KeyEvent;

public class TestRect extends GameEntity {

	private float accel = 1f;

	public TestRect() {
		this(0, 0, 0);
	}

	public TestRect(int x, int y, int z) {
		super(x, y, z);

		// Sets up the rigidbody and renderer for this component
		rigidbody = new RigidBody();
		renderer = new SquareRenderer();
		if ( transform == null )
			Debug.log("Did you call the super() constructor in" + this.getClass());
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
}
