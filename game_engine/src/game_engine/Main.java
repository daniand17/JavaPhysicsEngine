package game_engine;

import objects.PlayerController;
import objects.TestRect;

public class Main {

	public static void main(String[] args) {

		Debug.toggleDebugMode();

		if ( Debug.debugModeEnabled() )
			Vector2.test();

		new Engine(800, 600, "Game Engine Test");

		ObjectManager.instantiate(new PlayerController(), new Vector2(300, 300));
		//ObjectManager.instantiate(new TestRect(), new Vector2(600, 300));
	}
}