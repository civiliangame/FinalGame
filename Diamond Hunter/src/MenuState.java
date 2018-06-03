

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
//The main menu GameState.
public class MenuState extends GameState {
	
	//Variables
	private BufferedImage bg;
	private BufferedImage bone;
	
	private int currentOption = 0;
	private String[] options = {
		"START",
		"QUIT"
	};
	
	
	//Constructor
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	
	//Brings the sprites over from content since it's static
	public void init() {
		bg = Content.MENU[0][0];
		bone = Content.BONE[0][0];
	}
	
	
	//Updates
	public void update() {
		handleInput();
	}
	
	
	//Draws the bones at the correct locations based on which option the player selects
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 44, 90);
		Content.drawString(g, options[1], 48, 100);
		
		if(currentOption == 0) g.drawImage(bone, 25, 86, null);
		else if(currentOption == 1) g.drawImage(bone, 25, 96, null);
		
	}
	
	//Handles all input
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
	
	//Select options based on the player's input
	private void selectOption() {
		if(currentOption == 0) {
			gamestatemanager.setState(GameStateManager.PLAY);
		}
		if(currentOption == 1) {
			System.exit(0);
		}
	}
	
}
