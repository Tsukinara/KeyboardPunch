import java.util.ArrayList;

public class Interpreter {

	private ArrayList<Integer> notes;
	private ArrayList<Integer> chord;

	public Interpreter() {
		notes = new ArrayList<Integer>();
		chord = new ArrayList<Integer>();
	}

	public void notePlayed(int note) {
		if(!areWeInThere(note)) {
			notes.add(note);
		}
	}

	public void noteReleased(int note) {
		if(areWeInThere(note)) {
			notes.remove(notes.indexOf(note));
		}
	}

	public boolean areWeInThere(int note) {
		return notes.contains(note);
	}

	public String getChord() {
		chord.clear();
		String chord = "";
		getMostRelevant();
		return chord;
	}

	private void getMostRelevant() {
		int min = (notes.size() < 4 ? notes.size() : 4);
		int counter = 0;
		sortNotes();
		while (chord.size() < min) {
			if(notes.size() == counter) break;
			int note = notes.get(counter) % 12;
			if (!chord.contains(note)) chord.add(note);
			counter++;
		}
		System.out.println(chord);
	}

	private void sortNotes() {
		int min = notes.get(0);
		for (int i = 0; i < notes.size(); i++) {
			min = i;
			for (int j = i + 1; j < notes.size(); j++) {
				if (notes.get(j) < notes.get(min)) min = j;
			}
			if (min != i) {
				int temp = notes.get(i);
				notes.set(i, notes.get(min));
				notes.set(min, temp);
			}
		}
	}

}