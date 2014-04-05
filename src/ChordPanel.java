import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChordPanel extends JPanel {
	
	private JLabel chrod;
	private JLabel next;
	private JLabel seq;
	
	public ChordPanel() {
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		chrod = new JLabel("G Maj");
		next = new JLabel("Next:");
		seq = new JLabel("A B C D E F G");
		chrod.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		next.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		seq.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(chrod, gbc);
		gbc.gridy = 1;
		add(next, gbc);
		gbc.gridy = 2;
		add(seq, gbc);
		setBackground(new Color(240,240,240));
	}
}