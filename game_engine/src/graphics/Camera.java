package graphics;

import game_engine.GameObject;

public class Camera extends GameObject {

	public static final Camera main = new Camera();

	private double orthographicSize = 5;

	/**
	 * @return the orthographicSize
	 */
	public double getOrthographicSize() {
		return orthographicSize;
	}

	/**
	 * @param orthographicSize
	 *            the orthographicSize to set
	 */
	public void setOrthographicSize(double orthographicSize) {
		this.orthographicSize = orthographicSize;
	}

}
