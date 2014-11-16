package game_engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private final Engine game;

	public GameThread(Engine game) {
		this.game = game;
		// Lets panel get input from the keyboard
		setFocusable(true);
	}

	@Override
	public void run() {
		long lastUpdateTime = System.nanoTime();
		final double GAME_HERTZ = 30.0;
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 3;

		// This is the main game loop, it is super simple and not good, but I
		// will be improving it.
		while (true) {

			long currentTime = System.nanoTime();
			int updateCount = 0;
			/*
			 * while (currentTime - lastUpdateTime > TIME_BETWEEN_UPDATES &&
			 * updateCount < MAX_UPDATES_BEFORE_RENDER) {
			 * 
			 * }
			 */

			if ( game.getScreenFactory().getCurrentScreen() != null )
				// Update the screen logic
				game.getScreenFactory().getCurrentScreen().onUpdate();
			// Sleeps the thread for 10 ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if ( game.getScreenFactory().getCurrentScreen() != null )
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);

		repaint();
	}
}
