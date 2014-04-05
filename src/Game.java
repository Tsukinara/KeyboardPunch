import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	private static final long serialVersionUID = 6674913704274625426L;
	
	private Settings s;
	private PianoPanel p;
	private TimerPanel t;
	private ChordPanel c;
	public static GameData gamedata;
	
	public Game(GameData data) {
		super();
		gamedata = data;
		JPanel panel = new JPanel(new GridBagLayout());
		c = new ChordPanel();
		s = new Settings();
		t = new TimerPanel();
		p = new PianoPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 2.0;
		gbc.weighty = 2.0;
		panel.add(c, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 2.0;
		gbc.weighty = 2.0;
		panel.add(t, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 4.0;
		gbc.weighty = 1.0;
		panel.add(p, gbc);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.weightx = 0.0;
		gbc.weighty = 5.0;
		panel.add(s, gbc);
		getContentPane().add(panel);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void notePlayed(int note) {
		p.notePlayed(note);
	}
	
	public void noteReleased(int note) {
		p.noteReleased(note);
	}
}