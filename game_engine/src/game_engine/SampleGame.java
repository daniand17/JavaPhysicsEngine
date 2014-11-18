package game_engine;

public class SampleGame {

	private Engine game;

	public SampleGame() {
		game = new Engine(800, 600, "Untitled Game Engine");
	}

	public static void main(String[] args) {
		new SampleGame();
	}

}
