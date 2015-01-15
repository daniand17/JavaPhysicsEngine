package game_engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

/**
 * The transform class represents a directed acyclic graph in its
 * implementation. This class is used to unify multiple game objects in a given
 * hierarchy, and provides access to locational data for those objects in the
 * game world.
 * 
 * @author Andrew
 *
 */
public class Transform extends Component {

	private double rotation; // The rotation of this transform
	private Vector2 position; // the position of this transform
	private List<Transform> children; // children of this transform

	public Transform() {
		this(new Vector2(0f, 0f));

	}

	/**
	 * Private constructor to initalize the fields to default values
	 * 
	 * @param pos
	 */
	private Transform(Vector2 pos) {
		setPosition(pos);
		rotation = 0;
		children = new LinkedList<Transform>();
	}

	/**
	 * This method adds a new child to this transform and returns the child for
	 * continued processing
	 * 
	 * @param newChild
	 *            the child to add
	 * @return the added child
	 */
	public Transform addChild(Transform newChild) {
		children.add(newChild);
		return newChild;
	}

	/**
	 * @return the number of children on this transform.
	 */
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

	/**
	 * Returns a vector which points down relative to transform space.
	 * 
	 * @return
	 */
	public Vector2 down() {
		return transformVector(Vector2.down());
	}

	/**
	 * Returns a vector which points to the right relative to transform space.
	 * 
	 * @return
	 */
	public Vector2 right() {
		return transformVector(Vector2.right());
	}

	/**
	 * Returns a vector which points left relative to transform space.
	 * 
	 * @return
	 */
	public Vector2 left() {
		return transformVector(Vector2.left());
	}

	/**
	 * This function takes a Vector2 and transforms it relative to the transform
	 * coordinate system.
	 * 
	 * @param vec
	 *            the Vector2 to transform.
	 * @return the transformed vector
	 */
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
