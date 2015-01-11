package game_engine;

import objects.PlayerController;
import objects.TestRect;
import utility.Debug;
import utility.PerformanceAnalysis;

public class Main {

	public static void main(String[] args) {

		Debug.toggleDebugGizmos();
		Debug.toggleDebugMessages();

		PerformanceAnalysis.getNewTimerNumber("GameThread", 1000);
		PerformanceAnalysis.getNewTimerNumber("All Physics Update", 1000);
		PerformanceAnalysis.getNewTimerNumber("Resolve World Collisions", 1000);

		new Engine(1400, 900, "Game Engine Test");

		ObjectManager.instantiate(new PlayerController(), new Vector2(300, 300));
		ObjectManager.instantiate(new TestRect(), new Vector2(600, 300));

	}
}