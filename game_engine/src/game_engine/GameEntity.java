package game_engine;

public abstract class GameEntity implements IEntity {

	protected Vector2 position;

	public GameEntity() {
		position = new Vector2(0f, 0f);
	}

	public GameEntity(int x, int y) {
		position = new Vector2(x, y);
	}

	public void setPosition(Vector2 pos) {
		position = pos;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public void fixedUpdate() {
	}
}
