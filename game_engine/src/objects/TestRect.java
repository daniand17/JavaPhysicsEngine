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
		// No control inputs
	}

	@Override
	public void update() {
		if ( transform.position.y > Display.SIZE.height ){
			rigidbody.position.y = 0;}
		else if ( transform.position.y < 0 ){
			rigidbody.position.y = Display.SIZE.height;}
		
		if ( transform.position.x > Display.SIZE.width ){
			rigidbody.position.x = 0;}
		else if ( transform.position.x < 0 ){
			rigidbody.position.x = Display.SIZE.width;}
		}		
}

