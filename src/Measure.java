import java.util.ArrayList;

public class Measure {
	
	private ArrayList<Double> beat;
	private ArrayList<Integer> shift;
	
	public Measure(ArrayList<Double> b, ArrayList<Integer> s) {
		beat = b;
		shift = s;
	}
	
	public ArrayList<Double> getBeat() {
		return beat;
	}
	
	public ArrayList<Integer> getShift() {
		return shift;
	}
	
	public ArrayList<Integer> getBeatsThatMatch(double beatID) {
		ArrayList<Integer> matches = new ArrayList<Integer>();
		for (int i = 0; i < beat.size(); i++)
			if (beat.get(i) == beatID) matches.add(shift.get(i));
		return matches;
	}
}