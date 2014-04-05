

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	
	private Settings settings;
	private PianoPanel p;
	private TimerPanel t;
	public static GameData gamedata;
	
	public Game(GameData data) {
		super();
		gamedata = data;
		setSize(600,800);
		JPanel panel = new JPanel(new GridLayout(3,1));
		settings = new Settings();
	//	p=new PianoPanel();
		t=new TimerPanel();
	//panel.add(p);
		panel.add(t);
		//panel.add(settings);	
		getContentPane().add(panel);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}