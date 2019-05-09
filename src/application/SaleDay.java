package application;

public class SaleDay {
	
	private Weather weather;
	private int cornetQuantity;
	private int iceQuantity;
	private double salePrice;
	private int visitors;
	private int soldIce;
	private double costs;
	private double earnings;
	
	public SaleDay() {
		this.weather = new Weather();			
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public int getCornetQuantity() {
		return cornetQuantity;
	}

	public void setCornetQuantity(int cornetQuantity) {
		this.cornetQuantity = cornetQuantity;
	}

	public int getIceQuantity() {
		return iceQuantity;
	}

	public void setIceQuantity(int iceQuantity) {
		this.iceQuantity = iceQuantity;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getVisitors() {
		return visitors;
	}

	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}

	public int getSoldIce() {
		return soldIce;
	}

	public void setSoldIce(int soldIce) {
		this.soldIce = soldIce;
	}

	public double getCosts() {
		return costs;
	}

	public void setCosts(double costs) {
		this.costs = costs;
	}

	public double getEarnings() {
		return earnings;
	}

	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}
	

}
