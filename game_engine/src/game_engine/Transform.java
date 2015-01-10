package game_engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class Transform extends Component {

	private double rotation;
	private Vector2 position;
	private List<Transform> children;

	public Transform() {
		this(new Vector2(0f, 0f));

	}

	Transform(Vector2 pos) {
		setPosition(pos);
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

	/**
	 * Returns a Vector2 which points up in the transform space. If the
	 * transform does not have any rotation then this is equivalent to
	 * Vector2.up()
	 * 
	 * @return
	 */
	public Vector2 up() {
		return transformVector(Vector2.up());
	}

	public Vector2 down() {
		return transformVector(Vector2.down());
	}

	public Vector2 right() {
		return transformVector(Vector2.right());
	}

	public Vector2 left() {
		return transformVector(Vector2.left());
	}

	private Vector2 transformVector(Vector2 vec) {
		double cs = Math.cos(rotation);
		double sn = Math.sin(rotation);
		return new Vector2(vec.x * cs - vec.y * sn, vec.x * sn + vec.y * cs);

	}

	/**
	 * This method is called by the Debug class to render the transform if Debug
	 * mode is enabled. This method should not be called by any implementing
	 * class as it is already called automatically in debug mode.
	 * 
	 * @param g2d
	 */
	public void renderTransform(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.setStroke(new BasicStroke(BasicStroke.JOIN_BEVEL));
		g2d.drawString("x", (int) getPosition().x, (int) getPosition().y);
	}

	/**
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
