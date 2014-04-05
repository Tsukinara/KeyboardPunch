import javax.swing.JFrame;

public class Game extends JFrame {
	
	private Settings settings;
	private Timing timing;
	public Game() {
		super();
		settings = new Settings();
		timing = new Timing();
		add(settings);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}