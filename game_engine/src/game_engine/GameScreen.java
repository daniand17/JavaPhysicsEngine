package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameScreen extends ProgramWindow {
	private int x = 0, y = 0;

	Random rng = new Random();

	@Override
	public void fixedUpdate() {
		// Tests keyboard listener and instantiating an object
		if ( KeyboardListener.isKeyPressed(KeyEvent.VK_0) ) {
			int randX = rng.nextInt((int) Engine.getDisplayDimensions().x);
			int randY = rng.nextInt((int) Engine.getDisplayDimensions().y);
			ObjectManager.instantiate(new TestPlayer(), new Vector3(randX, randY, 0f));
		}
		if ( KeyboardListener.isKeyPressed(KeyEvent.VK_9) ) {
			int randX = rng.nextInt((int) Engine.getDisplayDimensions().x);
			int randY = rng.nextInt((int) Engine.getDisplayDimensions().y);
			ObjectManager.instantiate(new TestCircle(), new Vector3(randX, randY, 0f));
		}
		// Tests mouse input and motion
		if ( Input.isMousePressed() ) {
			float x = Input.getMouseCoordinates().x;
			float y = Input.getMouseCoordinates().y;
			ObjectManager.instantiate(new TestPlayer(), new Vector3(x - 32, y - 32, 0f));
		}
	}

	@Override
	public void onDraw(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(x, y, 64, 64);
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}
}
