package game_engine;

import objects.*;
import utility.Debug;
import utility.PerformanceAnalysis;

public class Main {

	public static void main(String[] args) {

		// Debug.toggleDebugGizmos();
		Debug.toggleDebugMessages();

		PerformanceAnalysis.getNewTimerNumber("GameThread", 1000);
		PerformanceAnalysis.getNewTimerNumber("All Physics Update", 1000);
		PerformanceAnalysis.getNewTimerNumber("Resolve World Collisions", 1000);

		new Engine(1200, 800, "Game Engine Test");
		// TODO: Is there a preferred way to set initial velocities on a specific object from the main script?
		GameObject player = ObjectManager.instantiate(new PlayerController(), new Vector2(400, 300));
		ObjectManager.instantiate(new GravityPointObject(), new Vector2(400, 400));
		// FIXME: Note that the method below only works every other time the program runs. wtf? (Comment out initial velocity
		// in PlayerController to test).
		//player.start(); // If this isn't called, nothing is attached to playerController out here
		//player.getRigidbody().setVelocity(new Vector2(100d, 0d));
		
/*
		for (int i = 0; i < 5; i++) {
			ObjectManager.instantiate(new TestRect(),
					new Vector2(Math.random() * 1400, Math.random() * 900));
		}
*/
	}
}