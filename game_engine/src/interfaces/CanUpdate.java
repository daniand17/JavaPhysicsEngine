package interfaces;

public interface CanUpdate {

	/**
	 * This method is called at the beginning of the game loop, before physics updates. Can be used
	 * to set up components and such. Unsure if thread safe.
	 */
	void start();

	/**
	 * This method is called after physics updates, but before the canvas is rendered. Called more
	 * often than fixedUpdate() because it is not restricted to a fixed timestep. Called each
	 * iteration of the game loop.
	 */
	void update();

}
