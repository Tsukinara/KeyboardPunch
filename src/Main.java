public class Main {
	public static void main(String[] args) {
		GameData gd = new GameData(0, 0);
		gd.initialize_data(120, 1);
		Game g = new Game(gd);
		g.setVisible(true);
	}
}