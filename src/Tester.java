
public class Tester {

	public static void main(String[] args) {
		GameData gd = new GameData(0, 0);
		gd.initialize_data(120, 1);
		Game testGame = new Game(gd);
		testGame.setVisible(true);

	}

}
