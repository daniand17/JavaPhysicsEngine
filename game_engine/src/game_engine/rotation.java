package game_engine;

public interface rotation {
	/*
	 * This is an interface describing the methods required for a rotation class. I don't know if we
	 * actually need an interface for a rotation class.
	 */
	Vector3 rotateX(Vector3 vector, float theta);

	Vector3 rotateY(Vector3 vector, float theta);

	Vector3 rotateZ(Vector3 vector, float theta);

	Vector3 complexRotate(Vector3 vector, float thetaX, float thetaY,
			float thetaZ);
}