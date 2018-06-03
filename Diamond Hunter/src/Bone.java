// Bone class.

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

//Modifies upon bone collection
public class Bone extends Entity {
	
	//Where the sprites come in
	BufferedImage[] sprites;
	
	//Changing tiles
	private ArrayList<int[]> tilesToChange;
	
	//Constructor
	public Bone(TileMap tilemap) {
		
		super(tilemap);
		
		
		//Initializing dimensions, width and height
		w = 16;
		h = 16;
		cw = 12;
		ch = 12;
		
		
		//Applying the bone sprite
		sprites = Content.BONE[0];
		
		animation.setsquareTiles(sprites);
		animation.settimeDelay(10);
		
		//initializing it
		tilesToChange = new ArrayList<int[]>();
		
	}
	
	//Adding the change
	public void addChange(int[] i) {
		tilesToChange.add(i);
	}
	
	//Getting changes
	public ArrayList<int[]> getChanges() {
		return tilesToChange;
	}
	
	
	//Calling the update method in Animation
	public void update() {
		animation.update();
	}
	
	//Drawing it
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
