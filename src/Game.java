

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	
	private Settings settings;
	private PianoPanel p;
	public static GameData gamedata;
	
	public Game(GameData data) {
		super();
		gamedata = data;
		setSize(600,800);
		JPanel panel = new JPanel(new BorderLayout());
		settings = new Settings();
		p=new PianoPanel();
		panel.add(p,BorderLayout.CENTER);
		panel.add(settings,BorderLayout.PAGE_END);	
		getContentPane().add(panel);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}