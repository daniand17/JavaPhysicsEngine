package utility;

public class PerformanceAnalyzer {

	private static double[] diff = new double[5];
	private static double[] startTimes = new double[5];
	private static int numCycles = 1000;
	private static int[] averageOverCycles = { numCycles, numCycles, numCycles, numCycles,
			numCycles };
	private static int[] currentCycleNumber = new int[5];

	private static double NANO_TO_MS = 1000000;

	private static int timerCount = 0;

	public static void startTimer(int number) {
		if ( number < diff.length && currentCycleNumber[number] < averageOverCycles[number] ) {
			startTimes[number] = System.nanoTime();
			currentCycleNumber[number]++;
		}
	}

	public static double stopTimer(int timerNumber) {

		double result = -1;
		diff[timerNumber] += (System.nanoTime() - startTimes[timerNumber]);
		if ( currentCycleNumber[timerNumber] == averageOverCycles[timerNumber] ) {
			result = diff[timerNumber] / (NANO_TO_MS * averageOverCycles[timerNumber]);
			diff[timerNumber] = 0;
			currentCycleNumber[timerNumber] = 0;
		}

		return result;

	}

	public static int getNewTimerNumber() {
		int newNum = timerCount;
		timerCount++;

		return newNum;
	}

}
