package game_engine;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Engine {

	private final JFrame window;
	
	private final GameThread gameThread;
	private final KeyboardListener keyboardListener;
	private final MouseListener mousepadListener;

	public Engine(int windowX, int windowY, String title) {
		// Creates the window
		window = new JFrame();

	
		keyboardListener = new KeyboardListener();
		mousepadListener = new MousepadListener();

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
		window.addKeyListener(keyboardListener);
		window.addMouseListener(mousepadListener);

		// Creates the game thread that will run the game loop and update logic
		gameThread = new GameThread(this);
		// Adds the thread to the window
		window.add(gameThread);
		// Starts the game thread
		new Thread(gameThread).start();

	}

	public MouseListener getMouseListener() {
		return mousepadListener;
	}

	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}

	public JFrame getWindow() {
		return window;
	}
}