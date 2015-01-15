package graphics;

import game_engine.GameObject;

/**
 * This class represents an orthographic camera used to view the game world and
 * adjust the position of the viewport in game. This is not currently being used
 * but is in the game world as an object
 * 
 * @author andrew
 *
 */
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
