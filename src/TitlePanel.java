import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitlePanel extends JPanel {
	
	private String title = "KeyboardPuuuuuuuuuuuuuuuuuuunch";
	
	public TitlePanel() {
		super();
		setBackground(new Color(0x003864));

	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawLine(0, getHeight(), getWidth(), getHeight());
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 72));
		g2d.setColor(Color.white);
		g2d.drawString(title, getWidth()/2 - g2d.getFontMetrics().stringWidth(title)/2, getHeight()/2 + g2d.getFontMetrics().getMaxAscent()/2 - 5);
	}
}