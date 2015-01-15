package physics;

import game_engine.Vector2;

import java.awt.geom.Rectangle2D;

/**
 * This class implements the Collider abstract class as a box collider with
 * width and height dimensions.
 * 
 * @author andrew
 *
 */
class BoxCollider2D extends Collider {

	BoxCollider2D(Vector2 size) {
		this.name = "BoxCollider2D";
		this.size = size;
		// Accounts for the difference between Java shape coordinates and engine
		// coordinates
		offset = new Vector2(size.x * 0.5, size.y * 0.5);
		// The position of this collider away from the transform
		setRelativePosition(Vector2.zero());
		setSize(size);
	}

	/**
	 * Sets the size of this collider given a Vector2
	 */
	public void setSize(Vector2 size) {
		Rectangle2D.Double temp = new Rectangle2D.Double();
		temp.width = size.x;
		temp.height = size.y;
		collider = generateColliderFromShape(temp);
	}
}
