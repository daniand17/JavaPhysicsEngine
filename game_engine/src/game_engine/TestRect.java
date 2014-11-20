package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TestRect extends GameEntity {

	private float speed = 1f;

	public TestRect() {
		this(0, 0, 0);
	}

	public TestRect(int x, int y, int z) {
		super(x, y, z);
		rigidbody = new RigidBody();
		if ( transform == null )
			Debug.log("Did you call the super() constructor in" + this.getClass());
	}

	@Override
	public void render(Graphics2D g2d, double alpha) {
		Color temp = g2d.getColor();
		g2d.setColor(Color.blue);

		double interpX = transform.position.x * alpha + prevPos.x * (1.0f - alpha);
		double interpY = transform.position.y * alpha + prevPos.y * (1.0 - alpha);
		// double interpZ = transform.position.z * alpha + prevPos.z * (1.0 - alpha);
		g2d.fillRect((int) Math.round(interpX), (int) Math.round(interpY), 64, 64);
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
