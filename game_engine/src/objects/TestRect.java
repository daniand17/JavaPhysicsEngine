package objects;

import game_engine.GameObject;
import game_engine.Vector2;
import graphics.Display;
import graphics.Renderer;
import graphics.SquareRenderer;
import graphics.Renderer.Renderers;
import physics.Collider;
import physics.Collider.Colliders;
import physics.Rigidbody2D;

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
		rigidbody = new Rigidbody2D(getTransform());
		rigidbody.setAngularDrag(1);
		rigidbody.setDrag(.1);
		renderer = Renderer.createRenderer(Renderers.SQUARE_2D, this, getTransform());
		collider = Collider.createCollider(Colliders.RECTANGLE_2D, this, getTransform());
		rigidbody.setGravityScale(0);
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
