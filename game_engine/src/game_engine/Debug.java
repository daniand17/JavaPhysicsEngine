package game_engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Debug {

	private static File debugOutput;
	private static PrintWriter out;
	private static final String format = "%-20s>%s%n";

	// A constant to call the generic files
	private final static String genFilename = "debugOut";
	private static String logName;

	private static int debugLogCount;

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
				"Debug",
				"Remember to close the debugger with Debug.close()if you aren't \nreceiving output to a file when calling Debug.logF()!");
		Debug.log("Debug", "Also bear in mind that you can only write out to 1 file at a time.");
		Debug.log("Debug", "Debugger ready to accept input.");

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
		Debug.log("Debug:", "Remember to close the debugger with Debug.close()"
				+ "\nif you aren't receiving output to a file when calling Debug.logF!");
	}

	/**
	 * Simply prints the message to stdout. Calls System.out.println()
	 * 
	 * @param msg
	 *            the message to print out.
	 */
	public static void log(String tag, String msg) {

		if ( tag != null && msg != null )
			System.out.printf(format, tag, msg);
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

	/**
	 * Closes the printwriter and flushes the buffer. Call this method if you
	 * want your debug information saved to the file.
	 */
	public static void close() {
		out.close();
	}
}
