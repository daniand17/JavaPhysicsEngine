package game_engine;

import java.util.LinkedList;
import java.util.List;

public class Transform extends Component {

	private double rotation;
	public Vector2 position;
	private List<Transform> children;

	public Transform() {
		this(new Vector2(0f, 0f));

	}

	Transform(Vector2 pos) {
		position = pos;
		rotation = 0;
		children = new LinkedList<Transform>();
	}

	public Transform addChild(Transform newChild) {
		// Adds the child to the list of children
		children.add(newChild);
		// Returns the child in case we want to use it for other things after
		// instantiation
		return newChild;
	}

	public int getChildCount() {
		return children.size();
	}

	/**
	 * Return the double representation of the rotation of this transform.
	 * Positive CW.
	 * 
	 * @return the rotation
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Returns the double representation of the rotation of this transform in
	 * terms of degrees
	 * 
	 * @return
	 */
	public double getEulerRotation() {

		return rotation * 57.29577951;
	}

	/**
	 * Sets the rotation of this transform in radians. Positive CW.
	 * 
	 * @param rotation
	 *            the rotation to set
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
		this.rotation = this.rotation % (2 * Math.PI);
	}
}
