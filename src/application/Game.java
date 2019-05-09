package application;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	// Liste der einzelnen Spieltage
	private List<SaleDay> saleDays;
	
	//Alle rundenübergreifende Variablen
	
	private double gewinn;
	
	public double getGewinn() {
		return gewinn;
	}

	public void setGewinn(double gewinn) {
		this.gewinn = gewinn;
	}

	public Game () {
		saleDays = new ArrayList<SaleDay>();
		this.gewinn = 0;
	}
	
	public List<SaleDay> getSaleDays(){
		return this.saleDays;
	}

}
