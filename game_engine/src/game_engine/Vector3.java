package game_engine;

import interfaces_abstracts.Vector;

public class Vector3 extends Vector {

	public static final Vector3 up = new Vector3(0f, 0f, 1f);
	public static final Vector3 right = new Vector3(1f, 0f, 0f);
	public static final Vector3 forward = new Vector3(0f, 1f, 0f);

	public float x;
	public float y;
	public float z;

	public Vector3() {
		this(0f, 0f, 0f);
	}

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(double x, double y, double z) {
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
	}

	public void scale(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}

	public Vector3 add(Vector3 vec) {
		return new Vector3(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}

	public Vector3 normalize() {
		float mag = this.magnitude();
		return new Vector3(x / mag, y / mag, z / mag);
	}

	public float magnitude() {

		return (float) Math.sqrt(x * x + y * y + z * z);
	}
}
