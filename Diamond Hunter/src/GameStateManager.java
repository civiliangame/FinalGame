// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

import java.awt.Graphics2D;

public class GameStateManager {
	
	//Variables
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	private boolean paused;
	private PauseState pauseState;
	
	//Static variables
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;
	
	public static final int NUM_STATES = 4;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public GameStateManager() {
		
		paused = false;
		pauseState = new PauseState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}
	
	//Sets the State
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) {
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		}
		else if(i == GAMEOVER) {
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
	}
	
	//Unloads the state
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	
	//Pause or unpause the game
	public void setPaused(boolean b) {
		paused = b;
	}
	
	
	//Update the game
	public void update() {
		if(paused) {
			pauseState.update();
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	//Create graphics
	public void draw(Graphics2D g) {
		if(paused) {
			pauseState.draw(g);
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
	
}
