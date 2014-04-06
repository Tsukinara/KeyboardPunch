import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
	
	private ArrayList<Measure> majorbass;
	private ArrayList<Measure> minorbass;
	private ArrayList<Measure> majortreble;
	private ArrayList<Measure> minortreble;
	
	public Loader() {
		majorbass = new ArrayList<Measure>();
		minorbass = new ArrayList<Measure>();
		majortreble = new ArrayList<Measure>();
		minortreble = new ArrayList<Measure>();
		majorbass = load("major_bass_data.txt");
		minorbass = load("minor_bass_data.txt");
		majortreble = load("major_treble_data.txt");
		minortreble = load("minor_treble_data.txt");
	}
	
	public ArrayList<Measure> load(String filename) {
		ArrayList<Measure> l = new ArrayList<Measure>();
		try {
			Scanner scan = new Scanner(new FileReader(filename));
			ArrayList<Double> d = new ArrayList<Double>();
			ArrayList<Integer> i = new ArrayList<Integer>();
			String s;
			while(scan.hasNext()) {
				s = scan.nextLine();
				if(s.equals("--")) {
					l.add(new Measure(d, i));
					d = new ArrayList<Double>();
					i = new ArrayList<Integer>();
				}
				else {
					String[] temp = s.split(",");
					d.add(Double.valueOf(temp[0]));
					i.add(Integer.parseInt(temp[1]));
				}
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("404 Error: File Not Found");
		}
		return l;
		 
	}
	
	public ArrayList<Measure> getMajorBass() {
		return majorbass;
	}
	
	public ArrayList<Measure> getMinorBass() {
		return minorbass;
	}
	
	public ArrayList<Measure> getMajorTreble() {
		return majortreble;
	}
	
	public ArrayList<Measure> getMinorTreble() {
		return minortreble;
	}
}