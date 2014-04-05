import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PianoPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3875360370099517808L;
	
	final int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(); //size of panel
	final int keylength = 50; //sizeofkeys
	final int offset = width / 30; //offset for formatting
	int widthWhite = width / 45;
	int widthBlack = widthWhite * 2 / 3;
	int space = widthWhite * 7;
	
	
	
	public PianoPanel() {
		super();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
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
	
	
	public void notePlayed(int note){
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}