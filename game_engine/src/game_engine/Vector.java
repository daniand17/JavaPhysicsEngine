package game_engine;

public abstract class Vector {

	/**
	 * Defines how each type of vector is multiplied by a scalar. Implementation is dependent on the
	 * dimension of the vector-thus Vector2D and Vector3D implement this a bit differently.
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
	public static float distance(Vector2D first, Vector2D second) {

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
	public static float distance(Vector3D first, Vector3D second) {

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
	public static float magnitude(Vector2D vec) {
		float mag = vec.x * vec.x + vec.y * vec.y;
		return (float) Math.sqrt(mag);
	}

	/**
	 * Finds the magnitude of a 3D vector
	 * 
	 * @param vec
	 * @return
	 */
	public static float magnitude(Vector3D vec) {
		float mag = vec.x * vec.x + vec.y * vec.y + vec.z * vec.z;
		return (float) Math.sqrt(mag);
	}
}
