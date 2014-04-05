import java.util.ArrayList;

public class Interpreter {

	private ArrayList<Integer> notes;
	private ArrayList<Integer> chord;
	GameData gd;
	int currentChord, currentType;
	private String lolstring = "You messed up lol.";

	public Interpreter() {
		notes = new ArrayList<Integer>();
		chord = new ArrayList<Integer>();
	}
	
	public void setGD(GameData gd) {
		this.gd = gd;
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
	
	public ArrayList<String> get_next_chords() {
		ArrayList<String> next_chords = new ArrayList<String>();
		setConstants(get_chord());
		int key = gd.get_key();
		boolean majmin = gd.getMajMin();
		if (majmin)
			next_chords = majorNextChords(key);
		else
			next_chords = minorNextChords(key);
		return next_chords;
	}
	
	private ArrayList<String> majorNextChords(int key) {
		ArrayList<String> options = new ArrayList<String>();
		if (currentChord < key) currentChord+=12;
		switch (currentChord - key) {
		case 0:
			if (currentType == 0) addValues(options, new int[]{2, 1, 3, 0, 4, 0, 4, 1, 4, 6, 5, 0, 5, 1, 7, 0, 8, 0, 9, 1, 11, 2, 11, 3});
			else if (currentType == 6) addValues(options, new int[]{5, 0});
			else if (currentType == 7) addValues(options, new int[]{0, 0});
			else options.add(lolstring);
			break;
		case 1:
			if (currentType == 2 || currentType == 3) addValues(options, new int[]{2, 1});
			else options.add(lolstring);
			break;
		case 2:
			if (currentType == 0 || currentType == 6) addValues(options, new int[]{7, 0, 7, 7});
			else if (currentType == 1 || currentType == 5) addValues(options, new int[]{7, 0, 7, 7, 11, 1, 11, 3});
			else if (currentType == 2) addValues(options, new int[]{9, 1, 0, 0, 0, 7});
			else options.add(lolstring); 
			break;
		case 3:
			if (currentType == 0) addValues(options, new int[]{0, 0, 5, 0});
			else options.add(lolstring);
			break;
		case 4:
			if (currentType == 4 || currentType == 6 || currentType == 0) addValues(options, new int[]{9, 1});
			else if (currentType == 1) addValues(options, new int[]{9, 1, 5, 0, 2, 1, 2, 5});
			else if (currentType == 7) addValues(options, new int[]{4, 0});
			else options.add(lolstring);
			break;
		case 5:
			if (currentType == 0) addValues(options, new int[]{2, 1, 2, 5, 0, 0, 0, 7, 7, 0, 7, 6, 11, 2, 11, 3});
			else if (currentType == 7) addValues(options, new int[]{5, 0});
			else if (currentType == 1) addValues(options, new int[]{0, 0, 0, 7});
			else if (currentType == 4) addValues(options, new int[]{7, 0, 7, 7});
			else if (currentType == 2 || currentType == 3) addValues(options, new int[]{9, 1, 0, 0, 0, 7});
			else options.add(lolstring);
			break;
		case 6:
			options.add(lolstring);
			break;
		case 7:
			if (currentType == 0 || currentType == 6) addValues(options, new int[]{4, 0, 4, 6, 0, 0, 0, 7, 9, 1, 9, 5});
			else if (currentType == 7) addValues(options, new int[]{7, 0});
			else options.add(lolstring);
			break;
		case 8:
			if (currentType == 2 || currentType == 3)  addValues(options, new int[]{9, 1, 0, 0, 0, 7});
			else if (currentType == 0) addValues(options, new int[]{10, 0});
			else options.add(lolstring);
			break;
		case 9:
			if (currentType == 0 || currentType == 6) addValues(options, new int[]{2, 1, 2, 5});
			else if (currentType == 1 || currentType == 5) addValues(options, new int[]{5, 0, 5, 4, 2, 1, 2, 5});
			else options.add(lolstring);
			break;
		case 10:
			if (currentType == 0) addValues(options, new int[]{0, 0, 0, 7});
			else options.add(lolstring);
			break;
		case 11:
			if (currentType == 2 || currentType == 3)  addValues(options, new int[]{9, 1, 0, 0, 0, 7});
			else options.add(lolstring);
			break;
		default:
			options.add(lolstring);
		}
		return options;
	}
	
	private ArrayList<String> minorNextChords(int key) {
		ArrayList<String> options = new ArrayList<String>();
		if (currentChord < key) currentChord+=12;
		switch (currentChord - key) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		case 11:
			break;
		default:
			options.add(lolstring);
		}
		return options;
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
	}

