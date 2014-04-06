import java.util.ArrayList;

public class LabeledSuggestion {

	private ArrayList<Integer> keyList=new ArrayList<Integer>();

	public LabeledSuggestion(String chord) {
		Chord c = new Chord(chord, 0);
		int[] notes = c.getNotes();
		if (notes != null) for(int i = 0; i < notes.length; i++) keyList.add(notes[i]);
	}

	public ArrayList<Integer> getKeyList() {
		return keyList;
	}
}