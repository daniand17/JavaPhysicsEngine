package game_engine;

import java.awt.event.KeyEvent;

public class Input {

	KeyboardListener keyboard;
	MousepadListener mouse;

	public void keyPressed(KeyEvent event) {
		keyboard.isKeyPressed(event.getKeyCode());
	}

	public void keyReleased(KeyEvent event) {
		keyboard.isKeyReleased(event.getKeyCode());
	}
}
