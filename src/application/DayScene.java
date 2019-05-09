package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DayScene {

	private Main main;
	private SaleDay saleDay;
	private TextField waffeln;

	public DayScene(Main main) {
		this.main = main;
		this.saleDay = new SaleDay();
		GameData.getGame().getSaleDays().add(this.saleDay);
		GameData.nextRound();
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
		Label label1 = new Label("Tag " + GameData.getRound());
		Text wetterText = new Text(this.saleDay.getWeather().getText());
		wetterText.getStyleClass().add("text");
		Text temperaturText = new Text(this.saleDay.getWeather().getTemperature());
		temperaturText.getStyleClass().add("text");
		this.waffeln = new TextField();
		Button forward = new Button("weiter");
		addForwardEvent(forward);

		Button cancel = new Button("Spiel verlassen");
		addCancelEvent(cancel);

		// Inhalt in den Container einfügen
		main.getChildren().addAll(label1, wetterText, temperaturText, waffeln, forward, cancel);
		root.getChildren().addAll(main);
		return root;
	}

	private void addForwardEvent(Button forward) {
		forward.setOnAction((event) -> {
			System.out.println(waffeln.getText());
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
