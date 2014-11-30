package interfaces_abstracts;

import game_engine.Vector3;

import java.awt.Graphics2D;

public interface RenderableEntity {

	void render(Graphics2D g2d, Vector3 renderPos);

	void renderObject(Graphics2D g2d, double alpha);
}
