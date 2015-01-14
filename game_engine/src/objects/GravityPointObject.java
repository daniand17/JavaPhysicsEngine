package objects;

import game_engine.GameObject;
import game_engine.Vector2;
import graphics.GraphicsThread;
import graphics.Renderer;
import graphics.Renderer.Renderers;
import physics.Collider;
import physics.Collider.Colliders;
import physics.Rigidbody2D;
import physics.GravityPoint;

public class GravityPointObject extends GameObject {

	boolean debug = true;

	@Override
	public void start() {
		// This function is used for calling things that you want to happen
		// after the object is instantiated, but after the constructor
		// The name of this game object
		this.name = "GravityPoint";
		// Sets up the rigidbody and renderer for this component
		rigidbody = new Rigidbody2D(getTransform());
		// TODO: Eliminate "magic number" arbitrary defaults.
		gravitypoint = new GravityPoint(getTransform(), 1000000d, 32d);
		rigidbody.setDrag(0.1d);
		renderer = Renderer.createRenderer(Renderers.SQUARE_2D, this, getTransform());
		collider = Collider.createCollider(Colliders.RECTANGLE_2D, this, getTransform());
		rigidbody.setGravityScale(0d);
	}

	@Override
	public void update() {
		Vector2 pos = getTransform().getPosition();

		if ( pos.y > GraphicsThread.SIZE.height )
			pos.y = 0;
		if ( pos.x > GraphicsThread.SIZE.width )
			pos.x = 0;
		if ( pos.x < 0 )
			pos.x = GraphicsThread.SIZE.width;
		if ( pos.y < 0 )
			pos.y = GraphicsThread.SIZE.height;

	}
}
