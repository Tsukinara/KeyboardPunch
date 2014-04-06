public class Main {
	public static void main(String[] args) {
		GameData gd = new GameData(0, 0);
		Interpreter i = new Interpreter();
		gd.initialize_data(120, 1);
		MidiPlayer mp = new MidiPlayer();
		mp.play_song("song_test.txt");
		Game g = new Game(gd, i);
		MidiHandler mh = new MidiHandler(g, i);
		mh.imNotUseless();
		g.setVisible(true);
	}
}