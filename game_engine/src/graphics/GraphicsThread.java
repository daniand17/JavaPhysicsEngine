package graphics;

import game_engine.GameObject;
import game_engine.ObjectManager;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import utility.Debug;

public class GraphicsThread extends Canvas implements Runnable {

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
	// The background displayed
	private Paint backgroundGradient;
	// The graphics context
	private Graphics2D g2d;
	// The various rendering hints to use (anti-aliasing etc)
	private RenderingHints renderingHints;
	// The size of the window
	public static Dimension SIZE;

	public GraphicsThread(Dimension dims) {
		SIZE = dims;
	}

	/**
	 * This function should be called only by the Engine class to initialize
	 * this thread for rendering
	 */
	public void setupWindow() {

		setBounds(0, 0, SIZE.width, SIZE.height);
		// Uses a double buffer, and ignores repaint requests since we are
		// handling the graphics
		this.createBufferStrategy(2);
		strategy = this.getBufferStrategy();
		// We don't want to use the repaint function of the graphics context
		// since we have a buffer strategy
		this.setIgnoreRepaint(true);

		// Sets the background of the game window
		backgroundGradient = new GradientPaint(0, 0, Color.gray, SIZE.width, SIZE.height,
				Color.lightGray.brighter());

		g2d = (Graphics2D) strategy.getDrawGraphics();

		// Sets up anti-aliasing
		renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Sets up quality rendering
		RenderingHints qualitySetting = new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		// Sets up interpolation of colors and pixels
		RenderingHints interpolation = new RenderingHints(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		// Sets up the quality of the color rendering
		RenderingHints colorRendering = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);

		renderingHints.add(qualitySetting);
		renderingHints.add(interpolation);
		renderingHints.add(colorRendering);

		// Allows this component to get input from mouse and keyboard
		setFocusable(true);
	}

	/**
	 * This method is to be used only when the rendering loop is run separately
	 * from the game logic update loop.
	 */
	public void renderGraphics() {
		// Get the current graphics context
		g2d = (Graphics2D) strategy.getDrawGraphics();
		// Add the rendering hints
		g2d.setRenderingHints(renderingHints);
		// set up the background to clear away what was just rendered
		g2d.setPaint(backgroundGradient);
		// Fills the rectangle corresponding to the background
		g2d.fillRect(0, 0, SIZE.width, SIZE.height);
		// TODO (Andy) Set this to draw only objects that are visible
		for (GameObject ent : ObjectManager.getAllObjects()) {
			if ( ent != null && ent.getRenderer() != null ) {
				ent.getRenderer().renderObject(g2d);
			}
		}

		if ( Debug.debugGizmosEnabled() )
			Debug.renderDebugGizmos(g2d);

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

		// Call the OnGUI function for each game object
		for (GameObject obj : ObjectManager.getAllObjects())
			obj.onGUI(g2d);
	}

	@Override
	/**
	 * The implmentation of the runnable interface which is the essence of the rendering thread.
	 */
	public void run() {

		double now;
		double after;
		double diff;
		double hz = 120; // How many rendering updates we want.
		double framesPerSec; // The desired FPS

		while (true) {
			now = System.nanoTime();
			renderGraphics();
			after = System.nanoTime();

			diff = (after - now) / 1000000;

			framesPerSec = 1000 / hz;

			// Sleeps the thread to maintain ~ 120fps
			if ( framesPerSec > diff )
				try {
					Thread.sleep((long) (framesPerSec - diff));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}
