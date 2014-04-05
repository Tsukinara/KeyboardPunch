import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PianoPanel extends JPanel {
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < 45; i++) {
			drawWhite(i*10, 0, g);
		}

		//groups of 3
		 for (int i=17;i<420;i+=70){
		 drawBlack(i,0,g);
		 drawBlack(i+10,0,g);
		 drawBlack(i+20,0,g);
		 }
		 
		 //groups of 2
		 for (int i=57;i<450;i+=70){
			 drawBlack(i,0,g);
			 drawBlack(i+10,0,g);
		 }
		 
		 drawBlack(437,0,g);

	}

	public void drawWhite(int x, int y, Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 10, 50);
	}

	public void drawBlack(int x, int y, Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 6, 30);

	}
}