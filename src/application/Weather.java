package application;

public class Weather {

	private int temperature;
	private double fact = 0.2;
	private String text;

	public Weather() {
		setTemperature();
		setAdjectives();
	}

	private void setTemperature() {

		this.temperature = (int) (Math.random() * 19) + 20;
		System.out.println("Heute sind es " + temperature + " Grad Celsius.");

	}

	private double setAdjectives() {

		int number = (int) (Math.random() * 4) + 1;

		switch (number) {

		case 1:
			if (this.temperature < 23) {
				this.text = "Es ist stürmisch und am Nachmittag soll es regnen.";
				fact = 0.25;
			} else if (this.temperature >= 23 && this.temperature <= 28) {
				this.text = "Hin und wieder sind leichte Schauer zu erwarten.";
				fact = 0.5;
			} else {
				this.text = "Es soll einen kurzen Schauer am Nachmittag geben.";
				fact = 0.75;
			}
			break;
		case 2:
			if (this.temperature < 27) {
				this.text = "Am Vormittag gibt es einen leichten Nieselregen.";
				fact = 0.6;
			} else {
				this.text = "Die Sonne scheint den ganzen Tag und es bleibt trocken.";
				fact = 0.8;
			}
			break;
		case 3:
			if (this.temperature < 25) {
				this.text = "Es ist stark bewölkt und windet sehr.";
				fact = 0.3;
			} else if (this.temperature >= 25 && this.temperature <= 35) {
				this.text = "Es ist ab und zu leicht bewölkt.";
				fact = 0.7;
			} else {
				this.text = "Heute wird es sehr heiss!";
				fact = 0.8;
			}
			break;
		case 4:
			if (this.temperature < 23) {
				this.text = "Gegen Mittag soll ein starkes Gewitter aufziehen.";
				fact = 0.15;
			} else if (this.temperature >= 23 && this.temperature <= 30) {
				this.text = "Am Vormittag bleibt es trocken während es ab Mittag immer mal wieder regnen kann.";
				fact = 0.4;
			} else {
				this.text = "Der Himmel ist Wolkenfrei und die Sonne scheint den ganzen Tag.";
				fact = 0.9;
			}
		}
		return fact;

	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public double getFact() {
		return fact;
	}

	public void setFact(double fact) {
		this.fact = fact;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
