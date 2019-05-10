package application;

import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DayScene {

	private static final String WAFFELTEXT = "Wie viele Waffeln möchtest du einkaufen?";
	private static final String EISTEXT = "Wie viele Liter Eis möchtest du einkaufen?";
	private static final String PREISTEXT = "Wie hoch ist der Preis pro Kugel";
	private static final String TAGBEENDENTEXT = "Tag beenden";
	private static final String SPIELVERLASSENTEXT = "Spiel verlassen";
	private static final String TEXTCLASS = "text";
	private static final String DAYTEXT = "Eingabe für Tag %d";
	private static final double LITTERCOST = 3.5;
	private static final double WAFFELPREIS = 0.20;

	private Main main;
	private SaleDay saleDay;
	private TextField waffeln;
	private TextField eismenge;
	private TextField preis;

	public DayScene(Main main) {
		this.main = main;
		this.saleDay = new SaleDay();
		GameData.getGame().getSaleDays().add(this.saleDay);
		GameData.nextRound();
	}

	/**
	 * Funktion erstellt die Scene für einen Spieltag
	 * 
	 * @return Pane DayScene
	 */
	public Pane buildScene() {
		// Hintergrundbild im Fenster
		StackPane root = new StackPane();
		root.setId("pane");
		// Container
		VBox main = new VBox();
		main.setId("main");

		// Container-Inhalt
		Label label1 = new Label(String.format(DAYTEXT, GameData.getRound()));
		Text wetterText = new Text(this.saleDay.getWeather().getText());
		wetterText.getStyleClass().add(TEXTCLASS);
		Text temperaturText = new Text(this.saleDay.getWeather().getTemperature());
		temperaturText.getStyleClass().add(TEXTCLASS);
		Text eingabeWaffelmenge = new Text(WAFFELTEXT);
		this.waffeln = new TextField();
		Text eingabeEismenge = new Text(EISTEXT);
		this.eismenge = new TextField();
		Text eingabePreis = new Text(PREISTEXT);
		this.preis = new TextField();

		// Inhalt in den Container einfügen
		main.getChildren().addAll(label1, wetterText, temperaturText, eingabeWaffelmenge, waffeln, eingabeEismenge,
				eismenge, eingabePreis, preis, createButtons());
		root.getChildren().addAll(main);
		return root;
	}

	/**
	 * Horizontal angeordnete Buttons
	 * 
	 * @return HBOX mit 2 Buttons
	 */
	private HBox createButtons() {
		Button action = new Button(TAGBEENDENTEXT);
		addForwardEvent(action);
		Button cancel = new Button(SPIELVERLASSENTEXT);
		addCancelEvent(cancel);
		return new HBox(action, cancel);
	}

	/**
	 * Event leitet weiter zur Resultseite vorher werden die Berechnungen
	 * durchgeführt.
	 * 
	 * @param forward
	 */
	private void addForwardEvent(Button forward) {
		forward.setOnAction((event) -> {
			calculateResults();
			ResultScene scene = new ResultScene(this.main);
			main.getScene().setRoot(scene.buildScene());
		});
	}

	private void calculateResults() {
		int waffelWert = Integer.parseInt(this.waffeln.getText());
		System.out.println(waffelWert);
		int litterWert = Integer.parseInt(this.eismenge.getText());
		System.out.println(litterWert);
		double preis = Double.parseDouble(this.preis.getText());
		System.out.println(preis);
		int besucher = (int) (Math.round((Math.random() * 500) + 500) * this.saleDay.getWeather().getFact());
		System.out.println(besucher);
		int kugeln = litterWert * 12;
		System.out.println(kugeln);
		this.saleDay.setVisitors(besucher);
		this.saleDay.setCornetQuantity(waffelWert);
		this.saleDay.setIceQuantity(kugeln);
		this.saleDay.setSalePrice(preis);
		this.saleDay.setCosts(calculateCosts(litterWert, waffelWert));
		calculateEarning();
	}

	private void calculateEarning() {
		int kugeln = this.saleDay.getIceQuantity();
		int waffeln = this.saleDay.getCornetQuantity();
		Random random = new Random();
		int anzahl;
		double earning = 0;
		int sold = 0;
		while (this.saleDay.getVisitors() > 0 && waffeln > 0 && kugeln > 0) {
			anzahl = random.nextInt(1) + 1;
			waffeln--;
			if (kugeln == 1) {
				anzahl = 1;
			}
			kugeln -= anzahl;
			sold += anzahl;
			earning += (anzahl * this.saleDay.getSalePrice());
		}
		this.saleDay.setEarnings((Math.round((earning - this.saleDay.getCosts()) * 100) / 100));
		GameData.getGame().setGewinn(
				GameData.getGame().getGewinn() + this.saleDay.getEarnings());
		this.saleDay.setSoldIce(sold);
	}

	private double calculateCosts(int litterWert, int waffelWert) {
		return (litterWert * LITTERCOST) + (waffelWert + WAFFELPREIS);
	}

	private void addCancelEvent(Button cancel) {
		cancel.setOnAction((event) -> {
			StartScene start = new StartScene(this.main);
			main.getScene().setRoot(start.buildStartScene());
		});
	}

}
