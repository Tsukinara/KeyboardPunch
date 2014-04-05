import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChordPanel extends JPanel {
	
	private JLabel chrod;
	private JLabel next;
	private JLabel seq;
	
	public ChordPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		chrod = new JLabel("G Maj");
		next = new JLabel("Next:");
		seq = new JLabel("A B C D E F G");
		chrod.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		next.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		add(chrod);
		add(next);
		add(seq);
	}
}