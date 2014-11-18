package game_engine;

public class Vector3 extends Vector {

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

	public void scale(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}
}
