package game_engine;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class BoxCollider2D extends Collider {

	public BoxCollider2D(Vector2 size) {

		this.className = "BoxCollider2D";
		this.size = size;
		setRelativePosition(new Vector2(-size.x * 0.5, -size.y * 0.5f));
		Rectangle2D.Double temp = new Rectangle2D.Double();
		temp.width = size.x;
		temp.height = size.y;
		AffineTransform convTransf = new AffineTransform();
		collider = convTransf.createTransformedShape(temp);
	}
}
