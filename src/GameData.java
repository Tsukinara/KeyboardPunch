public class GameData {
	int[][] notes;
	int measure_num;

	int key;	
	int bpm, difficulty;
	boolean majmin = true; //true for major, false for minor

	public GameData(int key, int type) {
		notes = new int[10][4];
		this.key = key;
	}

	public void initialize_data(int bpm, int difficulty) {
		this.bpm = bpm;
		this.difficulty = difficulty;
	}

	public boolean getMajMin() {return this.majmin;}
	public int get_difficulty() {return this.difficulty;}
	public int get_bpm() {return this.bpm;}
	public int get_key() {return this.key;}

	public void set_majmin(boolean majmin) {this.majmin = majmin;};
	public void set_bpm(int bpm) {if (bpm > 59 && bpm < 241) this.bpm = bpm;}
	public void set_key(int key) {if (key >= 0 && key < 12) this.key = key;}
	public void set_difficulty(int diff) {this.difficulty = diff;};
}