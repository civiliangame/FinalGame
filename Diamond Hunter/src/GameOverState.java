// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot

import java.awt.Color;
import java.awt.Graphics2D;


public class GameOverState extends GameState {
	
	private Color color;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		color = new Color(164, 198, 222);
	}
	
	public void update() {}
	
	public void draw(Graphics2D g) {
		
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		
		Content.drawString(g, "Congrats, Nolly is full!", 8, 78);
		
		Content.drawString(g, "press any key", 12, 110);
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}