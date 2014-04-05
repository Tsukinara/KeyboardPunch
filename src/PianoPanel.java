import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PianoPanel extends JPanel {
	final int width=600; //size of panel
	final int keylength=50; //sizeofkeys
	final int offset=20; //offset for formatting
	int widthWhite=width/45;
	int widthBlack=widthWhite*2/3;
	int space=widthWhite*7;
	public void paint(Graphics g) {
		super.paint(g);
	
		for (int i = 0; i < 45; i++) {
			drawWhite(i*widthWhite, offset, g);
		}

		//groups of 3
		 for (int i=(2*widthWhite-widthBlack/2);i<(width-3*widthWhite);i+=space){
		 drawBlack(i,offset,g);
		 drawBlack(i+widthWhite,offset,g);
		 drawBlack(i+2*widthWhite,offset,g);
		 }
		 
		 //groups of 2
		 for (int i=(6*widthWhite-widthBlack/2);i<width;i+=space){
			 drawBlack(i,offset,g);
			 drawBlack(i+widthWhite,offset,g);
		 }
		 
		 drawBlack(width-widthWhite*2-widthBlack/2,offset,g);

	}

	public void drawWhite(int x, int y, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, widthWhite, keylength);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, widthWhite, keylength);
	}

	public void drawBlack(int x, int y, Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, widthBlack, keylength*3/5);

	}
}