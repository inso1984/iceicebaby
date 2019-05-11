package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RulesScene implements ApplicationScene{

	private Main main;

	public RulesScene(Main main) {
		this.main = main;
	}

	public Pane buildScene() {
		// Hintergrundbild im Fenster
		StackPane root = new StackPane();
		root.setId("pane");

		// Container
		VBox main = new VBox();
		main.setId("main");

		// Container-Inhalt
		Label label1 = new Label("Spielregeln");
		Text regelText = new Text("Du hast 10 Tage (Runden) Zeit, dein Verkaufstalent als Eis-Verkäufer unter \n"
				+ "Beweis zu stellen. Entscheide Anhand der Wettervorhersage wie viele Waffeln \n"
				+ "und wie viel Liter Eis für den aktuellen Tag benötigt werden. Liegst du mit deiner \n"
				+ "Planung richtig wirst du Gewinn erwirtschaften.\n" + "Liegst du falsch wirst du Verlust machen.\n");
		regelText.setId("text");
		Button forward = new Button("weiter");
		addForwardEvent(forward);

		Button cancel = new Button("Spiel verlassen");
		addCancelEvent(cancel);

		// Inhalt in den Container einfügen
		main.getChildren().add(label1);
		main.getChildren().add(regelText);
		main.getChildren().add(forward);
		main.getChildren().add(cancel);
		root.getChildren().addAll(main);
		return root;
	}

	private void addForwardEvent(Button forward) {
		forward.setOnAction((event) -> {
			// statische Klasse auf die ich immer zugreifen kann
			GameData.startGame();
			DayScene scene = new DayScene(this.main);
			main.getScene().setRoot(scene.buildScene());
		});
	}

	private void addCancelEvent(Button cancel) {
		cancel.setOnAction((event) -> {
			StartScene start = new StartScene(this.main);
			main.getScene().setRoot(start.buildScene());
		});
	}

}
