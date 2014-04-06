import javax.swing.Timer;

public class ChordPlayer implements Runnable {
	Timer timer;
	GameData gd;
	MidiPlayer mp;
	Interpreter i;
	
	public ChordPlayer(Game g) {
		mp = new MidiPlayer();
		gd = g.getGameData();
		timer = g.getTimerPanel().timer;
		i = g.getInterpreter();
	}
	
	public void run() {
		Chord c = i.getChord();
		for (int i = 0; i < c.getNotes().length; i++) mp.play_note(c.getNotes()[i] + 60, 127);
		
	}
	
}