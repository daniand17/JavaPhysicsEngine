package game_engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class creates a wrapper for Javas keyboard and mouse listeners. This
 * class contains the public interface to the listeners used in this engine for
 * ease of use by the user.
 * 
 * @author andrew
 *
 */
public class Input {

	private static final KeyboardListener keyboard = new KeyboardListener();
	private static final MousepadListener mouse = new MousepadListener();

	/**
	 * Package access method used to get the keyboard listener
	 * 
	 * @return
	 */
	static KeyboardListener getKeyboard() {
		return keyboard;
	}

	/**
	 * Package access method used to get the mouse listener.
	 * 
	 * @return
	 */
	static MousepadListener getMouse() {
		return mouse;
	}

	/**
	 * This method takes a KeyCode and returns whether that button was pressed
	 * this frame
	 * 
	 * @param key
	 *            the keycode corresponding to the depressed key
	 * @return whether the key was pressed this frame
	 */
	public static boolean getKeyDown(int key) {
		return KeyboardListener.isKeyPressed(key);
	}

	/**
	 * Whether the key defined by the KeyCode was released this frame
	 * 
	 * @param key
	 *            the key to check for release
	 * @return whether the key was released.
	 */
	public static boolean isKeyReleased(int key) {
		return KeyboardListener.isKeyReleased(key);
	}

	/**
	 * Returns whether a button on the mouse has been depressed this frame
	 * 
	 * @return
	 */
	public static boolean isMousePressed() {
		return mouse.isMousePressed();
	}

	/**
	 * Gets the screen coordinates of the mouse
	 * 
	 * @return
	 */
	public static Vector2 getMouseCoordinates() {
		return mouse.getMouseCoords();
	}

	// TODO (Andy) add public static boolean onMouseDown()
	// TODO (Andy) add public static boolean onMouseUp()

	// TODO (Andy) add public static boolean onKeyDown(int key)
	// TODO (Andy) add public static boolean onKeyUp(int key)
}

class MousepadListener implements MouseListener, MouseMotionListener {

	private int mouseX, mouseY;
	private boolean clicked;

	@Override
	public void mouseClicked(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// Unused
	}

	@Override
	public void mousePressed(MouseEvent event) {
		mouseClicked(event);
		clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		clicked = false;
	}

	public boolean isMousePressed() {
		return clicked;
	}

	public Vector2 getMouseCoords() {
		return new Vector2(mouseX, mouseY);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}
}

class KeyboardListener implements KeyListener {

	private static boolean[] keys = new boolean[256];

	@Override
	public void keyPressed(KeyEvent event) {
		keys[event.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keys[event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// Unused
	}

	public static boolean isKeyPressed(int key) {
		return keys[key];
	}

	public static boolean isKeyReleased(int key) {
		return !keys[key];
	}
}
