package utility;

import game_engine.GameObject;
import game_engine.ObjectManager;
import game_engine.Vector2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import physics.Collider;

public class Debug {

	private static boolean debugGizmos = false;
	private static boolean debugMessages = false;

	private static File debugOutput;
	private static PrintWriter out;
	private static final String format = "%-10s>%s%n";
	public static final String NAME = "Debug";

	// A constant to call the generic files
	private final static String genFilename = "debugOut";
	private static String logName;

	private static int debugLogCount;

	private static Queue<Ray> rays = new LinkedList<Ray>();

	/**
	 * This method draws a ray on the screen given an origin and destination,
	 * and a color. This has the effect of drawing the ray starting at the
	 * origin, and ending it at the destination RELATIVE to the origin. A
	 * primary use of this method would be to display the velocity of a
	 * rigidbody given a transforms position and rigidbody velocity
	 * 
	 * @param origin
	 *            The point at which to start drawing the ray
	 * @param destination
	 *            the destination of the ray relative to the origin
	 * @param color
	 *            the color to draw the ray
	 */
	public static void drawRay(Vector2 origin, Vector2 destination, Color color) {
		rays.add(new Ray(origin, origin.add(destination), color));
	}

	/**
	 * This method draws a ray using the default color. Has the same behavior as
	 * the method in which color is specified.
	 * 
	 * @param origin
	 *            the origin of the ray
	 * @param destination
	 *            the destination of the ray relative to the origin.
	 */
	public static void drawRay(Vector2 origin, Vector2 destination) {
		drawRay(origin, destination, Color.blue);
	}

	public static void setupDebugOutput(String filename) {
		// Creates a generic debug output file
		logName = filename + ".txt";
		debugOutput = new File(logName);

		// Increments the number of debug logs currently open.
		debugLogCount++;

		try {
			out = new PrintWriter(debugOutput);
		} catch (FileNotFoundException e) {
			System.err.println("Could not access the debug output file! Debug "
					+ "messages not printing to file " + logName);
			return;
		}

		Debug.log(
				"NAME",
				"Remember to close the debugger with Debug.close()if you aren't \nreceiving output to a file when calling Debug.logF()!");
		Debug.log("NAME", "Also bear in mind that you can only write out to 1 file at a time.");
		Debug.log("NAME", "Debugger ready to accept input.");

	}

	public static void setupDebugOutput() {
		// Creates a generic debug output file
		logName = genFilename + debugLogCount + ".txt";
		debugOutput = new File(logName);

		// Increments the number of debug logs currently open.
		debugLogCount++;

		try {
			out = new PrintWriter(debugOutput);
		} catch (FileNotFoundException e) {
			System.err.println("Could not access the debug output file! Debug "
					+ "messages not printing to file " + logName);
			return;
		}
		Debug.log("NAME", "Remember to close the debugger with Debug.close()"
				+ "\nif you aren't receiving output to a file when calling Debug.logF!");
	}

	/**
	 * Simply prints the message to stdout. Calls System.out.println()
	 * 
	 * @param message
	 *            the message to print out.
	 */
	public static void log(String tag, String message) {

		if ( tag != null && message != null && debugMessages )
			System.out.printf(format, tag, message);
	}

	/**
	 * Prints the message to the file specified in the constructor. If no file
	 * was specified in the constructor, then it will be some generic debug
	 * output file name.
	 */
	public static void logF(String message) {

		if ( out == null )
			setupDebugOutput();

		if ( out != null && message != null ) {
			out.write(message + "\n");
			out.flush();
		}
		else
			System.err.println("Debug.LogF() not successful. Could not print to Debug Log: "
					+ logName);
	}

	public static void err(String tag, String message) {
		if ( tag != null && message != null && debugMessages )
			System.err.printf(format, tag, message);
	}

	/**
	 * Closes the printwriter and flushes the buffer. Call this method if you
	 * want your debug information saved to the file.
	 */
	public static void close() {
		if ( out != null )
			out.close();
	}

	/**
	 * Toggles the debugger to output debug messages or not. Default is off.
	 * Call this in your code if you want to turn the debug mode on, and call it
	 * again if you want to turn it off.
	 */
	public static void toggleDebugGizmos() {
		debugGizmos = !debugGizmos;
	}

	public static void toggleDebugMessages() {
		debugMessages = !debugMessages;
	}

	/**
	 * This method returns whether the engine is in debug mode. Generally called
	 * in a conditional statement to print out a debug message.
	 * 
	 * @return whether messages called using Debug.log() are enabled
	 */
	public static boolean debugMessagesEnabled() {
		return debugMessages;
	}

	/**
	 * Returns whether the gizmos in debug mode are enabled (ie display
	 * transforms, colliders etc)
	 * 
	 * @return whether gizmos are rendering
	 */
	public static boolean debugGizmosEnabled() {
		return debugGizmos;
	}

	/**
	 * Renders objects that wouldn't normally appear in game on screen such as
	 * colliders, transform positions etc
	 * 
	 * @param g2d
	 */
	public static void renderDebugGizmos(Graphics2D g2d) {

		// TODO separate the rendering into a separate class for rendering all
		// objects (maybe)
		// Render the collider of each object
		for (Collider obj : ObjectManager.getColliderObjects())
			obj.renderCollider(g2d);

		for (GameObject obj : ObjectManager.getAllObjects())
			obj.getTransform().renderTransform(g2d);

		g2d.setColor(Color.blue);
		while (!rays.isEmpty()) {
			Ray ray = rays.remove();
			g2d.drawLine((int) ray.start.x, (int) ray.start.y, (int) ray.end.x, (int) ray.end.y);
		}
	}
}

class Ray {

	public Vector2 start;
	public Vector2 end;
	public Color rayColor;

	public Ray(Vector2 start, Vector2 end, Color color) {
		this.start = start;
		this.end = end;
		this.rayColor = color;

	}

}
