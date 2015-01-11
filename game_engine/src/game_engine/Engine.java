package game_engine;

import graphics.Camera;
import graphics.GraphicsThread;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * This class is a low-level class designed solely to get the engine up and
 * running. It initializes the game window, adds key, mouse, and mouse motion
 * listeners to the window, and starts the game thread.
 * 
 * @author andrew
 *
 */
public class Engine {

	private final JFrame window;
	private final GraphicsThread graphicsThread;
	private final GameThread gameThread;

	/**
	 * The constructor which is called by Main.java to construct a new game.
	 * 
	 * @param windowX
	 * @param windowY
	 * @param title
	 */
	public Engine(int windowX, int windowY, String title) {
		// Creates the window
		window = new JFrame(title);
		// Sets the parameters of the window
		window.setSize(windowX, windowY);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Allows us to listen for keyboard input on the window
		window.setFocusable(true);
		// Puts the window in the center of the screen
		window.setLocationRelativeTo(null);
		// The game window
		graphicsThread = new GraphicsThread(window.getSize());

		window.add(graphicsThread);
		// Adds key and mouse listeners
		window.addKeyListener(Input.getKeyboard());
		window.addMouseListener(Input.getMouse());
		window.addMouseMotionListener(Input.getMouse());
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		window.setVisible(true);
		// Creates the game thread that will run the game loop and update logic
		gameThread = new GameThread();
		graphicsThread.setupWindow();
		// Starts the game thread
		new Thread(gameThread).start();
		// Start the rendering
		new Thread(graphicsThread).start();
		// Instantiates the main camera
		ObjectManager.instantiate(Camera.main, Vector2.zero());
	}
}