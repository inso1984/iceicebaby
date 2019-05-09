package application;

//Globale Klasse, dessen static Values sofort im Speicher erstellt(reserviert) werden

public class GameData {
	
	private static Game game;
	private static int round;
	
	public static void startGame() {
		game = new Game();
		round = 0;
	}

	public static Game getGame() {
		return game;
	}
	
	public static void nextRound() {
		round++;
	}

	public static int getRound() {
		return round;
	}

}
