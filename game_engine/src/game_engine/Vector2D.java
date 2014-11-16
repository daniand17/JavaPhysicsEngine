package game_engine;

public class Vector2D extends Vector {

	// Fields
	public float x;
	public float y;

	/**
	 * Initializes the vector with a value of 0 for x and y.
	 */
	public Vector2D() {
		x = 0;
		y = 0;
	}

	/**
	 * Used to initialize the vector with initial values.
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void scale(float scalar) {
		x *= scalar;
		y *= scalar;
	}
}
