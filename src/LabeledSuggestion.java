
public class LabeledSuggestion {
	public LabeledSuggestion(String chord){
		int[]chordAndType = getConstants(chord);
		int startingNote = chordAndType[0]; //between 0 and 11
		int chordalType = chordAndType[1];
	}

	private int[] getConstants(String chordName) {
		String type;
		int[]chordType = new int[2];
		if(chordName.charAt(1) == '#' || chordName.charAt(1) == 'b') {
			type = chordName.substring(2, chordName.length());
			switch(chordName.charAt(0)) {
			case 'C': chordType[0] = 1; break;
			case 'D': chordType[0]= 3; break;
			case 'F': chordType[0] = 6; break;
			case 'A': chordType[0] = 8; break;
			default: chordType[0] = 11; break;
			}
		} else {
			type = chordName.substring(1, chordName.length());
			switch(chordName.charAt(0)) {
			case 'C': chordType[0] = 0; break;
			case 'D': chordType[0] = 2; break;
			case 'E': chordType[0] = 4; break;
			case 'F': chordType[0] = 5; break;
			case 'G': chordType[0] = 7; break;
			case 'A': chordType[0] = 9; break;
			case 'B': chordType[0] = 11; break;
			}
		}
		if (type.equals("maj")) chordType[1] = 0;
		if (type.equals("min")) chordType[1] = 1;
		if (type.equals("dim")) chordType[1] = 2;
		if (type.equals("dim7")) chordType[1] = 3;
		if (type.equals("maj7")) chordType[1] = 4;
		if (type.equals("min7")) chordType[1] = 5;
		if (type.equals("7")) chordType[1] = 6;
		if (type.equals("sus4")) chordType[1] = 7;

		return chordType;
	}
}
