import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ChordPanel extends JPanel {
	
	private static final long serialVersionUID = -932172714303934216L;
	private String chrod = "---";
	private String next = "Suggested Chords:";
	private ArrayList<String> chords;
	
	public ChordPanel() {
		super();
		chords = new ArrayList<String>();
		setBackground(new Color(0xbbddff));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 144));
		g2d.drawString(chrod, getWidth()/2 - g2d.getFontMetrics().stringWidth(chrod)/2, 2*getHeight()/5);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		g2d.setColor(new Color(0x76c2f9));//change pls
		g2d.fillRect(0, getHeight()/2 + 7, getWidth(), 3*getHeight()/5 + 17 - getHeight()/2);
		g2d.setColor(Color.black);
		g2d.drawString(next, getWidth()/2 - g2d.getFontMetrics().stringWidth(next)/2, 3*getHeight()/5);
		if(chords.size() > 6) {
			for(int i = 0; i < 6; i++) {
				g2d.drawString(chords.get(i), i*getWidth()/6 + 10, 3*getHeight()/4);
			}
			for(int i = 6; i < chords.size(); i++) {
				g2d.drawString(chords.get(i), (i - 6)*getWidth()/6 + 10, 7*getHeight()/8);
			}
		}
		else if(chords.size() > 0) {
			for(int i = 0; i < chords.size(); i++) {
				g2d.drawString(chords.get(i), i*getWidth()/6 + 10, 3*getHeight()/4);
			}
		}
	}
	
	public void setChord(String s) {
		chrod = s;
		repaint();
	}
	
	public void setNext(ArrayList<String> l) {
		chords = l;
		repaint();
	}
	
	public void reset() {
		chords.clear();
		repaint();
	}
}