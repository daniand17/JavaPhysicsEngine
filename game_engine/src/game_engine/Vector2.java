package game_engine;

import abstracts.Vector;

public class Vector2 extends Vector {

	public static final Vector2 forward = new Vector2(0f, 1f);
	public static final Vector2 right = new Vector2(1f, 0f);

	// Fields
	public float x;
	public float y;

	/**
	 * Initializes the vector with a value of 0 for x and y.
	 */
	public Vector2() {
		x = 0;
		y = 0;
	}

	/**
	 * Used to initialize the vector with initial values.
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void scale(float scalar) {
		x *= scalar;
		y *= scalar;
	}

}
