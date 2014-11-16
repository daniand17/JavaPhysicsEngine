package game_engine;

public class ScreenFactory {

	private final Engine game;
	private Screen screen;

	public ScreenFactory(Engine game) {
		this.game = game;
	}

	public void showScreen(Screen screen) {
		this.screen = screen;
		this.screen.onCreate();
	}

	public Screen getCurrentScreen() {
		return screen;
	}

	public Engine getGame() {
		return game;
	}
}
