package graphics;

import game_engine.Vector2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class EllipseRenderer extends Renderer {

	private Vector2 size;

	public EllipseRenderer(Vector2 size) {
		this.name = "EllipseRenderer";
		setSize(size);
		offset = new Vector2(size.x * 0.5, size.y * 0.5);
		// Create the shape that will be rendered
		shape = new Ellipse2D.Double(0, 0, size.x, size.y);
	}

	@Override
	void render(Graphics2D g2d, Vector2 renderPos) {
		g2d.setColor(Color.blue);
		g2d.setStroke(new BasicStroke(BasicStroke.CAP_SQUARE));
	}

	public Vector2 getSize() {
		return new Vector2(size.x, size.y);
	}

	public void setSize(Vector2 size) {
		if ( size.x < 0 )
			size.x = 0;

		if ( size.y < 0 )
			size.y = 0;

		this.size = size;

		shape = new Ellipse2D.Double(0, 0, size.x, size.y);
	}
}
