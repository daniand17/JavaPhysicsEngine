package game_engine;

import java.awt.geom.Ellipse2D;

class EllipseCollider2D extends Collider {

	EllipseCollider2D(Vector2 size) {
		this.className = "EllipseCollider2D";
		this.size = size;
		offset = new Vector2(size.x * 0.5, size.y * 0.5);
		setRelativePosition(Vector2.zero());
		setSize(size);
	}

	/**
	 * Sets the size of this collider
	 */
	@Override
	public void setSize(Vector2 size) {
		Ellipse2D.Double temp = new Ellipse2D.Double();
		temp.width = size.x;
		temp.height = size.y;
		collider = generateColliderFromShape(temp);
	}
}
