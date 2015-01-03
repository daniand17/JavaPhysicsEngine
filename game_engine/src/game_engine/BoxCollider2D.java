package game_engine;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class BoxCollider2D extends Collider {

	private Shape collider;

	public BoxCollider2D(Vector2 size) {

		this.className = "BoxCollider2D";
		this.size = size;
		setRelativePosition(new Vector2(size.x * 0.5, size.y * 0.5f));
		collider = new Rectangle2D.Double();

	}

	@Override
	Area getBoundedArea() {
		Rectangle2D.Double temp = (Rectangle2D.Double) collider;
		temp.width = size.x;
		temp.height = size.y;
		Vector2 pos = getPositionInWorldSpace();
		temp.x = pos.x;
		temp.y = pos.y;

		return new Area(temp);
	}
}
