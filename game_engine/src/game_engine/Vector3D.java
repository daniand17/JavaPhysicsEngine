package game_engine;

public class Vector3D extends Vector {

	public float x;
	public float y;
	public float z;

	public void scale(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}
}
