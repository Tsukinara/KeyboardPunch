

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	
	private Settings settings;
	private Timing timing;
	private PianoPanel p;
	public Game() {
		super();
		setSize(600,700);
		JPanel panel = new JPanel(new BorderLayout());
		settings = new Settings();
		timing = new Timing();
		p=new PianoPanel();
		panel.add(p,BorderLayout.CENTER);
		panel.add(settings,BorderLayout.PAGE_END);
		
	getContentPane().add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}