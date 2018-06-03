// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends Entity {
	
	// sprites
	private BufferedImage[] dogSprite;
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	
	// gameplay
	private int numBones;
	private int totalBones;
	private long ticks;
	
	public Player(TileMap tilemap) {
		
		super(tilemap);
		
		w = 16;
		h = 16;
		cw = 12;
		ch = 12;
		
		moveSpeed = 2;
		
		numBones = 0;
		
		dogSprite = Content.DOG[0];
		
		animation.setsquareTiles(dogSprite);
		animation.settimeDelay(10);
		
	}
	
	
	public void collectedBone() { numBones++; }
	public int numBones() { return numBones; }
	public int getTotalBones() { return totalBones; }
	public void setTotalBones(int i) { totalBones = i; }
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}
	
	// Keyboard input.
	public void setAction() {
		if(true) {
			if(currentAnimation == UP && tileMap.getIndex(row - 1, col) == 21) {
				tileMap.setTile(row - 1, col, 1);
			}
			if(currentAnimation == DOWN && tileMap.getIndex(row + 1, col) == 21) {
				tileMap.setTile(row + 1, col, 1);
			}
			if(currentAnimation == LEFT && tileMap.getIndex(row, col - 1) == 21) {
				tileMap.setTile(row, col - 1, 1);
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(row, col + 1) == 21) {
				tileMap.setTile(row, col + 1, 1);
			}
		}
	}
	
	public void update() {
		
		ticks++;
	
		// update position
		super.update();
		
	}
	
	// Draw Player.
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}