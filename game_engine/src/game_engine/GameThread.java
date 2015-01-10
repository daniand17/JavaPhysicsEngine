package game_engine;

import graphics.Display;
import physics.Rigidbody2D;

public class GameThread implements Runnable {

	private final Display display;

	private static double mtPeriod = 0;

	public GameThread(Display display) {
		this.display = display;
		display.setupWindow();
	}

	@Override
	public void run() {

		final double NANO_CONV = 1E-9d;

		double t = 0;
		double dt = 0.01;

		double currentTime = System.nanoTime() * NANO_CONV;
		double accumulator = 0.0;

		while (true) {

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

			update();

			// Used for interpolation
			double alpha = accumulator / dt;

			// Renders the game state
			display.render(alpha);

			double then = System.nanoTime() * NANO_CONV - now;

			mtPeriod = then * 1000;

			// Sleeps the thread for just a bit to save on CPU

			// TODO Commented this out in order to see the impact of changes on
			// the raw framerate
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

	public static double getMTInfo() {
		return mtPeriod;
	}

	private void update() {
		for (GameObject currEnt : ObjectManager.getAllObjects())
			currEnt.update();
	}

	private void fixedUpdate(double t, double dt) {

		// Do the physics updates for each game object
		for (GameObject obj : ObjectManager.getAllObjects())
			obj.physicsUpdate();

		// Update all the rigidbodies
		for (Rigidbody2D rb : ObjectManager.getPhysicsObjects())
			if ( rb != null )
				rb.updatePhysics(t, dt);

		// Resolve any collisions from this physics step
		for (GameObject obj : ObjectManager.getColliderObjects()) {
			if ( obj != null )
				obj.resolveCollisions(ObjectManager.getNearbyObjects(obj));
		}

		// At this point all collisions are resolved so we can kill the quadtree
		// and prep for the next frame
		ObjectManager.clearQuadtreeAndResetColliders();
	}
}
