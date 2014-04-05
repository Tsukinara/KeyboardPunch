import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class ChordPanel extends JPanel {
	
	private Font thefuckingfont;
	private String chrod = "---";
	private String next = "Next:";
	private String seq = "A B C D E F G Lop.";
	
	public ChordPanel() {
		super();
		thefuckingfont = new Font("Trebuchet MS", Font.BOLD, 36);
		setBackground(new Color(0x92c9ff));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 144));
		g2d.drawString(chrod, getWidth()/2 - g2d.getFontMetrics().stringWidth(chrod)/2, 2*getHeight()/5);
		g2d.setFont(thefuckingfont);
		g2d.drawString(next, getWidth()/2 - g2d.getFontMetrics().stringWidth(next)/2, 2*getHeight()/3);
		g2d.drawString(seq, getWidth()/2 - g2d.getFontMetrics().stringWidth(seq)/2, 7*getHeight()/8);
	}
	
	public void setChord(String s) {
		chrod = s;
		repaint();
	}
}