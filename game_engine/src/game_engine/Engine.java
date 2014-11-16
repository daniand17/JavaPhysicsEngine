package game_engine;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Engine {

	private final JFrame window;
	private final ScreenFactory screenFactory;
	private final GameThread gameThread;
	private final KeyboardListener keyboardListener;
	private final MouseListener mousepadListener;

	public Engine(int windowX, int windowY, String title) {
		window = new JFrame();
		screenFactory = new ScreenFactory(this);
		keyboardListener = new KeyboardListener();
		mousepadListener = new MousepadListener();

		window.setSize(windowX, windowY);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		gameThread = new GameThread(this);

		// Adds the thread to the window
		window.add(gameThread);
		// Adds key and mouse listeners
		window.addKeyListener(keyboardListener);
		window.addMouseListener(mousepadListener);

		// Starts the game thread
		new Thread(gameThread).start();

	}

	public MouseListener getMouseListener() {
		return mousepadListener;
	}

	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public JFrame getWindow() {
		return window;
	}
}