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

	// Various system properties to take advantage of OpenGL and graphics acceleration
	static {
		System.setProperty("sun.java2d.transaccel", "True");
		// System.setProperty("sun.java2d.trace", "timestamp,log,count");
		System.setProperty("sun.java2d.opengl", "True");
		// System.setProperty("sun.java2d.d3d", "True");
		System.setProperty("sun.java2d.ddforcevram", "True");
	}

	private static final long serialVersionUID = 1L;
	private BufferStrategy strategy;
	private Paint backgroundGradient; // TODO put this in its own class w/ buffer strategy

	public static Dimension SIZE;

	public Display(Dimension dims) {
		SIZE = dims;
	}

	public void setupWindow() {

		// Uses a double buffer, and ignores repaint requests since we are handling the graphics
		// updates manually
		this.createBufferStrategy(2);
		strategy = this.getBufferStrategy();
		this.setIgnoreRepaint(true);

		// Sets the background of the game window
		backgroundGradient = new GradientPaint(0, 0, Color.gray, SIZE.width, SIZE.height,
				Color.lightGray.brighter());

		// Allows this component to get input from mouse and keyboard
		setFocusable(true);
	}

	public void render(double alpha) {
		Graphics2D g2d = (Graphics2D) strategy.getDrawGraphics();
		g2d.setPaint(backgroundGradient);
		g2d.fillRect(0, 0, (int) SIZE.width, SIZE.height);

		// TODO Set this to draw only objects that are visible
		for (GameEntity ent : ObjectManager.getObjects())
			if ( ent != null && ent.renderer != null )
				ent.renderer.renderObject(g2d, alpha);

		renderGUI(g2d);

		g2d.dispose();
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public void renderGUI(Graphics2D g2d) {
		int leftmargin = 12;
		g2d.setColor(Color.green.brighter());
		g2d.drawString("Objects on screen: " + ObjectManager.getObjects().size(), leftmargin, 20);
		g2d.drawString(GameThread.getMTInfo(), leftmargin, 40);

	}
}
