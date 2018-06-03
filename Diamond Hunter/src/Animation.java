// This class takes in an array of images.
// Calling getImage() gives you the appropriate
// image in the Animate cycle.

import java.awt.image.BufferedImage;

public class Animation{

	//Intialize Variables
	private BufferedImage[] squareTiles;
	private int currentsquareTile;
	private int numsquareTiles;
	private int numCount;
	private int timeDelay;

	
	//Updates the game
	public void update() {

		//Error
		if(timeDelay == -1) 
			return;

		numCount++;

		if(numCount == timeDelay) {
			currentsquareTile++;
			numCount = 0;
		}
		if(currentsquareTile == numsquareTiles) {
			currentsquareTile = 0;
		}

	}
	
	
	//Gettter setter methods
	public void setsquareTile(int i) { 
		currentsquareTile = i; 
	}
	

	public int getsquareTile() { 
		return currentsquareTile;
	}
	public void setNumsquareTiles(int i) { 
		numsquareTiles = i;
	}

	
	public void setsquareTiles(BufferedImage[] squareTiles) {
		this.squareTiles = squareTiles;
		currentsquareTile = 0;
		numCount = 0;
		timeDelay = 2;
		numsquareTiles = squareTiles.length;
	}

	public void settimeDelay(int i) { 
		timeDelay = i;
	}

	public int getnumCount() { 
		return numCount;
	}
	public BufferedImage getImage() { 
		return squareTiles[currentsquareTile];
	}

}