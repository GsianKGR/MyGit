package lightsout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Exe {
	public static void main(String[] args) {
		GameMain game = new GameMain("LightsOut!");
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
		game.setResizable(false);
		ImageIcon icon = new ImageIcon("../JLightsOut/icon/title.png");
		game.setIconImage(icon.getImage());
	}
}