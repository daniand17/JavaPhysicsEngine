package physics;

import game_engine.Component;
import game_engine.GameObject;
import game_engine.Transform;
import game_engine.Vector2;

import java.util.List;
/**
 * This is a component which can be attached to any GameObject to act as a point source of gravity. Both
 * positive (provides an attractive force) and negative (provides a repulsive force) gravitational constants 
 * are allowed. There is no correspondence between the given gravitational constant and the mass of the 
 * Rigidbody2D component.
 * 
 * The attached object is not affected by the gravitational forces of any other object. In order to set up an N-body 
 * problem, each body must have its own GravityPoint component attached.
 * 
 * @author Joe
 *
 */

public class GravityPoint extends Component {

	private Vector2 relativePosition = new Vector2(0f, 0f);
	private double gravityConstant; // Constant of gravitational attraction
	private double minRadius; // Minimum radius to compute force (force goes to infinity at 0 distance)


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
		this.relativePosition = newRelativePosition;
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
		// FIXME the transform is null
		return relativePosition.add(getTransform().getPosition());
	}
	
	public Vector2 positionInWorldSpace() {
		// TODO Implement this method once we figure out how to do the
		// transforms and conversion from transform local space to world space.
		// Not crucial yet but this should eventually be the defacto method used
		// for collision detection once transforms are figured out.
		return getPosition();
	}


/*
	 public void resolveGravity(List<GameObject> list) {

		if ( list.size() == 0 )
			return;

		// Iterate through the list of Rigidbody2D objects
		for (GameObject go : list)
			if (go.getRigidbody() != null)
				Physics.resolvePointGravity(this, go);
	}
*/
}
