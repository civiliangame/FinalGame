
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

//GameState that shows logo.

public class IntroState extends GameState {
	
	//Variables
	private BufferedImage logo;
	
	private int alpha;
	private int ticks;
	
	private final int FADE_IN = 60;
	private final int LENGTH = 60;
	private final int FADE_OUT = 60;
	
	
	//Constructor
	public IntroState(GameStateManager gsm) {
		super(gsm);
	}
	
	
	//Loads the greenberg (tm) logo
	public void init() {
		ticks = 0;
		try {
			logo = ImageIO.read(getClass().getResourceAsStream("/Logo/logo.gif"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fade in and fade out like Nintendo
	public void update() {
		handleInput();
		ticks++;
		if(ticks < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if(ticks > FADE_IN + LENGTH) {
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if(ticks > FADE_IN + LENGTH + FADE_OUT) {
			gamestatemanager.setState(GameStateManager.MENU);
		}
	}
	
	
	//Draw it
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		g.drawImage(logo, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2, null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			gamestatemanager.setState(GameStateManager.MENU);
		}
	}
	
}