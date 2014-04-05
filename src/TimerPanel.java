import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class TimerPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3875360370099517808L;
	private Timer timer;
	private int beat = 0;
	
	public TimerPanel(){
		super();
		timer = new Timer(500, this);
		timer.start();
	}
	
    public void paint(Graphics g){
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D)g;
    	
    	for(int i = 0; i < 4; i++) {
			if(i == beat)
				g2d.setColor(new Color(0x323232));
			else
				g2d.setColor(new Color(0x787878));
			g2d.drawString(String.valueOf(i + 1), i * getWidth() / 4 + getWidth() / 8, getHeight()/2);
		}
	
    }//end paint
    
	public void actionPerformed(ActionEvent e) {
		beat = (beat + 1) % 4;
		timer.setDelay((int) ((1.0/(Game.gamedata.get_bpm()/60.0))*1000.0));
		repaint();
	}
}
