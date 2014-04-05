import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Settings extends JPanel implements ActionListener, ChangeListener{
	
	private static final long serialVersionUID = -3752556673462306365L;
	private Font thefuckingfont;
	private JSlider speed;
	private JSlider diff;
	private JSlider key;
	private JLabel speedL;
	private JLabel diffL;
	private JLabel keyL;
	private JButton restart;
	private JButton exit;
	
	public Settings() {
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		thefuckingfont = new Font("Trebuchet MS", Font.BOLD, 12);
		speed = new JSlider(60, 180, Game.gamedata.bpm);
		diff = new JSlider(1, 4, Game.gamedata.difficulty);
		key = new JSlider(0, 11, Game.gamedata.key);
		speed.setBackground(new Color(0x92c9ff));
		diff.setBackground(new Color(0x92c9ff));
		key.setBackground(new Color(0x92c9ff));
		speed.setLabelTable(speed.createStandardLabels(120, 60));
		diff.setLabelTable(diff.createStandardLabels(1, 1));
		speed.setPaintLabels(true);
		diff.setPaintLabels(true);
		speed.setPaintTicks(true);
		diff.setPaintTicks(true);
		key.setPaintTicks(true);
		speed.setMajorTickSpacing(10);
		diff.setMajorTickSpacing(1);
		key.setMajorTickSpacing(1);
		speed.setFont(thefuckingfont);
		diff.setFont(thefuckingfont);
		key.setFont(thefuckingfont);
		speedL = new JLabel("Speed: " + Game.gamedata.bpm + " bpm");
		diffL = new JLabel("Difficulty: " + Game.gamedata.difficulty);
		keyL = new JLabel("Key: " + GameData.get_note(Game.gamedata.key));
		speedL.setFont(thefuckingfont);
		diffL.setFont(thefuckingfont);
		keyL.setFont(thefuckingfont);		
		restart = new JButton("RESTART");
		exit = new JButton("EXIT");
		restart.setFont(thefuckingfont);
		exit.setFont(thefuckingfont);
		speed.addChangeListener(this);
		diff.addChangeListener(this);
		key.addChangeListener(this);
		restart.addActionListener(this);
		exit.addActionListener(this);
		Insets i1 = new Insets(0,10,10,10);
		Insets i2 = new Insets(0,10,20,10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = i1;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(speedL, gbc);
		gbc.insets = i2;
		gbc.gridy = 1;
		add(speed, gbc);
		gbc.insets = i1;
		gbc.gridy = 2;
		add(diffL, gbc);
		gbc.insets = i2;
		gbc.gridy = 3;
		add(diff, gbc);
		gbc.insets = i1;
		gbc.gridy = 4;
		add(keyL, gbc);
		gbc.insets = i2;
		gbc.gridy = 5;
		add(key, gbc);
		gbc.insets = new Insets(0,10,0,10);
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		gbc.gridy = 6;
		add(restart, gbc);
		gbc.weightx = 1.0;
		gbc.gridx = 1;
		add(exit, gbc);
		setBackground(new Color(0x92c9ff));
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
			Game.gamedata.set_bpm(speed.getValue());
			speedL.setText("Speed: " + speed.getValue() + "  bpm");
		}
		else if(s == diff) {
			Game.gamedata.set_difficulty(diff.getValue());
			diffL.setText("Difficulty: " + diff.getValue());
		}
		else if(s == key) {
			keyL.setText("Key: " + GameData.get_note(key.getValue()));
		}
	}
}