import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class TimerPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3875360370099517808L;
	private Timer timer;
	private int beat = 0;
	private String title = "Metronome";
	
	public TimerPanel(){
		super();
		timer = new Timer(500, this);
		timer.start();
		setBackground(new Color(240,240,240));
	}
	
    public void paint(Graphics g){
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D)g;
    	g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 48));
    	for(int i = 0; i < 4; i++) {
			if(i == beat)
				g2d.setColor(new Color(0x323232));
			else
				g2d.setColor(new Color(0xaaaaaa));
			g2d.drawString(String.valueOf(i + 1), i * getWidth() / 4 + getWidth() / 8, getHeight()/2);
		}
    	g2d.setColor(Color.black);
    	g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
    	g2d.drawString(title, getWidth() - g2d.getFontMetrics().stringWidth(title),3*getHeight()/4);
    	g2d.drawString(String.valueOf(Game.gamedata.get_bpm()) + " bpm", getWidth() - g2d.getFontMetrics().stringWidth(title), 7*getHeight()/8);
    }//end paint
    
	public void actionPerformed(ActionEvent e) {
		beat = (beat + 1) % 4;
		timer.setDelay((int) ((1.0/(Game.gamedata.get_bpm()/60.0))*1000.0));
		repaint();
	}
}
