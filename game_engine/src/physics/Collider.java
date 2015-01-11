package physics;

import game_engine.Component;
import game_engine.GameObject;
import game_engine.Transform;
import game_engine.Vector2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.List;

public abstract class Collider extends Component {
	/**
	 * Constants which correspond to the types of colliders that can be made by
	 * this factory.
	 * 
	 * @author andrew
	 *
	 */
	public enum Colliders {
		ELLIPSE_2D, RECTANGLE_2D
	}

	public Shape collider;
	// Whether this collider should is a trigger instead of a physical object
	public boolean isTrigger = false;
	private boolean collisionsResolvedThisFrame = false;
	// The 2 dimensional size of this collider
	public Vector2 size;
	// Position relative to the transform of the attached GameObject
	public Vector2 relativePosition = new Vector2(0f, 0f);
	protected Vector2 offset;

	/**
	 * This method will set the size of the collider to be the specified new
	 * size.
	 * 
	 * @param size
	 *            the size of the new vector
	 */
	public abstract void setSize(Vector2 size);

	/**
	 * This method is used to get the area of the collider in world space.
	 * 
	 * @return
	 */
	Area getBoundedArea() {
		// Update this colliders position
		Vector2 pos = positionInWorldSpace();
		Vector2 relPos = getRelativePosition();
		// The rotation transformation
		AffineTransform transf = AffineTransform.getRotateInstance(getTransform().getRotation(),
				offset.x - relPos.x, offset.y - relPos.y);

		// The translation transformation
		AffineTransform translationMatrix = AffineTransform.getTranslateInstance(pos.x - offset.x,
				pos.y - offset.y);
		// Create the transformed shape from the collider
		Shape temp = transf.createTransformedShape(collider);
		// Translate the new shape using the translation matrix
		temp = translationMatrix.createTransformedShape(temp);
		// Get the area of the shape to send for collision detection
		return new Area(temp);
	}

	void attachTransform(Transform transform) {
		this.setTransform(transform);
	}

	void attachGameObject(GameObject gameObject) {
		this.setGameObject(gameObject);
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

	/**
	 * This method gets the position of the collider in world space
	 * 
	 * @return
	 */
	public Vector2 positionInWorldSpace() {
		// TODO Implement this method once we figure out how to do the
		// transforms and conversion from transform local space to world space.
		// Not crucial yet but this should eventually be the defacto method used
		// for collision detection once transforms are figured out.
		return getPosition();
	}

	/**
	 * Calls the method to render the collider on the graphics context when
	 * called.
	 * 
	 * @param g2d
	 *            the graphics context to render this collider on
	 */
	public void renderCollider(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke());
		g2d.draw(this.getBoundedArea());
	}

	protected static Shape generateColliderFromShape(Shape shape) {
		AffineTransform convTransf = new AffineTransform();
		return convTransf.createTransformedShape(shape);
	}

	/**
	 * This is a package-access method that is used to resolve collisions
	 * involving this GameObject. It is called only if this object has a
	 * collider attached to it. It is handed a list of objects compiled from the
	 * quadtree, in which collisions might occur due to spatial locality.
	 * 
	 * @param list
	 *            the list of objects that MIGHT collide with this game object
	 */
	public void resolveCollisions(List<Collider> list) {
		this.collisionsResolvedThisFrame = true;

		if ( list.size() == 0 )
			return;

		// Iterate through the list of colliding objects
		for (Collider col : list)
			// Checks to see if this collisions was already resolved
			if ( !col.collisionsResolvedThisFrame )
				Physics.resolveCollision(this, col);
	}

	public void setCollisionsResolved(boolean bool) {
		collisionsResolvedThisFrame = false;
	}

	/**
	 * This method creates a new collider of the given type with a standard size
	 * given by the Vector2 (64,64).
	 * 
	 * @param type
	 *            the type of collider to make
	 * @return the new collider
	 */
	public static Collider createCollider(Colliders type, GameObject attachedGO,
			Transform attachedTransform) {

		Vector2 size = new Vector2(64f, 64f); // TODO make std size 1 when ortho
												// camera is implemented
		switch (type) {
		case ELLIPSE_2D:
			EllipseCollider2D newEllipseCol = new EllipseCollider2D(size);
			newEllipseCol.attachTransform(attachedTransform);
			newEllipseCol.attachGameObject(attachedGO);
			return newEllipseCol;
		case RECTANGLE_2D:
			BoxCollider2D newBoxColl = new BoxCollider2D(size);
			newBoxColl.attachTransform(attachedTransform);
			newBoxColl.attachGameObject(attachedGO);
			return newBoxColl;
		}
		// If no valid collider type is specified return null
		return null;
	}
}
