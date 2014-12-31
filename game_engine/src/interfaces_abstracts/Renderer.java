package interfaces_abstracts;

import game_engine.Vector2;
import game_engine.Vector3;

import java.awt.Graphics2D;

public abstract class Renderer implements RenderableEntity {

	private Vector2 previous;
	private Vector2 current;

	/**
	 * This method does the interpolation for the rendering of the object given
	 * an alpha value, and a graphics2D object. Calls the implementing class'
	 * render method to render the object.
	 */
	public void renderObject(Graphics2D g2d, double alpha) {
		if ( previous != null && current != null ) {
			// calculate the interpolated vectors
			Vector2 cRenderPos = new Vector2(current.x * alpha, current.y * alpha);
			Vector2 pRenderPos = new Vector2(previous.x * (1 - alpha), previous.y * (1 - alpha));
			Vector2 interpPos = cRenderPos.add(pRenderPos);
			// Call the implementing class' render function

			render(g2d, interpPos);

		}
	}

	public void updateRendererPositions(Vector2 prevPos, Vector2 position) {
		previous = prevPos;
		current = position;
	}

	/**
	 * The render method is called by the class implementing this abstract
	 * class. Takes a Graphics2D object and a position in which to render the
	 * object.
	 */
	public abstract void render(Graphics2D g2d, Vector2 renderPos);
}
