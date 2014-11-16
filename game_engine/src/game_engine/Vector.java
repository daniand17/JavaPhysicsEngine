package game_engine;

public abstract class Vector {

	/**
	 * Defines how each type of vector is multiplied by a scalar. Implementation
	 * is dependent on the dimension of the vector-thus Vector2D and Vector3D
	 * implement this a bit differently.
	 * 
	 * @param scalar
	 */
	public abstract void scale(float scalar);

	/**
	 * Takes 2 2D vectors and finds their distance
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static float distance(Vector2 first, Vector2 second) {

		float dist = (second.x - first.x) * (second.x - first.x)
				+ (second.y - first.y) * (second.y - first.y);

		return (float) Math.sqrt(dist);
	}

	/**
	 * Takes 2 3D vectors and finds their distance
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static float distance(Vector3 first, Vector3 second) {

		float dist = (second.x - first.x) * (second.x - first.x)
				+ (second.y - first.y) * (second.y - first.y)
				+ (second.z - first.z) * (second.z - first.z);

		return (float) Math.sqrt(dist);
	}

	/**
	 * Finds the magnitude of a 2D vector.
	 * 
	 * @param vec
	 * @return
	 */
	public static float magnitude(Vector2 vec) {
		float mag = vec.x * vec.x + vec.y * vec.y;
		return (float) Math.sqrt(mag);
	}

	/**
	 * Finds the magnitude of a 3D vector
	 * 
	 * @param vec
	 * @return
	 */
	public static float magnitude(Vector3 vec) {
		float mag = vec.x * vec.x + vec.y * vec.y + vec.z * vec.z;
		return (float) Math.sqrt(mag);
	}

	/**
	 * Finds the dot product of two Vector2s.
	 * 
	 * @param vec1
	 *            the first vector
	 * @param vec2
	 *            the second vector
	 * @return the dot product of vec1 and vec2
	 */
	public static float dot(Vector2 vec1, Vector2 vec2) {
		return vec1.x * vec2.x + vec1.y * vec2.y;
	}

	/**
	 * Finds the dot product of two Vector3s
	 * 
	 * @param vec1
	 *            the first vector
	 * @param vec2
	 *            the second vector
	 * @return the dot product of vec1 and vec2
	 */
	public static float dot(Vector3 vec1, Vector3 vec2) {
		return vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
	}

	public static Vector2 rotate(Vector2 vec, float theta) {

		Vector2 temp = vec;
		// Clockwise rotation
		if ( theta > 0 ) {
			temp.x = (float) Math.cos(theta) * vec.x - (float) Math.sin(theta)
					* vec.y;

			temp.y = (float) Math.sin(theta) * vec.x - (float) Math.cos(theta)
					* vec.y;
		}
		// Counterclockwise rotation
		else if ( theta < 0 ) {
			theta *= -1;
			temp.x = (float) Math.cos(theta) * vec.x + (float) Math.sin(theta)
					* vec.y;

			temp.y = (float) -Math.sin(theta) * vec.x + (float) Math.cos(theta)
					* vec.y;

		}
		return temp;
	}
}
