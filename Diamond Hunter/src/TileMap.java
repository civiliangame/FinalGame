// The tile map class contains a loaded tile set
// and a 2d array of the map.
// Each index in the map corresponds to a specific tile.

import java.io.BufferedReader;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class TileMap {

	// position
	private int x;
	private int y;
	private int xdest;
	private int ydest;
	private int speed;
	private boolean moving;

	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	//Constructor
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		speed = 4;
	}

	
	//Loading tiles and making sure they have proper properties
	public void loadTiles(String s) {

		try {

			tileset = ImageIO.read(
					getClass().getResourceAsStream(s)
					);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(
						col * tileSize,
						0,
						tileSize,
						tileSize
						);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(
						col * tileSize,
						tileSize,
						tileSize,
						tileSize
						);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
				//tiles[1][col] = new Tile(subimage, Tile.NORMAL);
			}

			tiles[1][1] = tiles[0][1];
			tiles[1][2] = tiles[0][1];
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	//Read the map file
	public void loadMap(String s) {

		//Try catch loop to avoid any pesky errors due to IO
		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(in)
					);

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xmin = GamePanel.WIDTH - width;
			xmin = -width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymin = -height;
			ymax = 0;

			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public int getTileSize() { 
		return tileSize;
	}
	public int getx() { 
		return x;
	}
	public int gety() {
		return y;
	}
	public int getWidth() { 
		return width;
	}
	public int getHeight() { 
		return height;
	}
	public int getNumRows() { 
		return numRows;
	}
	public int getNumCols() { 
		return numCols;
	}
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}

	//Get the index
	public int getIndex(int row, int col) {
		return map[row][col];
	}

	//getter method for moving
	public boolean isMoving() { 
		return moving;
	}

	//Set the tiles
	public void setTile(int row, int col, int index) {
		map[row][col] = index;
	}

	//Replace tiles
	public void replace(int i1, int i2) {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(map[row][col] == i1) map[row][col] = i2;
			}
		}
	}

	//Set destination position
	public void setPosition(int x, int y) {
		xdest = x;
		ydest = y;
	}

	//Set current position
	public void setPositionImmediately(int x, int y) {
		this.x = x;
		this.y = y;
	}


	//Adjust the bounds
	public void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}


	//Updates the map
	public void update() {
		if(x < xdest) {
			x += speed;
			if(x > xdest) {
				x = xdest;
			}
		}
		if(x > xdest) {
			x -= speed;
			if(x < xdest) {
				x = xdest;
			}
		}
		if(y < ydest) {
			y += speed;
			if(y > ydest) {
				y = ydest;
			}
		}
		if(y > ydest) {
			y -= speed;
			if(y < ydest) {
				y = ydest;
			}
		}

		fixBounds();

		colOffset = -this.x / tileSize;
		rowOffset = -this.y / tileSize;

		if(x != xdest || y != ydest) moving = true;
		else moving = false;

	}


	//Draws the map
	public void draw(Graphics2D g) {

		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

			if(row >= numRows) break;

			for(int col = colOffset; col < colOffset + numColsToDraw; col++) {

				if(col >= numCols) break;
				if(map[row][col] == 0) continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g.drawImage(
						tiles[r][c].getImage(),
						x + col * tileSize,
						y + row * tileSize,
						null
						);

			}

		}

	}

}