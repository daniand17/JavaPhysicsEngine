package objects;

import game_engine.Collider;
import game_engine.Collider.ColliderTypes;
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
		collider = Collider.createCollider(ColliderTypes.RECTANGLE_2D, new Vector2(64, 64));
		rigidbody.gravityScale = 0;
	}

	@Override
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
}
