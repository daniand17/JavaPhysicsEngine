package game_engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public abstract class Collider extends Component {

	public Shape collider;
	// Whether this collider should is a trigger instead of a physical object
	public boolean isTrigger = false;
	boolean collisionsResolvedThisFrame = false;
	// The 2 dimensional size of this collider
	public Vector2 size;
	// Position relative to the transform of the attached GameObject
	private Vector2 relativePosition = new Vector2(0f, 0f);

	/**
	 * This method is used to get the area of the collider in world space.
	 * 
	 * @return
	 */
	Area getBoundedArea() {
		// Update this colliders position
		Vector2 pos = getPositionInWorldSpace();
		Vector2 relPos = getRelativePosition();
		// The rotation transformation
		AffineTransform transf = AffineTransform.getRotateInstance(getTransform().getRotation(),
				-relPos.x, -relPos.y);
		// TODO may need to generalize this further-need to think about the
		// pivot point

		// The translation transformation
		AffineTransform translationMatrix = AffineTransform.getTranslateInstance(pos.x, pos.y);
		// Create the transformed shape from the collider
		Shape temp = transf.createTransformedShape(collider);
		// Translate the new shape using the translation matrix
		temp = translationMatrix.createTransformedShape(temp);
		// Get the area of the shape to send for collision detection
		return new Area(temp);
	}

	/**
	 * Package-access method that gets the coordinates of this collider adjusted
	 * for the transform of the GameObject it is attached to. If the transform
	 * is a child of another transform, this does not account for that, only the
	 * local space of that transform. DOES NOT return a world space currently.
	 * This will be generally useful for collision detection.
	 * 
	 * @return the Vector2 representing the position of this collider.
	 */
	Vector2 getPosition() {

		return relativePosition.add(getTransform().position);
	}

	/**
	 * Gets the coordinates of this collider relative to the transform of the
	 * GameObject it is attached to. For example, if the collider is centered on
	 * the GameObject, then this would return a Vector2 equivalent to the zero
	 * vector.
	 * 
	 * @return the position of this collider.
	 */
	public Vector2 getRelativePosition() {
		return relativePosition;
	}

	/**
	 * Sets the coordinates of this collider relative to its attached transform
	 * given a Vector2
	 * 
	 * @param newRelativePosition
	 *            the new relative position of this collider
	 */
	public void setRelativePosition(Vector2 newRelativePosition) {
		if ( newRelativePosition != null )
			this.relativePosition = newRelativePosition;
	}

	Vector2 getPositionInWorldSpace() {
		// TODO Implement this method once we figure out how to do the
		// transforms and conversion from transform local space to world space.
		// Not crucial yet but this should eventually be the defacto method used
		// for collision detection once transforms are figured out.

		return getPosition();
	}

	void renderCollider(Graphics2D g2d, double alpha) {
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke());
		g2d.draw(this.getBoundedArea());
	}

}
