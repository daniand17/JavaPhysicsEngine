package utility;

/**
 * This class contains (or will contain) various useful functions that don't
 * really fit anywhere else.
 * 
 * @author andrew
 *
 */
public class Utility {

	/**
	 * Rounds a number to the nearest 1000th
	 * 
	 * @param num
	 *            the number to round
	 * @return the rounded number
	 */
	public static float roundToThousandth(double num) {

		return ((int) (num * 1000)) / 1000f;
	}

	/**
	 * Rounds a number to the nearest tenth.
	 * 
	 * @param num
	 *            the number to round
	 * @return the rounded number
	 */
	public static float roundToTenth(double num) {
		return ((int) (num * 10)) / 10f;
	}
}
