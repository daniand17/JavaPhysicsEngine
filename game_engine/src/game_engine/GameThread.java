package game_engine;

import physics.Collider;
import physics.Rigidbody2D;
import utility.PerformanceAnalysis;

public class GameThread implements Runnable {

	private static double mtPeriod = 0;

	public GameThread() {

	}

	@Override
	public void run() {

		final double NANO_CONV = 1E-9d;

		double t = 0;
		double dt = 0.01;

		double currentTime = System.nanoTime() * NANO_CONV;
		double accumulator = 0.0;

		while (true) {

			PerformanceAnalysis.startTimer(0);

			double now = System.nanoTime() * NANO_CONV;
			double frameTime = now - currentTime;

			if ( frameTime > 0.25 )
				frameTime = 0.25;

			currentTime = now;
			accumulator += frameTime;

			ObjectManager.sortObjectsByComponents();

			while (accumulator >= dt) {

				fixedUpdate(t, dt);
				t += dt;
				accumulator -= dt;
			}

			// Call the update methods for each game object
			update();

			double then = System.nanoTime() * NANO_CONV - now;

			mtPeriod = then * 1000;

			PerformanceAnalysis.stopTimer(0);

			// Sleeps the thread for just a bit to save on CPU
			if ( mtPeriod < 10 )
				try {
					Thread.sleep((long) (12 - mtPeriod));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			// Updates the length the main thread took for info purposes
			then = System.nanoTime() * NANO_CONV - now;
			mtPeriod = then * 1000;

		}
	}

	private void update() {
		for (GameObject currEnt : ObjectManager.getAllObjects())
			currEnt.update();
	}

	private void fixedUpdate(double t, double dt) {

		PerformanceAnalysis.startTimer(1);
		// Do the physics updates for each game object
		for (GameObject obj : ObjectManager.getAllObjects())
			obj.physicsUpdate();
		// Update all the rigidbodies
		for (Rigidbody2D rb : ObjectManager.getPhysicsObjects())
			if ( rb != null )
				rb.updateRigidbodyPhysics(t, dt);

		PerformanceAnalysis.startTimer(2);
		// Resolve any collisions from this physics step
		for (Collider obj : ObjectManager.getColliderObjects())
			if ( obj != null )
				obj.resolveCollisions(ObjectManager.getNearbyObjects(obj));
		PerformanceAnalysis.stopTimer(2);

		// At this point all collisions are resolved so we can kill the quadtree
		// and prep for the next frame
		ObjectManager.clearQuadtreeAndResetColliders();
		PerformanceAnalysis.stopTimer(1);
	}
}
