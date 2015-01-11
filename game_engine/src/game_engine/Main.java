package game_engine;

import objects.PlayerController;
import objects.TestRect;
import utility.Debug;

public class Main {

	public static void main(String[] args) {

		Debug.toggleDebugMode();

		new Engine(1400, 900, "Game Engine Test");

		ObjectManager.instantiate(new PlayerController(), new Vector2(300, 300));
		ObjectManager.instantiate(new TestRect(), new Vector2(600, 300));
		
	}
}