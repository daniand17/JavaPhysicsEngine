package game_engine;

import objects.GravityPointObject;
import objects.PlayerController;
import utility.Debug;
import utility.PerformanceAnalysis;

/**
 * This class is used to simulate a basic game world, initialize the engine, and
 * instantiate some objects.
 * 
 * @author Andrew
 *
 */
public class Main {

	public static void main(String[] args) {

		// Whether we want to see other debug gizmos such as transforms,
		// colliders etc
		Debug.toggleDebugGizmos();
		// Whether we want to see debug messages in the console
		Debug.toggleDebugMessages();

		// Create some timers for evaluating basic runtime performance of
		// various code
		PerformanceAnalysis.getNewTimerNumber("GameThread", 1000);
		PerformanceAnalysis.getNewTimerNumber("All Physics Update", 1000);
		PerformanceAnalysis.getNewTimerNumber("Resolve World Collisions", 1000);

		// Initialize the engine and starts the rendering and logic threads
		new Engine(1200, 800, "Game Engine Test");
		// Instantiate some test objects
		ObjectManager.instantiate(new PlayerController(), new Vector2(400, 300));
		ObjectManager.instantiate(new GravityPointObject(), new Vector2(400, 500));
	}
}