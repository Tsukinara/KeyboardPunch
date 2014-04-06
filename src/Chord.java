public class Chord {
	private int[] notes;
	private String name;
	private int key, chordType;

	public Chord(int [] notes, int key) {
		this.key = key;
		this.notes = notes;
		this.name = getChord(notes);
	}

	public Chord(String name, int key) {
		this.key = key;
		this.name = name;
		this.notes = getNotes(name);
	}

	public String getName() {return this.name;}
	public int[] getNotes() {return this.notes;}
	public int getKey() {return this.key;}

	private int[] getNotes(String name) {
		int[] notes;
		String type;
		if(name.charAt(1) == '#' || name.charAt(1) == 'b') {
			type = name.substring(2, name.length());
		} else {
			type = name.substring(1, name.length());
		}
		if (type.equals("maj")) { chordType = 0; notes = new int[3];}
		else if (type.equals("m")) { chordType = 1; notes = new int[3];}
		else if (type.equals("dim")) { chordType = 2; notes = new int[3];}
		else if (type.equals("dim7")) { chordType = 3; notes = new int[4];}
		else if (type.equals("M7")) { chordType = 4; notes = new int[4];}
		else if (type.equals("m7")) { chordType = 5; notes = new int[4];}
		else if (type.equals("7")) { chordType = 6; notes = new int[4];}
		else if (type.equals("sus4")) { chordType = 7; notes = new int[3];}
		else notes = null;

		if (notes != null) {
			if(name.charAt(1) == '#' || name.charAt(1) == 'b') {
				switch(name.charAt(0)) {
				case 'C': notes[0] = 1; break;
				case 'D': notes[0] = 3; break;
				case 'F': notes[0] = 6; break;
				case 'A': notes[0] = 8; break;
				default: notes[0] = 11; break;
				}
			} else {
				type = name.substring(1, name.length());
				switch(name.charAt(0)) {
				case 'C': notes[0] = 0; break;
				case 'D': notes[0] = 2; break;
				case 'E': notes[0] = 4; break;
				case 'F': notes[0] = 5; break;
				case 'G': notes[0] = 7; break;
				case 'A': notes[0] = 9; break;
				case 'B': notes[0] = 11; break;
				}
			}
			switch(chordType) {
			case 0: notes[1] = notes[0] + 4; notes[2] = notes[1] + 3;	break;
			case 1: notes[1] = notes[0] + 3; notes[2] = notes[1] + 4;	break;
			case 2: notes[1] = notes[0] + 3; notes[2] = notes[1] + 3;	break;
			case 3: notes[1] = notes[0] + 3; notes[2] = notes[1] + 3; notes[3] = notes[2] + 3;	break;
			case 4: notes[1] = notes[0] + 4; notes[2] = notes[1] + 3; notes[3] = notes[2] + 4;	break;
			case 5: notes[1] = notes[0] + 3; notes[2] = notes[1] + 4; notes[3] = notes[2] + 3;	break;
			case 6: notes[1] = notes[0] + 4; notes[2] = notes[1] + 3; notes[3] = notes[2] + 3;	break;
			case 7: notes[1] = notes[0] + 5; notes[2] = notes[1] + 2;	break;
			}
		}

		return notes;
	}


	private String getChord(int[] chord) {
		String chordString = "";
		if (chord.length == 0) return "---";
		if (chord.length == 1) {
			chordString += Interpreter.get_note(chord[0]);
			int relmin = chord[0];
			if (relmin < key) relmin+=12;
			if(relmin == key+9 || relmin == key+2) chordString += "m";
			else chordString += "maj";
			return chordString;
		}
		if (chord.length == 2) {
			int base = chord[0];
			int next = chord[1];
			if (next < base) next +=12;
			return two_note_chord(base, next);			
		}
		if (chord.length == 3) {
			int base = chord[0];
			int next = chord[1];
			if (next < base) next += 12;
			int last = chord[2];
			while (last < next) last += 12;
			return three_note_chord(base, next, last);
		}
		int base = chord[0];
		int next = chord[1];
		int third = chord[2];
		int last = chord[3];
		while (next < base) next +=12;
		while (third < next) third +=12;
		while (last < third) last +=12;
		return four_note_chord(base, next, third, last);
	}

	private String two_note_chord(int base, int next) {
		String chordString = Interpreter.get_note(base);
		switch (next - base) {
		case 1: 
			chordString = Interpreter.get_note(next%12);
			chordString += "M7";	break;
		case 2:
			chordString = Interpreter.get_note(next%12);
			chordString += "7"; 	break;
		case 3: 
			chordString += "m"; 	break;
		case 4:
			int relmin = base; if (relmin < key) relmin+=12;
			if (relmin == key-3 || relmin == key+2) chordString += "m";
			else chordString += "maj"; break;
		case 7:
			chordString += "maj"; 	break;
		case 5: case 8:
			chordString = Interpreter.get_note(next%12);
			chordString += "maj"; 	break;
		case 6:
			chordString += "dim7";	break;
		case 9:
			chordString = Interpreter.get_note(next%12);
			chordString += "m";	break;
		case 10:
			chordString += "7";		break;
		case 11:
			chordString += "M7";	break;				
		default: return "???";
		}
		return chordString;
	}

	private String three_note_chord(int base, int next, int last) {
		int baseclone = base;
		while (baseclone < last) baseclone+=12;
		//check for dims
		if (next - base == 3 && last - next == 3) return Interpreter.get_note(base) + "dim";
		if (next - base == 3 && last - next == 6) return Interpreter.get_note(last%12) + "dim";
		if (next - base == 6 && last - next == 3) return Interpreter.get_note(next%12) + "dim";

		//look for majors
		if (next - base == 4 && last - next == 3) return Interpreter.get_note(base) + "maj";
		if (next - base == 3 && last - next == 5) return Interpreter.get_note(last%12) + "maj";
		if (next - base == 5 && last - next == 4) return Interpreter.get_note(next%12) + "maj";
		if (next - base == 7 && last - next == 9) return Interpreter.get_note(base) + "maj";

		//look for minors
		if (next - base == 3 && last - next == 4) return Interpreter.get_note(base) + "m";
		if (next - base == 4 && last - next == 5) return Interpreter.get_note(last%12) + "m";
		if (next - base == 5 && last - next == 3) return Interpreter.get_note(next%12) + "m";
		if (next - base == 7 && last - next == 8) return Interpreter.get_note(base) + "m";

		//check for 7ths
		if (next - base == 1 && (last - next == 4 || last - next == 7)) return Interpreter.get_note(next%12) + "M7";
		if ((next - base == 4 || next - base ==  7) && last - next == 1) return Interpreter.get_note(last%12) + "M7";
		if (last - base == 11 && (next - base == 4 || next - base == 7)) return Interpreter.get_note(base%12) + "M7";

		if (next - base == 2 && (last - next == 4 || last - next == 7)) return Interpreter.get_note(next%12) + "7";
		if ((next - base == 4 || next - base ==  7) && last - next == 2) return Interpreter.get_note(last%12) + "7";
		if (last - base == 10 && (next - base == 4 || next - base == 7)) return Interpreter.get_note(base%12) + "7";

		if (next - base == 2 && (last - next == 3 || last - next == 7)) return Interpreter.get_note(next%12) + "m7";
		if ((next - base == 3 || next - base ==  7) && last - next == 2) return Interpreter.get_note(last%12) + "m7";
		if (last - base == 10 && (next - base == 3 || next - base == 7)) return Interpreter.get_note(base%12) + "m7";		

		//check for sus4
		if (next - base == 2 && last - next == 5) return Interpreter.get_note(last%12) + "sus4";
		if (next - base == 5 && last - next == 5) return Interpreter.get_note(next%12) + "sus4";
		if (next - base == 5 && last - next == 2) return Interpreter.get_note(base) + "sus4";

		return "???";
	}

	private String four_note_chord(int base, int next, int third, int last) {
		String chordString = Interpreter.get_note(base);
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
		if (next-base == 1 && third == next+4 && last == third+3) return Interpreter.get_note(next%12) + "M7";
		if (third-next == 1 && next == base+4 && last == third+4) return Interpreter.get_note(third%12) + "M7";
		if (last-third == 1 && base == next-3 && next == third-4) return Interpreter.get_note(last%12) + "M7";
		if (baseclone-last == 1 && next == base+4 && third == next+3) return chordString + "M7";

		//check for minor 7ths
		if (next-base == 2 && third == next+3 && last == third+4) return Interpreter.get_note(next%12) + "m7";
		if (third-next == 2 && next == base+3 && last == third+3) return Interpreter.get_note(third%12) + "m7";
		if (last-third == 2 && base == next-4 && next == third-3) return Interpreter.get_note(last%12) + "m7";
		if (baseclone-last == 2 && next == base+3 && third == next+4) return chordString + "m7";

		//check for major-minor 7ths
		if (next-base == 2 && third == next+4 && last == third+3) return Interpreter.get_note(next%12) + "7";
		if (third-next == 2 && next == base+3 && last == third+4) return Interpreter.get_note(third%12) + "7";
		if (last-third == 2 && base == next-3 && next == third-3) return Interpreter.get_note(last%12) + "7";
		if (baseclone-last == 2 && next == base+4 && third == next+3) return chordString + "7";

		return "???";
	}
}