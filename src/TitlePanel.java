import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitlePanel extends JPanel {
	
	private JLabel label;
	
	public TitlePanel() {
		super();
		label = new JLabel("KeyboardPunch", SwingConstants.CENTER);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		setBackground(new Color(0x003864));
		add(label);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawLine(0, getHeight(), getWidth(), getHeight());
	}
}