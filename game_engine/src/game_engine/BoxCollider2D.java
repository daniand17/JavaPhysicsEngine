package game_engine;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class BoxCollider2D extends Collider {

	public BoxCollider2D(Vector2 sizeDimensions) {
		this.size = sizeDimensions;
	}

	@Override
	Shape getBoundedArea() {
		Vector2 pos = this.getPosition();
		return new Rectangle2D.Double(pos.x, pos.y, size.x, size.y);
	}

}
