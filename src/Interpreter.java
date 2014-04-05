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
		this.chord.clear();
		String chordString;
		get_most_relevant();
		chordString = calculate_chord(this.chord);
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

	private String calculate_chord(ArrayList<Integer> chord) {
		String chordString = "";
		if (chord.size() == 0) return "---";
		if (chord.size() == 1) {
			chordString += get_note(chord.get(0));
			chordString += "maj";
			return chordString;
		}
		if (chord.size() == 2) {
			int base = chord.get(0);
			int next = chord.get(1);
			if (next < base) next +=12;
			return two_note_chord(base, next);			
		}
		if (chord.size() == 3) {
			int base = chord.get(0);
			int next = chord.get(1);
			if (next < base) next += 12;
			int last = chord.get(2);
			while (last < next) last += 12;
			return three_note_chord(base, next, last);
		}
		int base = chord.get(0);
		int next = chord.get(1);
		int third = chord.get(2);
		int last = chord.get(3);
		while (next < base) next +=12;
		while (third < next) third +=12;
		while (last < third) last +=12;
		return four_note_chord(base, next, third, last);
	}

	private String two_note_chord(int base, int next) {
		String chordString = get_note(base);
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
		default: return "???";
		}
		return chordString;
	}
	
	private String three_note_chord(int base, int next, int last) {
		int baseclone = base;
		while (baseclone < last) baseclone+=12;
		//check for dims
		if (next - base == 3 && last - next == 3) return get_note(base) + "dim";
		if (next - base == 3 && last - next == 6) return get_note(last%12) + "dim";
		if (next - base == 6 && last - next == 3) return get_note(next%12) + "dim";
		
		//look for majors
		if (next - base == 4 && last - next == 3) return get_note(base) + "maj";
		if (next - base == 3 && last - next == 5) return get_note(last%12) + "maj";
		if (next - base == 5 && last - next == 4) return get_note(next%12) + "maj";
		
		//look for minors
		if (next - base == 3 && last - next == 4) return get_note(base) + "min";
		if (next - base == 4 && last - next == 5) return get_note(last%12) + "min";
		if (next - base == 5 && last - next == 3) return get_note(next%12) + "min";
		
		//check for 7ths
		
		//check major minor
		
		return "???";
	}

	private String four_note_chord(int base, int next, int third, int last) {
		String chordString = get_note(base);
		int baseclone = base;
		while (baseclone < last) baseclone+=12;
		//check for diminished chords first
		if(next - base == 3 && third - next == 3 && last - third == 3) return chordString + "dim7";

		//check overall validity next
		int numClose = 0;
		if (next-base == 1 || next-base == 2) numClose++;
		if (third-next == 1 || third-next == 2) numClose++;
		if (last-third == 1 || last-third == 2) numClose++;
		if (baseclone-last == 1 || baseclone-last == 2) numClose++;
		if(numClose != 1) return "???";

		//check for major 7ths
		if (next-base == 1 && third == next+4 && last == third+3) return get_note(next%12) + "maj7";
		if (third-next == 1 && next == base+4 && last == third+4) return get_note(third%12) + "maj7";
		if (last-third == 1 && base == next-3 && next == third-4) return get_note(last%12) + "maj7";
		if (baseclone-last == 1 && next == base+4 && third == next+3) return chordString + "maj7";

		//check for minor 7ths
		if (next-base == 2 && third == next+3 && last == third+4) return get_note(next%12) + "min7";
		if (third-next == 2 && next == base+3 && last == third+3) return get_note(third%12) + "min7";
		if (last-third == 2 && base == next-4 && next == third-3) return get_note(last%12) + "min7";
		if (baseclone-last == 2 && next == base+3 && third == next+4) return chordString + "min7";

		//check for major-minor 7ths
		if (next-base == 2 && third == next+4 && last == third+3) return get_note(next%12) + "7";
		if (third-next == 2 && next == base+3 && last == third+4) return get_note(third%12) + "7";
		if (last-third == 2 && base == next-3 && next == third-3) return get_note(last%12) + "7";
		if (baseclone-last == 2 && next == base+4 && third == next+3) return chordString + "7";

		return "???";
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