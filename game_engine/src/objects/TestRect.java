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
		// after the object is instantiated, but after the constructor
		// The name of this game object
		this.name = "TestRect";
		// Sets up the rigidbody and renderer for this component
		rigidbody = new Rigidbody2D();
		renderer = new SquareRenderer(new Vector2(64f, 64f));
		collider = new BoxCollider2D(new Vector2(64f, 64f));
		rigidbody.gravityScale = 0;
	}

	@Override
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
}
