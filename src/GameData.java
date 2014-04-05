public class GameData {
	int[][] notes;
	String chord_name;
	int measure_num;
	int key, type;
	
	public GameData(int key, int type) {
		notes = new int[10][4];
		this.key = key;
		this.chord_name = getKeyName(key, type);
	}
	
	public String getKeyName(int key, int type) {
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
		switch (type) {
		case 0: name+=" maj";	break;
		case 1: name+=" min";	break;
		case 2: name+=" maj7";	break;
		case 3: name+=" min7";	break;
		case 4: name+=" dim";	break;
		case 5: name+=" dim7";	break;
		default: name+=" maj";	break;
		}
		return name;
	}
	
	
}