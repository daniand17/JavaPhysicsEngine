package game_engine;

import objects.PlayerController;


public class Main {

	public static void main(String[] args) {
		new Engine(800, 600, "Game Engine Test");
		ObjectManager.instantiate(new PlayerController(), new Vector3(0, 0, 0));
	}
}
