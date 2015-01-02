package game_engine;

import java.awt.Shape;

public abstract class Collider extends Component {

	// Whether this collider should is a trigger instead of a physical object
	public boolean isTrigger = false;
	boolean collisionsResolvedThisFrame = false;
	// The 2 dimensional size of this collider
	public Vector2 size;
	// The position of the collider relative to the transform of the GameObject
	// it is attached to
	private Vector2 relativePosition = new Vector2(0f, 0f);

	abstract Shape getBoundedArea();

	/**
	 * Package-access methodd that gets the coordinates of this collider
	 * adjusted for the transform of the GameObject it is attached to. If the
	 * transform is a child of another transform, this does not account for
	 * that, only the local space of that transform. DOES NOT return a world
	 * space currently. This will be generally useful for collision detection.
	 * 
	 * @return the Vector2 representing the position of this collider.
	 */
	Vector2 getPosition() {

		return relativePosition.add(transform.position);
	}

	/**
	 * Gets the coordinates of this collider relative to the transform of the
	 * GameObject it is attached to. For example, if the collider is centered on
	 * the GameObject, then this would return a Vector2 equivalent to the zero
	 * vector.
	 * 
	 * @return the position of this collider.
	 */
	Vector2 getRelativePosition() {
		return relativePosition;
	}

	Vector2 getPositionInWorldSpace() {
		// TODO Implement this method once we figure out how to do the
		// transforms and conversion from transform local space to world space.
		// Not crucial yet but this should eventually be the defacto method used
		// for collision detection once transforms are figured out.
		return null;
	}

}
