import java.util.ArrayList;

public class Interpreter {

	private ArrayList<Integer> notes;
	private ArrayList<Integer> chord;

	public Interpreter() {
		notes = new ArrayList<Integer>();
		chord = new ArrayList<Integer>();
	}

	public void notePlayed(int note) {
		if(!are_we_in_there(note)) {
			notes.add(note);
		}
	}

	public void noteReleased(int note) {
		if(are_we_in_there(note)) {
			notes.remove(notes.indexOf(note));
		}
	}

	public boolean are_we_in_there(int note) {
		return notes.contains(note);
	}

	public String get_chord() {
		chord.clear();
		String chordString = "";
		get_most_relevant();
		chordString = calculate_chord();
		return chordString;
	}

	private void get_most_relevant() {
		int min = (notes.size() < 4 ? notes.size() : 4);
		int counter = 0;
		sort_notes();
		while (chord.size() < min) {
			if(notes.size() == counter) break;
			int note = notes.get(counter) % 12;
			if (!chord.contains(note)) chord.add(note);
			counter++;
		}
		System.out.println(chord);
	}

	private void sort_notes() {
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

	private String calculate_chord() {
		String chordString = "";
		if (chord.size() == 1) {
			chordString += get_note(chord.get(0));
			chordString += "maj";
			return chordString;
		}
		if (chord.size() == 2) {
			int base = chord.get(0);
			int next = chord.get(1);
			if (next < base) next +=12;
			chordString+=get_note(base);
			switch (next - base) {
			case 1: 
				chordString = get_note(next);
				chordString += "maj7";	break;
			case 2:
				chordString = get_note(next);
				chordString += "7"; 	break;
			case 3: 
				chordString += "min"; 	break;
			case 4: case 7:
				chordString += "maj"; 	break;
			case 5: case 8:
				chordString = get_note(next);
				chordString += "maj"; 	break;
			case 6:
				chordString += "dim7";	break;
			case 9:
				chordString = get_note(next);
				chordString += "min";	break;
			case 10:
				chordString += "7";		break;
			case 11:
				chordString += "maj7";	break;				
			default: return "Unknown";
			}
		}
		if (chord.size() == 3) {
			int base = chord.get(0);
		}
		return chordString;
	}

	public static String get_note(int key) {
		String name;
		switch (key) {
		case 0:	 name = "C"; 	break;
		case 1:	 name = "C#";	break;
		case 2:	 name = "D";	break;
		case 3:	 name = "D#";	break;
		case 4:	 name = "E";	break;
		case 5:	 name = "F";	break;
		case 6:	 name = "F#";	break;
		case 7:	 name = "G";	break;
		case 8:	 name = "Ab";	break;
		case 9:	 name = "A";	break;
		case 10: name = "Bb";	break;
		case 11: name = "B";	break;
		default: name = "C";	break;
		}
		return name;
	}

}