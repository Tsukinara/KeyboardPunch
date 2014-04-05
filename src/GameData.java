public class GameData {
	int[][] notes;
	String chord_name;
	int measure_num;

	int key, type;	
	int bpm, difficulty;

	public GameData(int key, int type) {
		notes = new int[10][4];
		this.key = key;
		this.chord_name = get_key_name(key, type);
	}

	public void initialize_data(int bpm, int difficulty) {
		this.bpm = bpm;
		this.difficulty = difficulty;
	}

	public int get_difficulty() {return this.difficulty;}
	public int get_bpm() {return this.bpm;}
	public int get_key() {return this.key;}
	public int get_type() {return this.type;}
	public String get_chord_name() {return this.chord_name;}

	public void set_bpm(int bpm) {if (bpm > 59 && bpm < 181) this.bpm = bpm;}
	public void set_key(int key) {if (key >= 0 && key < 12) this.key = key;}
	public void set_type(int type) {if (type >= 0 && type < 6) this.type = type;}
	public void set_difficulty(int diff) {this.difficulty = diff;};

	public static String get_key_name(int key, int type) {
		String name = "";
		name += get_note(key);
		name += get_type(type);
		return name;
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
		case 0: name="maj";	break;
		case 1: name="min";	break;
		case 2: name="maj7";	break;
		case 3: name="min7";	break;
		case 4: name="dim";	break;
		case 5: name="dim7";	break;
		case 6: name="7";		break;
		default: name="maj";	break;
		}
		return name;
	}
}