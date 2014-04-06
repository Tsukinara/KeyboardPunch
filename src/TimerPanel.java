import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class TimerPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3875360370099517808L;
	public Timer timer;
	private Timer fading;
	private Color fade;
	private int beat = 0;
	private ChordPlayer cp;

	public TimerPanel(){
		super();
		fade = new Color(0x323232);
		timer = new Timer(500, this);
		fading = new Timer(10,this);
		timer.start();
		fading.start();
		setBackground(new Color(250,250,255));
	}
	
	public void setCP(ChordPlayer cp) {this.cp = cp;}

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 144));
		for(int i = 0; i < 4; i++) {
			if(i == beat)
				g2d.setColor(fade);
			else
				g2d.setColor(new Color(0xaaaaaa));
			g2d.drawString(String.valueOf(i + 1), (i+1) * getWidth() / 5 - g2d.getFontMetrics().stringWidth(String.valueOf(i + 1))/2, getHeight()/2);
		}
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 72));
		g2d.drawString(String.valueOf(Game.gamedata.get_bpm()) + " bpm", getWidth()/2 - g2d.getFontMetrics().stringWidth(String.valueOf(Game.gamedata.get_bpm()) + " bpm")/2, 7*getHeight()/8);
	}

	public void actionPerformed(ActionEvent e) {
		Timer t = (Timer)e.getSource();
		if(t == timer) {
			beat = (beat + 1) % 4;
			Chord c = new Chord(Game.interpreter.getChordName(), 0);
			cp.play_chord(c);
			timer.setDelay((int) ((1.0/(Game.gamedata.get_bpm()/60.0))*1000.0));
			fade = new Color(0x323232);
			fading.setDelay((int) (1000.0/Game.gamedata.get_bpm()));
		}
		else if(t == fading) {
			int red = fade.getRed() + 2;
			int blue = fade.getBlue() + 2;
			int green = fade.getGreen() + 2;
			if(red > 170)
				red = 170;
			if(blue > 170)
				blue = 170;
			if(green > 170)
				green = 170;
			fade = new Color(red, green, blue);
		}
		repaint();
	}
}
