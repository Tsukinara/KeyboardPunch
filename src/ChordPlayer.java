import java.util.ArrayList;

public class ChordPlayer {
	private Game game;
	private MidiPlayer mp;
	private ArrayList<Chord> prev;
	private ArrayList<Integer> time;
	
	public ChordPlayer(Game g) {
		this.game = g;
		mp = new MidiPlayer();
		prev = new ArrayList<Chord>();
		time = new ArrayList<Integer>();
	}
	
	public void play_chord(Chord c) {
		if (c == null || c.getNotes() == null) return;
		for (int i = 0; i < c.getNotes().length; i++) {
			mp.play_note(c.getNotes()[i], 127);
			game.notePlayed(c.getNotes()[i], 1);
		}
		prev.add(c);
		time.add(4);
	}
	
	public void stop_chord(Chord c) {
		if (c == null || c.getNotes() == null) return;
		for (int i = 0; i < c.getNotes().length; i++) {
			mp.stop_note(c.getNotes()[i], 127);
			game.noteReleased(c.getNotes()[i]);
		}
	}
		
	public ArrayList<Chord> getPreviousChords() {
		return prev;
	}
	
	public ArrayList<Integer> getTimes() {
		return time;
	}
}