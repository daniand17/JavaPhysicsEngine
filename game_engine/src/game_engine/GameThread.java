package game_engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Graphics2D g2d;
	private GameScreen gameWindow;

	public GameThread(Engine game) {
		gameWindow = new GameScreen();
		// Lets panel get input from the keyboard
		setFocusable(true);
	}

	@Override
	public void run() {

		g2d = (Graphics2D) this.getGraphics();
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

			while (accumulator >= dt) {
				// Fixed update logic

				// Might need to do some sort of integrate(currentState, t, dt) here
				fixedUpdate(t, dt);
				t += dt;
				accumulator -= dt;
			}

			// Renders the game state
			repaint(); // TODO implement interpolation
		}
	}

	private void fixedUpdate(double t, double dt) {
		if ( gameWindow != null )
			// Update the screen logic
			for (PhysicsEntity ent : ObjectManager.getObjects())
				if ( ent != null )
					ent.updatePhysics(t, dt);

		gameWindow.fixedUpdate();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// TODO Set this to draw all objects that are visible
		if ( gameWindow != null )
			for (IEntity ent : ObjectManager.getObjects())
				if ( ent != null )
					ent.draw(g2d);
	}
}
