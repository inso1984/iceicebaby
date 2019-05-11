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
	private static final String GESAMTGEWINNTEXT = "Du hast agesamt %s CHF Gewinn erwirtschaftet";
	private static final String ENDETEXT = "Spiel Abschliessen";
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
		VBox main = new VBox(5);
		main.setId("main");

		// Container-Inhalt
		Label label1 = new Label(String.format(ERGEBNISTEXT, GameData.getRound()));
		Text besucherText = new Text(String.format(BESUCHERTEXT, this.saleDay.getVisitors()));
		Text eisText = new Text(String.format(EISTEXT, this.saleDay.getSoldIce(),
				(this.saleDay.getIceQuantity() - this.saleDay.getSoldIce())));
		Text earningText = new Text(String.format(GEWINNTEXT, DECIMALFORMAT.format(this.saleDay.getEarnings())));

		Text gesamtEinnahmen = new Text(
				String.format(GESAMTGEWINNTEXT, DECIMALFORMAT.format(GameData.getGame().getGewinn())));

		// Inhalt in den Container einfügen
		main.getChildren().addAll(label1, besucherText, eisText, earningText, gesamtEinnahmen, createButtons());
		root.getChildren().addAll(main);
		return root;
	}

	private HBox createButtons() {
		
		Button forward = new Button(GameData.getRound() == 10 ? ENDETEXT : WEITER);
		addForwardEvent(forward);

		Button cancel = new Button(SPIELVERLASSENTEXT);
		addCancelEvent(cancel);
		return new HBox(5, forward, cancel);
	}

	private void addForwardEvent(Button forward) {
		forward.setOnAction((event) -> {
			ApplicationScene scene;
			if(GameData.getRound()<10) {
				scene = new DayScene(this.main);
			}else {
				scene = new StartScene(this.main);
			}
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
