package game_engine;

import physics.Collider;
import physics.Rigidbody2D;
import utility.PerformanceAnalysis;

/**
 * This class implements the Runnable interface and can therefore be run in a
 * separate thread. This thread is used to update all game logic, instantiate
 * objects, and simulate physics.
 * 
 * @author andrew
 *
 */
public class GameThread implements Runnable {

	// How long the game updates took, used to sleep the thread to avoid using
	// all the CPU power when its not needed
	private static double mtPeriod = 0;

	/**
	 * The public constructor to create this game thread.
	 */
	public GameThread() {

	}

	@Override
	/**
	 * This function contains all the logic necessary to run the thread. This is the implementation of the runnable interface.
	 */
	public void run() {

		// Conversion from nano to seconds
		final double NANO_CONV = 1E-9d;

		// The current loop time
		double t = 0;
		// Size of the physics step
		double dt = 0.01;

		double currentTime = System.nanoTime() * NANO_CONV;
		// Used to determine how many physics steps need to be taken
		double accumulator = 0.0;

		while (true) {

			// Timing the game loop for debug purposes
			PerformanceAnalysis.startTimer(0);

			double now = System.nanoTime() * NANO_CONV;
			double frameTime = now - currentTime;

			if ( frameTime > 0.25 )
				frameTime = 0.25;

			currentTime = now;
			accumulator += frameTime;

			// Take all objects that were instantiated this frame and sort them
			// according to their components for various update loops
			ObjectManager.sortObjectsByComponents();

			// Do the physics updates
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

	/**
	 * This method does all the game logic updates for game objects. Calls the
	 * update() methods for each implementing class.
	 */
	private void update() {
		for (GameObject currEnt : ObjectManager.getAllObjects())
			currEnt.update();
	}

	/**
	 * This function does all the physics updates and also calls the
	 * physicsUpdate() method in each implementing class.
	 * 
	 * @param t
	 *            the current time
	 * @param dt
	 *            the timestep
	 */
	private void fixedUpdate(double t, double dt) {

		PerformanceAnalysis.startTimer(1);
		// Do the physics updates for each game object to get input info
		for (GameObject obj : ObjectManager.getAllObjects())
			obj.physicsUpdate();

		// Update rigidbody physics
		for (Rigidbody2D rb : ObjectManager.getPhysicsObjects()) {
			if ( rb != null )
				rb.updateRigidbodyPhysics(t, dt);
		}
		// The timer to see how long collisions are taking
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
