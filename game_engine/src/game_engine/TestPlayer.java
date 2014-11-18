package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TestPlayer extends GameEntity {

	

	private float speed = 100f;

	public TestPlayer() {
		this(0, 0, 0);
	}

	public TestPlayer(int x, int y, int z) {
		super(x, y, z);
		if ( transform == null )
			Debug.log("Did you call the super() constructor in" + this.getClass());
		rigidbody = new RigidBody();
	}

	@Override
	public void draw(Graphics2D g2d) {

		Color temp = g2d.getColor();
		g2d.setColor(Color.blue);
		g2d.fillRect((int) transform.position.x, (int) transform.position.y, 64, 64);
		g2d.setColor(temp);
	}

	public void fixedUpdate() {

		

		float xV = 0f;
		float yV = 0f;
		float zV = 0f;

		if ( Input.getKeyDown(KeyEvent.VK_S) )
			yV = speed;

		if ( Input.getKeyDown(KeyEvent.VK_W) )
			yV += -speed;

		if ( Input.getKeyDown(KeyEvent.VK_A) )
			xV = -speed;

		if ( Input.getKeyDown(KeyEvent.VK_D) )
			xV += speed;

		rigidbody.velocity = new Vector3(xV, yV, zV);

		// TODO do stuff here to describe behavior of a rectangle in the physics loop
	}
}
