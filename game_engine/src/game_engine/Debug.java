package game_engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Debug {

	private File debugOutput;
	private PrintWriter out;

	// A constant to call the generic files
	private final String genFilename = "DBout";
	private String logName;

	private static int debugLogCount;

	/**
	 * Constructs a Debug object to send debug messages to a file specified by
	 * the filename.
	 * 
	 * @param filename
	 */
	public Debug(String filename) {
		logName = filename + ".txt";
		debugOutput = new File(logName);

		try {
			out = new PrintWriter(debugOutput);
		} catch (FileNotFoundException e) {
			System.err.println("Could not access the debug output file! Debug "
					+ "messages not printing to file " + logName);
		}
	}

	public Debug() {
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
		}
	}

	/**
	 * Simply prints the message to stdout. Calls System.out.println()
	 * 
	 * @param msg
	 *            the message to print out.
	 */
	public static void Log(String message) {
		System.out.println(message);
	}

	/**
	 * Prints the message to the file specified in the constructor. If no file
	 * was specified in the constructor, then it will be some generic debug
	 * output file name.
	 */
	public void LogF(String message) {

		if ( out != null && message != null )
			out.write(message + "\n");
		else
			System.err
					.println("Debug.LogF() not successful. Could not print to Debug Log: "
							+ logName);
	}

	/**
	 * Closes the printwriter and flushes the buffer. Call this method if you
	 * want your debug information saved to the file.
	 */
	public void closeLog() {
		out.close();
	}
}
