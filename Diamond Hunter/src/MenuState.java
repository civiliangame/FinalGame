// The main menu GameState.

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage bone;
	
	private int currentOption = 0;
	private String[] options = {
		"START",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENU[0][0];
		bone = Content.BONE[0][0];
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 44, 90);
		Content.drawString(g, options[1], 48, 100);
		
		if(currentOption == 0) g.drawImage(bone, 25, 86, null);
		else if(currentOption == 1) g.drawImage(bone, 25, 96, null);
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			currentOption--;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			selectOption();
		}
	}
	
	private void selectOption() {
		if(currentOption == 0) {
			gamestatemanager.setState(GameStateManager.PLAY);
		}
		if(currentOption == 1) {
			System.exit(0);
		}
	}
	
}
