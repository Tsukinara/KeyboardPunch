import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Settings extends JPanel implements ActionListener, ChangeListener{
	
	private JSlider speed;
	private JSlider diff;
	private JSlider key;
	private JLabel speedL;
	private JLabel diffL;
	private JLabel keyL;
	private JButton restart;
	private JButton exit;
	
	private int speedVal = 120;
	private int diffVal = 1;
	private int	keyVal = 0;
	
	public Settings() {
		super();
		speed = new JSlider(60, 180, speedVal);
		diff = new JSlider(1, 4, diffVal);
		key = new JSlider(0, 11, keyVal);
//		speed.setPaintTrack(false);
//		diff.setPaintTrack(false);
//		key.setPaintTrack(false);
		speed.setPaintTicks(true);
		diff.setPaintTicks(true);
		key.setPaintTicks(true);
		speed.setMajorTickSpacing(10);
		diff.setMajorTickSpacing(1);
		key.setMajorTickSpacing(1);
		speedL = new JLabel("Speed: " + speedVal + " bpm");
		diffL = new JLabel("Difficulty: " + diffVal);
		keyL = new JLabel("Key: " + GameData.get_note(keyVal));
		restart = new JButton("RESTART");
		exit = new JButton("EXIT");
		speed.addChangeListener(this);
		diff.addChangeListener(this);
		key.addChangeListener(this);
		restart.addActionListener(this);
		exit.addActionListener(this);
		add(speedL);
		add(speed);
		add(diffL);
		add(diff);
		add(keyL);
		add(key);
		add(restart);
		add(exit);
	}
	
	public void actionPerformed(ActionEvent a) {
		JButton b = (JButton)a.getSource();
		if(b == restart) {
			
		}
		else if(b == exit) {
			System.exit(0);
		}
	}

	public void stateChanged(ChangeEvent c) {
		JSlider s = (JSlider)c.getSource();
		if(s == speed) {
			speedL.setText("Speed: " + speed.getValue() + "  bpm");
		}
		else if(s == diff) {
			diffL.setText("Difficulty: " + diff.getValue());
		}
		else if(s == key) {
			keyL.setText("Key: " + GameData.get_note(key.getValue()));
		}
	}
}