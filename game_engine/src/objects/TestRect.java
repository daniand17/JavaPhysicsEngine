package objects;

import game_engine.BoxCollider2D;
import game_engine.Display;
import game_engine.GameObject;
import game_engine.Rigidbody2D;
import game_engine.SquareRenderer;
import game_engine.Vector2;

public class TestRect extends GameObject {

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
		rigidbody = new Rigidbody2D();
		renderer = new SquareRenderer(new Vector2(64f, 64f));
		collider = new BoxCollider2D(new Vector2(64f, 64f));
	}

	@Override
	public void physicsUpdate() {
		// No control inputs
	}

	@Override
	public void update() {
		if ( transform.position.y > Display.SIZE.height ) {
			transform.position.y = 0;
		} else if ( transform.position.y < 0 ) {
			transform.position.y = Display.SIZE.height;
		}

		if ( transform.position.x > Display.SIZE.width ) {
			transform.position.x = 0;
		} else if ( transform.position.x < 0 ) {
			transform.position.x = Display.SIZE.width;
		}
	}
}
