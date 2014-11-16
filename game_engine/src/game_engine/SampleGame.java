package game_engine;

public class SampleGame {

	private Engine game;

	public SampleGame() {
		game = new Engine(800, 600, "Untitled Game Engine");
		game.getScreenFactory().showScreen(
				new SampleGameScreen(game.getScreenFactory()));
	}

	public static void main(String[] args) {
		new SampleGame();
	}

}
