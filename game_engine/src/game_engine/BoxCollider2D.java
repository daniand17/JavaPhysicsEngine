package game_engine;

import java.awt.geom.Rectangle2D;

class BoxCollider2D extends Collider {

	BoxCollider2D(Vector2 size) {
		this.name = "BoxCollider2D";
		this.size = size;
		offset = new Vector2(size.x * 0.5, size.y * 0.5);
		setRelativePosition(Vector2.zero());
		setSize(size);
	}

	public void setSize(Vector2 size) {
		Rectangle2D.Double temp = new Rectangle2D.Double();
		temp.width = size.x;
		temp.height = size.y;
		collider = generateColliderFromShape(temp);
	}
}
