package game_engine;

public interface rotation {
	/*
	 * This is an interface describing the methods required for a rotation class. I don't know if we
	 * actually need an interface for a rotation class.
	 */
	Vector3D rotateX(Vector3D vector, float theta);

	Vector3D rotateY(Vector3D vector, float theta);

	Vector3D rotateZ(Vector3D vector, float theta);

	Vector3D complexRotate(Vector3D vector, float thetaX, float thetaY,
			float thetaZ);
}