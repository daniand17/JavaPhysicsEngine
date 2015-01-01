package objects;

import game_engine.Display;
import game_engine.Input;
import game_engine.Vector2;
import interfaces_abstracts.GameEntity;

import java.awt.event.KeyEvent;

import components.RigidBody;
import components.SquareRenderer;

public class TestRect extends GameEntity {

	private float accel = 1f;
	boolean debug = true;

	@Override
	public void start() {
		// This function is used for calling things that you want to happen
		// after the object is
		// instantiated, but after the constructor
	}

	public TestRect() {
		// Sets up the rigidbody and renderer for this component
		rigidbody = new RigidBody();
		renderer = new SquareRenderer();
	}

	@Override
	public void fixedUpdate() {

		float xV = 0f;
		float yV = 0f;
		float zV = 0f;

		if (Input.getKeyDown(KeyEvent.VK_S))
			yV = accel;
		if (Input.getKeyDown(KeyEvent.VK_W))
			yV += -accel;
		if (Input.getKeyDown(KeyEvent.VK_A))
			xV = -accel;
		if (Input.getKeyDown(KeyEvent.VK_D))
			xV += accel;
		// Sets the velocity given input
		rigidbody.velocity = new Vector2(rigidbody.velocity.x + xV,
				rigidbody.velocity.y + yV);
		// Wraparound on the x axis
		if (transform.position.x < 0)
			transform.position = new Vector2(Display.SIZE.width,
					transform.position.y);
		else if (transform.position.x > 800)
			transform.position = new Vector2(transform.position.x
					% Display.SIZE.width, transform.position.y);

		// Wraparound on the y axis
		if (transform.position.y < 0)
			transform.position = new Vector2(transform.position.x,
					Display.SIZE.height);
		else if (transform.position.y > Display.SIZE.height)
			transform.position = new Vector2(transform.position.x,
					transform.position.y % Display.SIZE.height);
	}

	@Override
	public void update() {
		// This function is for doing things you want done outside the physics
		// loop, and more often
		// than a physics step
	}
}
