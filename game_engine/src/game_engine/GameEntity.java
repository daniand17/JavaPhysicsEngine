package game_engine;

public abstract class GameEntity implements IEntity {

	private Vector2 position;

	public GameEntity(int x, int y) {
		position.x = x;
		position.y = y;
	}

	public void setPosition(Vector2 pos) {
		position = pos;
	}

	public Vector2 getPosition() {
		return position;
	}
}
