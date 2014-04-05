import java.util.ArrayList;

public class Interpreter {
	
	private ArrayList<Integer> notes;
	
	public Interpreter() {
		new ArrayList<Integer>();
	}
	
	public void notePlayed(int note) {
		notes.add(note);
	}
	
	public void noteReleased(int note) {
		notes.remove(notes.indexOf(note));
	}
	
	public String getChord() {
		String chord = "";
		return chord;
	}
	
}