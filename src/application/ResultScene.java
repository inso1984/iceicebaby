package application;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResultScene {

	private Main main;
	private static final String ERGEBNISTEXT = "Ergebnisse von Tag %d";
	private static final String WEITER = "weiter";
	private static final String SPIELVERLASSENTEXT = "Spiel verlassen";
	private static final String BESUCHERTEXT = "Du hattest %d Besucher!";
	private static final String EISTEXT = "Bei den Besuchern konntest du %d Eis absetzen und hattest noch %d Kugeln übrig.";
	private static final String GEWINNTEXT = "Du hast an diesem Tag %s CHF Gewinn erwirtschaftet";
	private static DecimalFormat DECIMALFORMAT = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.GERMAN));
	private SaleDay saleDay;

	public ResultScene(Main main) {
		this.main = main;
		this.saleDay = GameData.getGame().getSaleDays().get(GameData.getRound() - 1);
	}

	public Pane buildScene() {
		// Hintergrundbild im Fenster
		StackPane root = new StackPane();
		root.setId("pane");
		// Container
		VBox main = new VBox();
		main.setId("main");

		// Container-Inhalt
		Label label1 = new Label(String.format(ERGEBNISTEXT, GameData.getRound()));
		Text besucherText = new Text(String.format(BESUCHERTEXT, this.saleDay.getVisitors()));
		Text eisText = new Text(
				String.format(EISTEXT, this.saleDay.getSoldIce(), (this.saleDay.getIceQuantity() - this.saleDay.getSoldIce())));
		Text earningText = new Text(String.format(GEWINNTEXT, DECIMALFORMAT.format(GameData.getGame().getGewinn())));

		// Inhalt in den Container einfügen
		main.getChildren().addAll(label1, besucherText, eisText, earningText, createButtons());
		root.getChildren().addAll(main);
		return root;
	}

	private HBox createButtons() {
		Button forward = new Button(WEITER);
		addForwardEvent(forward);

		Button cancel = new Button(SPIELVERLASSENTEXT);
		addCancelEvent(cancel);
		return new HBox(forward, cancel);
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
