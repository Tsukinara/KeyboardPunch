import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Settings extends JPanel implements ActionListener, ChangeListener {

	private static final long serialVersionUID = -3752556673462306365L;
	private Font thefuckingfont;
	private JRadioButton maj;
	private JRadioButton min;
	private JSlider speed;
	private JSlider diff;
	private JSlider key;
	private JLabel speedL;
	private JLabel diffL;
	private JLabel keyL;
	private JButton reset;
	private JButton exit;
	private ChordPanel chord;
	private JRadioButton chordhelper;
	private JRadioButton computer;
	

	public Settings(ChordPanel c) {
		super();
		chord = c;
	
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		thefuckingfont = new Font("Trebuchet MS", Font.BOLD, 12);
		speed = new JSlider(60, 240, Game.gamedata.bpm);
		diff = new JSlider(1, 4, Game.gamedata.difficulty);
		key = new JSlider(0, 11, Game.gamedata.key);
		chordhelper = new JRadioButton("Chord Helper");
		computer = new JRadioButton("Computer Assisted Melody");
		speed.setBackground(new Color(0xbbddff));
		diff.setBackground(new Color(0xbbddff));
		key.setBackground(new Color(0xbbddff));
		speed.setLabelTable(speed.createStandardLabels(180, 60));
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
		keyL = new JLabel("Key: " + Interpreter.get_note(Game.gamedata.key));
		speedL.setFont(thefuckingfont);
		diffL.setFont(thefuckingfont);
		keyL.setFont(thefuckingfont);
		chordhelper.setFont(thefuckingfont);
		reset = new JButton("RESET");
		exit = new JButton("EXIT");
		reset.setFont(thefuckingfont);
		exit.setFont(thefuckingfont);
		speed.addChangeListener(this);
		diff.addChangeListener(this);
		key.addChangeListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		chordhelper.addActionListener(this);
		computer.addActionListener(this);
		maj = new JRadioButton("Major");
		min = new JRadioButton("Minor");
		maj.setBackground(new Color(0xbbddff));
		min.setBackground(new Color(0xbbddff));
		maj.addActionListener(this);
		min.addActionListener(this);
		ButtonGroup group = new ButtonGroup();
		group.add(maj);
		group.add(min);
		maj.setSelected(true);
		Insets i1 = new Insets(0, 10, 10, 10);
		Insets i2 = new Insets(0, 10, 20, 10);
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
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 10, 0, 10);
		gbc.gridy = 6;
		add(maj, gbc);
		gbc.gridx = 1;
		add(min, gbc);

		chordhelper.setBackground(new Color(0xbbddff));
		gbc.gridy = 2;
		chordhelper.setVisible(true);
		add(chordhelper, gbc);
		

		gbc.gridx = 0;
		gbc.insets = new Insets(20, 10, 0, 10);
		gbc.weightx = 1.0;
		gbc.gridy = 8;
		add(reset, gbc);
		gbc.weightx = 1.0;
		gbc.gridx = 1;
		add(exit, gbc);
		
		gbc.gridx=0;
		gbc.gridwidth=2;
		gbc.gridy=7;
		computer.setBackground(new Color(0xbbddff));
		add(computer,gbc);

		setBackground(new Color(0xbbddff));
	}

	
	


	public void actionPerformed(ActionEvent a) {
		Object b = a.getSource();
		if (b == maj) {
			Game.gamedata.set_majmin(true);
		}
		if (b == min) {
			Game.gamedata.set_majmin(false);
		}
		if (b == reset) {
			Game.gamedata.set_bpm(120);
			speed.setValue(120);
			speedL.setText("Speed: 120 bpm");
			Game.gamedata.set_difficulty(4);
			diff.setValue(4);
			Game.suggest = false;
			diffL.setText("Difficulty: 4");
			Game.gamedata.set_key(0);
			key.setValue(0);
			keyL.setText("Key: " + Interpreter.get_note(key.getValue()));
			Game.gamedata.set_majmin(true);
			maj.setSelected(true);
			chord.reset();
		}
		
		if (b == chordhelper) {
		
			if (chordhelper.isSelected()) {
		
				Game.suggest = true;
			}
			if (!chordhelper.isSelected()) {
			
				Game.p.eraseChord();
				Game.suggest = false;
			}
		}
		
		if (b == computer) {
			if (computer.isSelected()){
				//play computer melodies
			}
			else {
				//don't play melodies
			}
		}

		else if (b == exit) {
			System.exit(0);
		}
	}

	public void stateChanged(ChangeEvent c) {
		
		JSlider s = (JSlider) c.getSource();
		if (s == speed) {
			Game.gamedata.set_bpm(speed.getValue());
			speedL.setText("Speed: " + speed.getValue() + "  bpm");
		} else if (s == diff) {
			Game.gamedata.set_difficulty(diff.getValue());
			if (diff.getValue() <= 1) {
System.out.println("I'm sleepy");
				// stuff
			}if (diff.getValue() <= 2) {
				System.out.println("I'm sleepy for realz");
				// stuff
			} if (diff.getValue() <= 3) {
				System.out.println("I'm sleepy for realzies, yo");
				// stuff
			}  else {
				// stuff
				System.out.println("what am i doing with life");
			}
			diffL.setText("Difficulty: " + diff.getValue());
		} else if (s == key) {
			Game.gamedata.set_key(key.getValue());
			keyL.setText("Key: " + Interpreter.get_note(key.getValue()));
		}
		
	}
}