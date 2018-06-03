
import javax.swing.JFrame;
//Game. This is the main class that runs. 
public class Game {
	
	public static void main(String[] args) {
		
		//Open up a JFrame and configure 
		JFrame window = new JFrame("Nolly's Bone Chase");
		
		window.add(new GamePanel());

		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setResizable(false);
		window.pack();
		
		
	}
	
}
