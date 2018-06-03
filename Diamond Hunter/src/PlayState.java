
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.ArrayList;

//Playing the game
public class PlayState extends GameState {

	// player
	private Player player;

	// tilemap
	private TileMap tileMap;

	// bones
	private ArrayList<Bone> bones;


	private int eventTick;
	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize; 

	// hud
	private Hud hud;

	// events
	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;

	// transition box
	private ArrayList<Rectangle> boxes;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		// create lists
		bones = new ArrayList<Bone>();

		// load map
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");

		// create player
		player = new Player(tileMap);

		// fill lists
		populateBones();

		// initialize player
		player.setTilePosition(17, 17);
		player.setTotalBones(bones.size());

		// set up camera position
		sectorSize = GamePanel.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);

		// load hud
		hud = new Hud(player, bones);

		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();

	}

	private void populateBones() {

		Bone d;

		d = new Bone(tileMap);
		d.setTilePosition(20, 20);
		d.addChange(new int[] { 23, 19, 1 });
		d.addChange(new int[] { 23, 20, 1 });
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(12, 36);
		d.addChange(new int[] { 31, 17, 1 });
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(28, 4);
		d.addChange(new int[] {27, 7, 1});
		d.addChange(new int[] {28, 7, 1});
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(4, 34);
		d.addChange(new int[] { 31, 21, 1 });
		bones.add(d);

		d = new Bone(tileMap);
		d.setTilePosition(28, 19);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(35, 26);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(38, 36);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(27, 28);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(20, 30);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(14, 25);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(4, 21);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(9, 14);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(4, 3);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(20, 14);
		bones.add(d);
		d = new Bone(tileMap);
		d.setTilePosition(13, 20);
		bones.add(d);

	}


	public void update() {

		// check keys
		handleInput();

		// check events
		if(eventStart) eventStart();
		if(eventFinish) eventFinish();

		if(player.numBones() == player.getTotalBones()) {
			eventFinish = blockInput = true;
		}

		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();

		if(tileMap.isMoving()) return;

		// update player
		player.update();

		// update bones
		for(int i = 0; i < bones.size(); i++) {

			Bone d = bones.get(i);
			d.update();

			// player collects bone
			if(player.intersects(d)) {

				// remove from list
				bones.remove(i);
				i--;

				// increment amount of collected bones
				player.collectedBone();


				// make any changes to tile map
				ArrayList<int[]> ali = d.getChanges();
				for(int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
			}
		}
	}

	public void draw(Graphics2D g) {

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);

		// draw bones
		for(Bone d : bones) {
			d.draw(g);
		}


		// draw hud
		hud.draw(g);

		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}

	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}
		if(blockInput) return;
		if(Keys.isDown(Keys.LEFT)) player.setLeft();
		if(Keys.isDown(Keys.RIGHT)) player.setRight();
		if(Keys.isDown(Keys.UP)) player.setUp();
		if(Keys.isDown(Keys.DOWN)) player.setDown();
		if(Keys.isPressed(Keys.SPACE)) player.setAction();
	}

	//===============================================

	private void eventStart() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1 && eventTick < 32) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					r.x -= 4;
				}
				else {
					r.x += 4;
				}
			}
		}
		if(eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}

	private void eventFinish() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
				else boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				}
				else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
	}

}
