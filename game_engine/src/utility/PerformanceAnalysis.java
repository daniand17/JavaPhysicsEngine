package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains timers for use in rough analysis of runtime performance of various
 * portions of code by timing their execution
 * 
 * @author andrew
 *
 */
public class PerformanceAnalysis {

	private static final String NAME = "PerformanceAnalysis";

	// The list of timers
	private static List<Timer> timers = new ArrayList<Timer>();

	/**
	 * Starts the timer given the id number of the timer
	 * 
	 * @param number
	 *            the id number of the timer
	 */
	public static void startTimer(int number) {

		if ( number < timers.size() )
			timers.get(number).startTimer();
	}

	/**
	 * Stops the timer given by the number
	 * 
	 * @param timerNumber
	 *            the id of the timer
	 */
	public static void stopTimer(int timerNumber) {

		if ( timerNumber < timers.size() )
			timers.get(timerNumber).stopTimer();

	}

	/**
	 * Creates a new timer and returns the ID of that timer.
	 * 
	 * @param name
	 *            the name used to identify this timer when it prints to the
	 *            console
	 * @param cyclesToAverage
	 *            the number of times the timer should be started and stopped
	 *            before printing to the console
	 * @return the ID of the timer
	 */
	public static int getNewTimerNumber(String name, int cyclesToAverage) {
		Timer timer = new Timer(name, cyclesToAverage);
		timers.add(timer);
		Debug.log(NAME, name + " timer created: \nID: " + (timers.size() - 1) + "\n# Cycles: "
				+ cyclesToAverage);

		return timers.size() - 1;
	}
}

/**
 * This class represents a timer and provides methods for starting and stopping,
 * and averaging execution time between starts and stops
 * 
 * @author andrew
 *
 */
class Timer {

	private static final int NANO_TO_MS = 1000000;
	private int cyclesToAverage = 0;
	private int currentCycles = 0;

	private double lastStartTime = 0;
	private double runningDiff = 0;

	private String name = "";

	public Timer(String name, int cycles) {
		cyclesToAverage = cycles;
		this.name = name;
	}

	public void stopTimer() {
		runningDiff += System.nanoTime() - lastStartTime;
		currentCycles++;

		if ( currentCycles == cyclesToAverage ) {
			currentCycles = 0;
			Debug.log("Timer: " + name, "\n" + runningDiff
					/ ((double) NANO_TO_MS * cyclesToAverage) + " ms");
			runningDiff = 0;
		}
	}

	public void startTimer() {
		lastStartTime = System.nanoTime();
	}

}
