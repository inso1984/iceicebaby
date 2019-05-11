package application;

import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author admin
 *
 */
public class DayScene implements ApplicationScene{

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
	private TextField waffeln = new TextField();
	private TextField eismenge = new TextField();
	final Slider preis = new Slider(1, 5, 0.1);

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
		VBox main = new VBox(5);
		main.setId("main");

		// Container-Inhalt
		Label label1 = new Label(String.format(DAYTEXT, GameData.getRound()));
		Text wetterText = new Text(this.saleDay.getWeather().getText());
		wetterText.getStyleClass().add(TEXTCLASS);
		Text temperaturText = new Text(this.saleDay.getWeather().getTemperature());
		temperaturText.getStyleClass().add(TEXTCLASS);
		Text eingabeWaffelmenge = new Text(WAFFELTEXT);
		this.waffeln.textProperty().addListener(getNumberCheckChangeListener(waffeln));
		Text eingabeEismenge = new Text(EISTEXT);
		this.eismenge.textProperty().addListener(getNumberCheckChangeListener(eismenge));
		VBox preisAngabe = getPreisBox();
		// Inhalt in den Container einfügen
		main.getChildren().addAll(label1, wetterText, temperaturText, eingabeWaffelmenge, waffeln, eingabeEismenge,
				eismenge, preisAngabe, preis, createButtons());
		root.getChildren().addAll(main);
		return root;
	}

	private VBox getPreisBox() {
		final Label preisValue = new Label(Double.toString(preis.getValue()));
		Text eingabePreis = new Text(PREISTEXT);
		
		preis.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    preisValue.setText(String.format("%.2f", new_val));
            }
        });
		HBox priceAndSlider = new HBox(5, preisValue, preis);
		return new VBox(eingabePreis, priceAndSlider);
	}

	/**
	 * Erstellt einen Changelistener welcher nur Number Werte zulässt.
	 * 
	 * @param textField
	 * @return ChangeListener
	 */
	private ChangeListener<String> getNumberCheckChangeListener(TextField textField) {
		return new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		};
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
		return new HBox(5, action, cancel);
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

	/**
	 * Alle Resultate für einen Tag müssen ermittelt werden. hier werden die Daten
	 * gesammelt und die Berechnungen durchgeführt.
	 */
	private void calculateResults() {
		int waffelWert = Integer.parseInt(this.waffeln.getText());
		int litterWert = Integer.parseInt(this.eismenge.getText());
		double preis = this.preis.getValue();
		int besucher = (int) (Math.round((Math.random() * 500) + 500) * this.saleDay.getWeather().getFact());
		int kugeln = litterWert * 12;
		this.saleDay.setVisitors(besucher);
		this.saleDay.setCornetQuantity(waffelWert);
		this.saleDay.setIceQuantity(kugeln);
		this.saleDay.setSalePrice(preis);
		this.saleDay.setCosts(calculateCosts(litterWert, waffelWert));
		calculateEarning();
	}

	/**
	 * Gewinn wird berechnet.
	 */
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
			// Wenn nur noch 1 Kugel da ist düfrfen nicht 2 verkauft werden.
			if (kugeln == 1) {
				anzahl = 1;
			}
			kugeln -= anzahl;
			sold += anzahl;
			earning += (anzahl * this.saleDay.getSalePrice());
		}
		this.saleDay.setEarnings((Math.round((earning - this.saleDay.getCosts()) * 100) / 100));
		GameData.getGame().setGewinn(GameData.getGame().getGewinn() + this.saleDay.getEarnings());
		this.saleDay.setSoldIce(sold);
	}

	private double calculateCosts(int litterWert, int waffelWert) {
		return (litterWert * LITTERCOST) + (waffelWert + WAFFELPREIS);
	}

	private void addCancelEvent(Button cancel) {
		cancel.setOnAction((event) -> {
			StartScene start = new StartScene(this.main);
			main.getScene().setRoot(start.buildScene());
		});
	}

}
