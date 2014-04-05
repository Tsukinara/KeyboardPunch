import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	private static final long serialVersionUID = 6674913704274625426L;
	
	private TitlePanel tp;
	private Settings s;
	private PianoPanel p;
	private TimerPanel t;
	private ChordPanel c;
	public static GameData gamedata;
	public static Interpreter interpreter;
	
	public Game(GameData data, Interpreter i) {
		super();
		gamedata = data;
		interpreter = i;
		//i.setGD(data);
		JPanel panel = new JPanel(new GridBagLayout());		
		panel.setBackground(new Color(0x92c9ff));
		c = new ChordPanel();
		s = new Settings();
		t = new TimerPanel();
		p = new PianoPanel();
		tp = new TitlePanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(tp, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(c, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		panel.add(t, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,100,0,100);
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		panel.add(s, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 3.0;
		panel.add(p, gbc);
		
		getContentPane().add(panel);
		setMinimumSize(new Dimension(1366,768));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void notePlayed(int note, int code) {
		p.notePlayed(note, code);
	}
	
	public void noteReleased(int note) {
		p.noteReleased(note);
	}
	
	public void setChord(String s) {
		c.setChord(s);
	}
	
	public void setNext(ArrayList<String> l) {
		c.setNext(l);
	}
}