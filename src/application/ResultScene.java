package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResultScene {

	private Main main;

	public ResultScene(Main main2) {
		// TODO Auto-generated constructor stub
	}

	public Pane buildScene() {
		// Hintergrundbild im Fenster
		StackPane root = new StackPane();
		root.setId("pane");
		SaleDay saleDay = new SaleDay();

		// Container
		VBox main = new VBox();
		main.setId("main");

		// Container-Inhalt
		Label label1 = new Label("Ergebnis Tag " + GameData.getRound());


		Button forward = new Button("weiter");
		addForwardEvent(forward);

		Button cancel = new Button("Spiel verlassen");
		addCancelEvent(cancel);

		// Inhalt in den Container einfügen
		main.getChildren().addAll(label1, forward, cancel);
		root.getChildren().addAll(main);
		return root;
	}

	private void addForwardEvent(Button forward) {
		forward.setOnAction((event) -> {
			DayScene scene = new DayScene(this.main);
			main.getScene().setRoot(scene.buildScene());
		});
	}

	private void addCancelEvent(Button cancel) {
		cancel.setOnAction((event) -> {
			StartScene start = new StartScene(this.main);
			main.getScene().setRoot(start.buildStartScene());
		});
	}

}
