//Abstract class that has methods that must be implemented

import java.awt.Graphics2D;

public abstract class GameState {
	
	protected GameStateManager gamestatemanager;
	
	public GameState(GameStateManager gamestatemanager) {
		this.gamestatemanager = gamestatemanager;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void handleInput();
	
}
