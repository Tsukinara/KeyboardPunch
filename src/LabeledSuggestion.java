import java.util.ArrayList;

public class LabeledSuggestion {

	private ArrayList<Integer> keyList=new ArrayList<Integer>();

	public LabeledSuggestion(String chord) {
		int[] chordAndType = getConstants(chord);
		int startingNote = chordAndType[0]; // between 0 and 11
		int chordalType = chordAndType[1]; // has rules
		setKeyList(startingNote, chordalType);
		
	}

	public ArrayList<Integer> getKeyList() {
		return keyList;
	}

	private void setKeyList(int startingNote, int chordalType) {
		keyList.add(startingNote);
		if (chordalType == 0) {// maj
			keyList.add(startingNote + 4);
			keyList.add(startingNote + 7);
		} else if (chordalType == 1) { // min
			keyList.add(startingNote + 3);
			keyList.add(startingNote + 7);
		}
		else if (chordalType == 2) {// dim
			keyList.add(startingNote + 3);
			keyList.add(startingNote + 6);
		} 
		else if (chordalType == 3) {// dim7
			keyList.add(startingNote + 3);
			keyList.add(startingNote + 6);
			keyList.add(startingNote + 9);
			
		} else if (chordalType == 4) { // maj7
			keyList.add(startingNote + 3);
			keyList.add(startingNote + 7);
			keyList.add(startingNote + 11);
		}
		else if (chordalType == 5) {// min7
			keyList.add(startingNote+3);
			keyList.add(startingNote + 7);
			keyList.add(startingNote + 10);
		} 
		else if (chordalType == 6) { // 7
			keyList.add(startingNote + 4);
			keyList.add(startingNote + 7);
			keyList.add(startingNote + 10);
		}
		else if (chordalType == 7) { // sus4 - lol just dont get this
		} 
	}

	private int[] getConstants(String chordName) {
		String type;
		int[] chordType = new int[2];
		if (chordName.charAt(1) == '#' || chordName.charAt(1) == 'b') {
			type = chordName.substring(2, chordName.length());
			switch (chordName.charAt(0)) {
			case 'C':
				chordType[0] = 1;
				break;
			case 'D':
				chordType[0] = 3;
				break;
			case 'F':
				chordType[0] = 6;
				break;
			case 'A':
				chordType[0] = 8;
				break;
			default:
				chordType[0] = 11;
				break;
			}
		} else {
			type = chordName.substring(1, chordName.length());
			switch (chordName.charAt(0)) {
			case 'C':
				chordType[0] = 0;
				break;
			case 'D':
				chordType[0] = 2;
				break;
			case 'E':
				chordType[0] = 4;
				break;
			case 'F':
				chordType[0] = 5;
				break;
			case 'G':
				chordType[0] = 7;
				break;
			case 'A':
				chordType[0] = 9;
				break;
			case 'B':
				chordType[0] = 11;
				break;
			}
		}
		if (type.equals("maj"))
			chordType[1] = 0;
		if (type.equals("m"))
			chordType[1] = 1;
		if (type.equals("dim"))
			chordType[1] = 2;
		if (type.equals("dim7"))
			chordType[1] = 3;
		if (type.equals("M7"))
			chordType[1] = 4;
		if (type.equals("m7"))
			chordType[1] = 5;
		if (type.equals("7"))
			chordType[1] = 6;
		if (type.equals("sus4"))
			chordType[1] = 7;

		return chordType;
	}
}
