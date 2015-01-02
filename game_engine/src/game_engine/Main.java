package game_engine;

import objects.PlayerController;
import objects.TestRect;
import game_engine.Vector2;
public class Main {
	private static boolean debugFlag = false;
	public static void main(String[] args) {
		if (debugFlag) {
			Vector2.test();
		}
		new Engine(800, 600, "Game Engine Test");
		ObjectManager.instantiate(new PlayerController(), new Vector2(0, 0));
		ObjectManager.instantiate(new TestRect(), new Vector2(100, 0));
	}
}
