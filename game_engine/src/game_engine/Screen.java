package game_engine;

import java.awt.Graphics2D;

public abstract class Screen {

	private final ScreenFactory screenFactory;

	public Screen(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	/**
	 * When a new screen is created, calls this method.
	 */
	public abstract void onCreate();

	/**
	 * Calls this method every update cycle
	 */
	public abstract void onUpdate();

	/**
	 * Used to draw to the screen.
	 * 
	 * @param g2d
	 *            the 2D graphics object
	 */
	public abstract void onDraw(Graphics2D g2d);

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}
}
