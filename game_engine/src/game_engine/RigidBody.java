package game_engine;

public class RigidBody {
	public Quaternion rotation;

	public Vector3 velocity;
	public float mass;

	public RigidBody() {
		this(0f, 0f, 0f);
		mass = 5f;
	}

	public RigidBody(float xvel, float yvel, float zvel) {
		velocity = new Vector3(xvel, yvel, zvel);
	}
}
