package game_engine;

public class Utility {

	/**
	 * Rounds a number to the nearest 1000th
	 * 
	 * @param num
	 *            the number to round
	 * @return the rounded number
	 */
	public static float roundToThousandth(double num) {

		return (float) ((int) (num * 1000)) / 1000f;
	}

	/**
	 * Rounds a number to the nearest tenth.
	 * 
	 * @param num
	 *            the number to round
	 * @return the rounded number
	 */
	public static float roundToTenth(double num) {
		return (float) ((int) (num * 10)) / 10f;
	}
}
