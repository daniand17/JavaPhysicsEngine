package game_engine;

public class GameThread implements Runnable {

	private final Display display;

	public GameThread(Display display) {
		this.display = display;
		display.setupWindow();
	}

	@Override
	public void run() {

		final double NANO_CONV = 1000000000;

		double t = 0;
		double dt = 0.01;

		double currentTime = System.nanoTime() / NANO_CONV;
		double accumulator = 0.0;

		while (true) {

			double now = System.nanoTime() / NANO_CONV;
			double frameTime = now - currentTime;

			if ( frameTime > 0.25 )
				frameTime = 0.25;

			currentTime = now;
			accumulator += frameTime;

			ObjectManager.initializeStartObjects();

			while (accumulator >= dt) {

				// Might need to do some sort of integrate(currentState, t, dt) here
				fixedUpdate(t, dt);
				t += dt;
				accumulator -= dt;
			}

			update();

			// Used for interpolation
			double alpha = accumulator / dt;

			// Renders the game state
			display.render(alpha);

			double then = System.nanoTime() / NANO_CONV - now;

			System.out.println(then * 1000);
		}
	}

	private void update() {
		for (GameEntity currEnt : ObjectManager.getObjects())
			currEnt.update();
	}

	private void fixedUpdate(double t, double dt) {
		// Update the screen logic
		for (PhysicsEntity ent : ObjectManager.getPhysicsObjects())
			if ( ent != null )
				ent.updatePhysics(t, dt);
	}
}