	private void sort_notes() {
		if (!notes.isEmpty()) {
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
			chordString = get_note(next%12);
			chordString += "maj7";	break;
		case 2:
			chordString = get_note(next%12);
			chordString += "7"; 	break;
		case 3: 
			chordString += "min"; 	break;
		case 4: case 7:
			chordString += "maj"; 	break;
		case 5: case 8:
			chordString = get_note(next%12);
			chordString += "maj"; 	break;
		case 6:
			chordString += "dim7";	break;
		case 9:
			chordString = get_note(next%12);
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
		if (next - base == 7 && last - next == 9) return get_note(base) + "maj";

		//look for minors
		if (next - base == 3 && last - next == 4) return get_note(base) + "min";
		if (next - base == 4 && last - next == 5) return get_note(last%12) + "min";
		if (next - base == 5 && last - next == 3) return get_note(next%12) + "min";
		if (next - base == 7 && last - next == 8) return get_note(base) + "min";

		//check for 7ths
		if (next - base == 1 && (last - next == 4 || last - next == 7)) return get_note(next%12) + "maj7";
		if ((next - base == 4 || next - base ==  7) && last - next == 1) return get_note(last%12) + "maj7";
		if (last - base == 11 && (next - base == 4 || next - base == 7)) return get_note(base%12) + "maj7";
		
		if (next - base == 2 && (last - next == 4 || last - next == 7)) return get_note(next%12) + "7";
		if ((next - base == 4 || next - base ==  7) && last - next == 2) return get_note(last%12) + "7";
		if (last - base == 10 && (next - base == 4 || next - base == 7)) return get_note(base%12) + "7";
		
		if (next - base == 2 && (last - next == 3 || last - next == 7)) return get_note(next%12) + "min7";
		if ((next - base == 3 || next - base ==  7) && last - next == 2) return get_note(last%12) + "min7";
		if (last - base == 10 && (next - base == 3 || next - base == 7)) return get_note(base%12) + "min7";		
		
		//check for sus4
		if (next - base == 2 && last - next == 5) return get_note(last%12) + "sus4";
		if (next - base == 5 && last - next == 5) return get_note(next%12) + "sus4";
		if (next - base == 5 && last - next == 2) return get_note(base) + "sus4";

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
	
	public static String get_type(int type) {
		String name;
		switch (type) {
		case 0: name="maj";		break;
		case 1: name="min";		break;
		case 2: name="dim";		break;
		case 3: name="dim7";	break;
		case 4: name="maj7";		break;
		case 5: name="min7";	break;
		case 6: name="7";		break;
		case 7: name="sus4";	break;
		default: name="maj";	break;
		}
		return name;
	}
	
	private void setConstants(String chordName) {
		String type;
		if(chordName.charAt(1) == '#' || chordName.charAt(1) == 'b') {
			type = chordName.substring(2, chordName.length());
			switch(chordName.charAt(0)) {
			case 'C': currentChord = 1; break;
			case 'D': currentChord = 3; break;
			case 'F': currentChord = 6; break;
			case 'A': currentChord = 8; break;
			default: currentChord = 11; break;
			}
		} else {
			type = chordName.substring(1, chordName.length());
			switch(chordName.charAt(0)) {
			case 'C': currentChord = 0; break;
			case 'D': currentChord = 2; break;
			case 'E': currentChord = 4; break;
			case 'F': currentChord = 5; break;
			case 'G': currentChord = 7; break;
			case 'A': currentChord = 9; break;
			case 'B': currentChord = 11; break;
			}
		}
		if (type.equals("maj")) currentType = 0;
		if (type.equals("min")) currentType = 1;
		if (type.equals("dim")) currentType = 2;
		if (type.equals("dim7")) currentType = 3;
		if (type.equals("maj7")) currentType = 4;
		if (type.equals("min7")) currentType = 5;
		if (type.equals("7")) currentType = 6;
		if (type.equals("sus4")) currentType = 7;
	}
	
	private ArrayList<String> addValues(ArrayList<String> orig, int[] contents) {
		for (int i = 0; i < contents.length; i+=2) {
			orig.add(get_note((contents[i] + gd.get_key())%12) + get_type(contents[i+1]));
		}
		return orig;
	}

}