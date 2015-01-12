package utility;

import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalysis {

	private static final String NAME = "PerformanceAnalysis";

	private static List<Timer> timers = new ArrayList<Timer>();

	public static void startTimer(int number) {

		if ( number < timers.size() )
			timers.get(number).startTimer();
	}

	public static void stopTimer(int timerNumber) {

		if ( timerNumber < timers.size() )
			timers.get(timerNumber).stopTimer();

	}

	public static int getNewTimerNumber(String name, int cyclesToAverage) {
		Timer timer = new Timer(name, cyclesToAverage);
		timers.add(timer);
		Debug.log(NAME, name + " timer created: \nID: " + (timers.size() - 1) + "\n# Cycles: "
				+ cyclesToAverage);

		return timers.size() - 1;
	}
}

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
