package physics;

import game_engine.Component;
import game_engine.Transform;
import game_engine.Vector2;

/**
 * This is a component which can be attached to any GameObject to act as a point
 * source of gravity. Both positive (provides an attractive force) and negative
 * (provides a repulsive force) gravitational constants are allowed. There is no
 * correspondence between the given gravitational constant and the mass of the
 * Rigidbody2D component.
 * 
 * The attached object is not affected by the gravitational forces of any other
 * object. In order to set up an N-body problem, each body must have its own
 * GravityPoint component attached.
 * 
 * @author Joe S.
 *
 */

public class GravityPoint extends Component {

	private Vector2 relativePosition = new Vector2(0f, 0f);
	private double gravityConstant; // Constant of gravitational attraction
	private double minRadius; // Minimum radius to compute force (force goes to
								// infinity at 0 distance)

	public GravityPoint(Transform trans, double gravityConstant, double minRadius) {

		this.name = "GravityPoint";
		this.transform = trans;
		this.gravityConstant = gravityConstant;
		this.minRadius = minRadius;
	}

	public void setGravityConstant(double gravityConstant) {
		this.gravityConstant = gravityConstant;
	}

	public double getGravityConstant() {
		return this.gravityConstant;
	}

	public void setMinRadius(double minRadius) {
		this.minRadius = minRadius;
	}

	public double getMinRadius() {
		return this.minRadius;
	}

	public Vector2 getRelativePosition() {
		return relativePosition;
	}

	public void setRelativePosition(Vector2 newRelativePosition) {
		this.relativePosition = newRelativePosition;
	}

	Vector2 getPosition() {
		// FIXME the transform is null
		return relativePosition.add(getTransform().getPosition());
	}

	public Vector2 positionInWorldSpace() {

		return getPosition();
	}
}
