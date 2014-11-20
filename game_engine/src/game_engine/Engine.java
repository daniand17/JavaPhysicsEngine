package game_engine;

import javax.swing.JFrame;

public class Engine {

	private final JFrame window;

	private final GameThread gameThread;
	private static Vector2 displayDims;

	public Engine(int windowX, int windowY, String title) {
		// Creates the window
		window = new JFrame();
		// Sets the parameters of the window
		window.setSize(windowX, windowY);
		displayDims = new Vector2(windowX, windowY);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Allows us to listen for keyboard input on the window
		window.setFocusable(true);
		// Puts the window in the center of the screen
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);

		// Adds key and mouse listeners
		window.addKeyListener(Input.getKeyboard());
		window.addMouseListener(Input.getMouse());
		window.addMouseMotionListener(Input.getMouse());

		// Creates the game thread that will run the game loop and update logic
		gameThread = new GameThread(this);
		// Adds the thread to the window
		window.add(gameThread);
		// Starts the game thread
		new Thread(gameThread).start();
	}

	public JFrame getWindow() {
		return window;
	}

	public static Vector2 getDisplayDimensions() {
		return displayDims;

	}
}