package game_engine;

import javax.swing.JFrame;

public class Engine {

	private final JFrame window;
	private final Display display;

	private final GameThread gameThread;

	public Engine(int windowX, int windowY, String title) {
		// Creates the window
		window = new JFrame();
		// Sets the parameters of the window
		window.setSize(windowX, windowY);

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

		// The game window
		display = new Display(window.getSize());
		window.add(display);
		// Creates the game thread that will run the game loop and update logic
		gameThread = new GameThread(display);

		// Adds the thread to the window
		// window.add(gameThread);
		// Starts the game thread
		new Thread(gameThread).start();
	}

	public JFrame getWindow() {
		return window;
	}
}