package game_engine;

import java.awt.Graphics2D;

public interface RenderableEntity {

	void render(Graphics2D g2d, Vector3 renderPos);

	void renderObject(Graphics2D g2d, double alpha);
}
