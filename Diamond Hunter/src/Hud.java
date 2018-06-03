

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


//Keeps track of important information
public class Hud {
	
	//Variables
	private int yoffset;
	
	private BufferedImage bone;
	
	private Player player;
	
	private int numBones;
	
	private Font font;
	private Color textColor; 
	
	
	//Constructor
	public Hud(Player p, ArrayList<Bone> d) {
		
		player = p;
		numBones = d.size();
		yoffset = GamePanel.HEIGHT;
		
		bone = Content.BONE[0][0];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(47, 64, 126);
		
	}
	
	
	//Draw everything
	public void draw(Graphics2D g) {
		
		
		// draw bone amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.numBones() + "/" + numBones;
		Content.drawString(g, s, 40, yoffset + 3);
		if(player.numBones() >= 10) g.drawImage(bone, 80, yoffset, null);
		else g.drawImage(bone, 72, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}
		
		
		
	}
	
}
