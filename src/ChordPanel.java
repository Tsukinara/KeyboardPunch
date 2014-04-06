import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ChordPanel extends JPanel {
	
	private static final long serialVersionUID = -932172714303934216L;
	private Font thefuckingfont;
	private String chrod = "GMaj";
	private String next = "Suggested Chords:";
	private String seq = "";
	private ArrayList<String> chords;
	
	public ChordPanel() {
		super();
		thefuckingfont = new Font("Trebuchet MS", Font.BOLD, 24);
		chords = new ArrayList<String>();
		setBackground(new Color(0xbbddff));
		//setBackground(new Color(0x92c9ff));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 144));
		g2d.drawString(chrod, getWidth()/2 - g2d.getFontMetrics().stringWidth(chrod)/2, 2*getHeight()/5);
		g2d.setFont(thefuckingfont);
		g2d.drawString(next, getWidth()/2 - g2d.getFontMetrics().stringWidth(next)/2, 3*getHeight()/5);
		if(chords.size() > 0){
			for(int i = 0; i < 6; i++) {
				seq += chords.get(i) + " ";
			}
			g2d.drawString(seq, getWidth()/2 - g2d.getFontMetrics().stringWidth(seq)/2, 3*getHeight()/4);
			if(chords.size() > 6) {
				for(int i = 0; i < chords.size() - 6; i++) {
					seq += chords.get(i) + " ";
				}
				g2d.drawString(seq, getWidth()/2 - g2d.getFontMetrics().stringWidth(seq)/2, 7*getHeight()/8);
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
		seq = "";
		repaint();
	}
}