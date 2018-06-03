// The game object superclass.
// This class has all the logic required
// to move around a tile based map.

import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class Entity {

	// dimensions
	protected int w;
	protected int h;
	protected int cw;
	protected int ch;

	// position
	protected int x;
	protected int y;
	protected int xdestination;
	protected int ydestination;
	protected int row;
	protected int col;

	// movement
	protected boolean move;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;

	// attributes
	protected int moveSpeed;

	// tilemap
	protected TileMap tileMap;
	protected int tileSize;
	protected int xmap;
	protected int ymap;

	// animation
	protected Animation animation;
	protected int currentAnimation;

	public Entity(TileMap tm) {
		tileMap = tm;
		tileSize = tileMap.getTileSize();
		animation = new Animation();
	}

	public int getx() { 
		return x;
	}
	public int gety() { 
		return y;
	}
	public int getRow() { 
		return row;
	}
	public int getCol() { 
		return col;
	}

	public void setPos(int i1, int i2) {
		x = i1;
		y = i2;
		xdestination = x;
		ydestination = y;
	}
	public void setMapPos() {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	public void setTilePos(int i1, int i2) {
		y = i1 * tileSize + tileSize / 2;
		x = i2 * tileSize + tileSize / 2;
		xdestination = x;
		ydestination = y;
	}

	public void setLeft() {
		if(move) return;
		left = true;
		move = validateNextPosition();
	}
	public void setRight() {
		if(move) return;
		right = true;
		move = validateNextPosition();
	}
	public void setUp() {
		if(move) return;
		up = true;
		move = validateNextPosition();
	}
	public void setDown() {
		if(move) return;
		down = true;
		move = validateNextPosition();
	}

	public boolean intersects(Entity o) {
		return getRectangle().intersects(o.getRectangle());
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, cw, ch);
	}

	// Returns whether or not the entity can
	// move into the next position.
	public boolean validateNextPosition() {

		if(move) return true;

		row = y / tileSize;
		col = x / tileSize;

		if(left) {
			if(col == 0 || tileMap.getType(row, col - 1) == Tile.BLOCKED) {
				return false;
			}
			else {
				xdestination = x - tileSize;
			}
		}
		if(right) {
			if(col == tileMap.getNumCols() || tileMap.getType(row, col + 1) == Tile.BLOCKED) {
				return false;
			}
			else {
				xdestination = x + tileSize;
			}
		}
		if(up) {
			if(row == 0 || tileMap.getType(row - 1, col) == Tile.BLOCKED) {
				return false;
			}
			else {
				ydestination = y - tileSize;
			}
		}
		if(down) {
			if(row == tileMap.getNumRows() - 1 || tileMap.getType(row + 1, col) == Tile.BLOCKED) {
				return false;
			}
			else {
				ydestination = y + tileSize;
			}
		}

		return true;

	}

	// Calculates the destination coordinates.
	public void getNextPosition() {

		if(left && x > xdestination) x -= moveSpeed;
		else left = false;
		if(left && x < xdestination) x = xdestination;

		if(right && x < xdestination) x += moveSpeed;
		else right = false;
		if(right && x > xdestination) x = xdestination;

		if(up && y > ydestination) y -= moveSpeed;
		else up = false;
		if(up && y < ydestination) y = ydestination;

		if(down && y < ydestination) y += moveSpeed;
		else down = false;
		if(down && y > ydestination) y = ydestination;

	}

	public void update() {

		// get next position
		if(move) getNextPosition();

		// check stop moving
		if(x == xdestination && y == ydestination) {
			left = right = up = down = move = false;
			row = y / tileSize;
			col = x / tileSize;
		}

		// update animation
		animation.update();

	}

	// Draws the entity.
	public void draw(Graphics2D g) {
		setMapPos();
		g.drawImage(
				animation.getImage(),
				x + xmap - w / 2,
				y + ymap - h / 2,
				null
				);
	}

}
