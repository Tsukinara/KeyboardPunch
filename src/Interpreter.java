import java.util.ArrayList;

public class Interpreter {

	private ArrayList<Integer> notes;
	private Chord chord;
	private GameData gd;
	private int currentChord, currentType;
	private String lolstring = "You messed up lol.";

	public Interpreter() {
		notes = new ArrayList<Integer>();
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
	
	public Chord getChord() {return this.chord;}
	public int getCurrentType() {return this.currentType;}

	public String getChordName() {
		String chordString;
		chord = new Chord(get_most_relevant(), gd.get_key());
		chordString = chord.getName();
		return chordString;
	}
	
	public ArrayList<String> get_next_chords() {
		ArrayList<String> next_chords = new ArrayList<String>();
		setConstants(getChordName());
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
			if (currentType == 1) addValues(options, new int[]{10, 0, 3, 0, 8, 0, 8, 4, 5, 1, 5, 5, 2, 2, 2, 3, 11, 2, 11, 3, 7, 0, 7, 4});
			else if (currentType == 0) addValues(options, new int[]{5, 1, 5, 5});
			else if (currentType == 7) addValues(options, new int[]{0, 1});
			else options.add(lolstring);
			break;
		case 1:
			if (currentType == 1) addValues(options, new int[]{0, 1});
			else options.add(lolstring);
			break;
		case 2:
			if (currentType == 2 || currentType == 3) addValues(options, new int[]{11, 2, 11, 3, 7, 0, 7, 4, 8, 0, 0, 1});
			else options.add(lolstring);
			break;
		case 3:
			if (currentType == 0) addValues(options, new int[]{8, 0, 5, 1, 5, 5, 2, 2, 2, 3});
			else if (currentType == 6) addValues(options, new int[]{8, 0});
			else options.add(lolstring);
			break;
		case 4:
			if (currentType == 2 || currentType == 3) addValues(options, new int[]{5, 1, 5, 5});
			else options.add(lolstring);
			break;
		case 5:
			if (currentType == 1 || currentType == 5) addValues(options, new int[]{2, 2, 2, 3, 0, 1, 11, 2, 11, 3, 7, 0, 7, 4});
			else if (currentType == 0 || currentType == 4) addValues(options, new int[]{0, 1, 8, 0, 8, 4});
			else options.add(lolstring);
			break;
		case 6:
			options.add(lolstring);
			break;
		case 7:
			if (currentType == 0 || currentType == 4) addValues(options, new int[]{0, 1, 0, 7, 8, 0});
			else options.add(lolstring);
			break;
		case 8:
			if (currentType == 0 || currentType == 4) addValues(options, new int[]{5, 1, 5, 5, 2, 2, 2, 3});
			else if (currentType == 2 || currentType == 3) addValues(options, new int[]{11, 2, 11, 3, 7, 0, 7, 4, 8, 0, 0, 1});
			else options.add(lolstring);
			break;
		case 9:
			options.add(lolstring);
			break;
		case 10:
			if (currentType == 0 || currentType == 4) addValues(options, new int[]{3, 0, 5, 0});
			else options.add(lolstring);
			break;
		case 11:
			if (currentType == 2 || currentType == 3) addValues(options, new int[]{8, 0, 0, 1, 0, 4});
			else options.add(lolstring);
			break;
		default:
			options.add(lolstring);
		}
		return options;
	}

	private int[] get_most_relevant() {
		ArrayList<Integer> holder = new ArrayList<Integer>();
		int min = (notes.size() < 4 ? notes.size() : 4);
		int counter = 0;
		sort_notes();
		while (holder.size() < min) {
			if(notes.size() == counter) break;
			int note = notes.get(counter) % 12;
			if (!holder.contains(note)) holder.add(note);
			counter++;
		}
		int[]chord = new int[holder.size()];
		for (int i = 0; i < holder.size(); i++) chord[i] = holder.get(i);
		return chord;
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
		case 1: name="m";		break;
		case 2: name="dim";		break;
		case 3: name="dim7";	break;
		case 4: name="M7";		break;
		case 5: name="m7";		break;
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
		if (type.equals("m")) currentType = 1;
		if (type.equals("dim")) currentType = 2;
		if (type.equals("dim7")) currentType = 3;
		if (type.equals("M7")) currentType = 4;
		if (type.equals("m7")) currentType = 5;
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