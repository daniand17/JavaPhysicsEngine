package game_engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class SampleGameScreen extends Screen {

	private int x = 0, y = 0;

	public SampleGameScreen(ScreenFactory screenFactory) {
		super(screenFactory);
	}

	@Override
	public void onCreate() {
		System.out.println("Creating screen");
	}

	@Override
	public void onUpdate() {
		
		if ( this.getScreenFactory().getGame().getKeyboardListener()
				.isKeyPressed(KeyEvent.VK_A) )
			x -= 2;

		if ( this.getScreenFactory().getGame().getKeyboardListener()
				.isKeyPressed(KeyEvent.VK_D) )
			x += 2;
		if ( this.getScreenFactory().getGame().getKeyboardListener()
				.isKeyPressed(KeyEvent.VK_W) )
			y -= 2;
		if ( this.getScreenFactory().getGame().getKeyboardListener()
				.isKeyPressed(KeyEvent.VK_S) )
			y += 2;

		if ( x > 800 + 64 )
			x = -64;
		else if ( x < -64 )
			x = 864;

		if ( y > 571 - 64 )
			y = 571 - 64;
		else if ( y < 0 )
			y = 0;
	}

	@Override
	public void onDraw(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(x, y, 64, 64);
	}
}
