package game_engine;

public abstract class GameEntity implements IEntity {

	public Transform transform;

	public GameEntity() {
		transform = new Transform(new Vector3(0f, 0f, 0f));
	}

	public GameEntity(float x, float y, float z) {
		transform = new Transform(new Vector3(x, y, z));
	}

	public void fixedUpdate() {
	}
}
