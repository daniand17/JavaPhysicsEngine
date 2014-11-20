package game_engine;

import java.awt.Graphics2D;

public abstract class Renderer implements RenderableEntity {

	private Vector3 previous;
	private Vector3 current;

	/**
	 * This method does the interpolation for the rendering of the object given an alpha value, and
	 * a graphics2D object. Calls the implementing class' render method to render the object.
	 */
	public void renderObject(Graphics2D g2d, double alpha) {
		if ( previous != null && current != null ) {
			// calculate the interpolated vectors
			Vector3 cRenderPos = new Vector3(current.x * alpha, current.y * alpha, current.z
					* alpha);
			Vector3 pRenderPos = new Vector3(previous.x * (1 - alpha), previous.y * (1 - alpha),
					previous.z * (1 - alpha));
			Vector3 renderPos = cRenderPos.add(pRenderPos);
			// Call the implementing class' render function
			render(g2d, renderPos);
		}
	}

	public void updateRendererPositions(Vector3 prev, Vector3 curr) {
		previous = prev;
		current = curr;
	}

	/**
	 * The render method is called by the class implementing this abstract class. Takes a Graphics2D
	 * object and a position in which to render the object.
	 */
	public abstract void render(Graphics2D g2d, Vector3 renderPos);

}
