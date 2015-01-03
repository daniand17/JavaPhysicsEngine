package game_engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class Display extends Canvas {

	// Various system properties to take advantage of OpenGL and graphics
	// acceleration
	static {
		System.setProperty("sun.java2d.transaccel", "True");
		// System.setProperty("sun.java2d.trace", "timestamp,log,count");
		System.setProperty("sun.java2d.opengl", "True");
		// System.setProperty("sun.java2d.d3d", "True");
		System.setProperty("sun.java2d.ddforcevram", "True");
	}

	private static final long serialVersionUID = 1L;
	private BufferStrategy strategy;
	private Paint backgroundGradient;
	public static Dimension SIZE;

	public Display(Dimension dims) {
		SIZE = dims;
	}

	void setupWindow() {

		// Uses a double buffer, and ignores repaint requests since we are
		// handling the graphics
		this.createBufferStrategy(2);
		strategy = this.getBufferStrategy();
		this.setIgnoreRepaint(true);

		// Sets the background of the game window
		backgroundGradient = new GradientPaint(0, 0, Color.gray, SIZE.width, SIZE.height,
				Color.lightGray.brighter());

		// Allows this component to get input from mouse and keyboard
		setFocusable(true);
	}

	/**
	 * This function takes a float used for interpolation of graphics between
	 * two states to ensure smooth graphics rendering. Then it renders the
	 * current game state by first filling in the background, then rendering
	 * each game object. Finally, it renders the GUI.
	 * 
	 * @param alpha
	 *            the interpolation value
	 */
	void render(double alpha) {
		// Gets the graphics strategy and sets the background to the one defined
		// by backgroundGradient
		Graphics2D g2d = (Graphics2D) strategy.getDrawGraphics();
		g2d.setPaint(backgroundGradient);
		// Fills the rectangle corresponding to the background
		g2d.fillRect(0, 0, SIZE.width, SIZE.height);

		// TODO renders each game object by calling the renderer of that object
		// TODO (Andy) Set this to draw only objects that are visible
		for (GameObject ent : ObjectManager.getAllObjects())
			if ( ent != null && ent.getRenderer() != null )
				ent.getRenderer().renderObject(g2d, alpha);

		renderDebugGizmos(g2d, alpha);
		// Renders the GUI
		renderGUI(g2d);

		// Dispose of the old buffer strategy
		g2d.dispose();
		// Show the new strategy (ie swap the buffers)
		strategy.show();
		// Sync the buffers
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Renders the GUI
	 * 
	 * @param g2d
	 *            the graphics context to render to
	 */
	void renderGUI(Graphics2D g2d) {
		int leftmargin = 12;
		g2d.setColor(Color.green.brighter());
		g2d.drawString("Objects on screen: " + ObjectManager.getAllObjects().size(), leftmargin, 20);

		double amt = GameThread.getMTInfo();
		g2d.drawString("FPS: " + Utility.roundToTenth((1 / amt) * 1000), leftmargin, 40);
	}

	void renderDebugGizmos(Graphics2D g2d, double alpha) {

		for (GameObject obj : ObjectManager.getColliderObjects())
			obj.collider.renderCollider(g2d, alpha);
	}
}
