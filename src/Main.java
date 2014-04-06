public class Main {
	public static void main(String[] args) {
		GameData gd = new GameData(0, 0);
		Interpreter i = new Interpreter();
		gd.initialize_data(120, 4);
		Game g = new Game(gd, i);
		MidiHandler mh = new MidiHandler(g, i);
		mh.imNotUseless();
		g.setVisible(true);
	}
}