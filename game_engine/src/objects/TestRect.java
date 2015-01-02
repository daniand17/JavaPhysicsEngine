package objects;

import game_engine.Display;
import game_engine.GameObject;
import game_engine.Input;
import game_engine.RigidBody;
import game_engine.SquareRenderer;
import game_engine.Vector2;

import java.awt.event.KeyEvent;

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
		rigidbody = new RigidBody();
		renderer = new SquareRenderer();
	}

	@Override
	public void physicsUpdate() {
		// No control inputs
	}

	@Override
	public void update() {
		if ( transform.position.y > Display.SIZE.height ){
			transform.position.y = 0;}
		else if ( transform.position.y < 0 ){
			transform.position.y = Display.SIZE.height;}
		
		if ( transform.position.x > Display.SIZE.width ){
			transform.position.x = 0;}
		else if ( transform.position.x < 0 ){
			transform.position.x = Display.SIZE.width;}
		}		
}

