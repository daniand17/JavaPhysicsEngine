package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TestPlayer extends GameEntity {

	private float speed = 10f;

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

		rigidbody.velocity = new Vector3(rigidbody.velocity.x + xV, rigidbody.velocity.y + yV,
				rigidbody.velocity.z + zV);

		if ( transform.position.x < 0 )
			transform.position = new Vector3(800, transform.position.y, transform.position.z);
		else if ( transform.position.x > 800 )
			transform.position = new Vector3(transform.position.x % 800, transform.position.y,
					transform.position.z);

		if ( transform.position.y < 0 )
			transform.position = new Vector3(transform.position.x, 600, transform.position.z);

		else if ( transform.position.y > 600 )
			transform.position = new Vector3(transform.position.x, transform.position.y % 600,
					transform.position.z);

		// TODO do stuff here to describe behavior of a rectangle in the physics loop
	}
}
