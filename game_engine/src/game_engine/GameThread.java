package game_engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private final Engine game;

	private Graphics2D g2d;
	private GameScreen gameWindow;

	public GameThread(Engine game) {
		this.game = game;
		gameWindow = new GameScreen(game);
		// Lets panel get input from the keyboard
		setFocusable(true);
	}

	@Override
	public void run() {

		g2d = (Graphics2D) this.getGraphics();
		long lastUpdateTime = System.nanoTime();
		final double GAME_HERTZ = 60.0;
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		final double UPDATE_PERIOD = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 3;

		// This is the main game loop, it is super simple and not good, but I
		// will be improving it.
		while (true) {

			double now = System.nanoTime();
			int updateCount = 0;

			while (now - lastUpdateTime > UPDATE_PERIOD && updateCount < MAX_UPDATES_BEFORE_RENDER) {
				// Fixed update logic
				fixedUpdate();
				lastUpdateTime += UPDATE_PERIOD;
				updateCount++;

			}

			// Renders the game state
			repaint(); // TODO implement interpolation

		}
	}

	private void fixedUpdate() {
		if ( gameWindow != null )
			// Update the screen logic
			gameWindow.fixedUpdate();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// TODO Set this to draw all objects that are visible
		if ( gameWindow != null )
			gameWindow.onDraw(g2d);
	}
}
