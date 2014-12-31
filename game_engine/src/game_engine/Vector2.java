package game_engine;

import interfaces_abstracts.Vector;

public class Vector2 implements Vector {

	// Fields
	public double x;
	public double y;

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
	 * @param d
	 * @param e
	 */
	public Vector2(double d, double e) {
		this.x = d;
		this.y = e;
	}

	public Vector2 add(Vector2 otherVector) {
		return new Vector2(this.x + otherVector.x, this.y + otherVector.y);
	}

	@Override
	public Vector2 scale(double scalar) {
		return new Vector2(scalar * x, scalar * y);
	}

	/**
	 * Returns the string representation of this vector for debugging purposes
	 */
	public String toString() {
		return "Vector2: (" + Utility.roundToThousandth(x) + ", " + Utility.roundToThousandth(y) + ")";
	}

}
