package game_engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input {

	private static final KeyboardListener keyboard = new KeyboardListener();
	private static final MousepadListener mouse = new MousepadListener();

	public static KeyboardListener getKeyboard() {
		return keyboard;
	}

	public static MousepadListener getMouse() {
		return mouse;
	}

	public static boolean isKeyPressed(int key) {
		return KeyboardListener.isKeyPressed(key);
	}

	public static boolean isKeyReleased(int key) {
		return KeyboardListener.isKeyReleased(key);
	}

	public static boolean isMousePressed() {
		return mouse.isMousePressed();
	}

	public static Vector2 getMouseCoordinates() {
		return mouse.getMouseCoords();
	}

	// TODO add public static boolean onMouseDown()
	// TODO add public static boolean onMouseUp()

	// TODO add public static boolean onKeyDown(int key)
	// TODO add public static boolean onKeyUp(int key)
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
