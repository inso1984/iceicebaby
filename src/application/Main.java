package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Scene scene;
	private SaleDay[] saleDays = new SaleDay[10];
	private int round = 0;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Fenstertitel
			primaryStage.setTitle("Ice Ice Baby!");
			
			// Baut den Startbildschirm auf
			StartScene start = new StartScene(this);
			
			
	        // Startbildschirm
			//new Scene(Pane,width,height) Pane = Designelement, breite, höhe
			this.scene = new Scene(start.buildStartScene(),1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

	        

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		

	}
	public Scene getScene() {
		return this.scene;
	}

	public SaleDay[] getSaleDays() {
		return saleDays;
	}

	public void setSaleDays(SaleDay[] saleDays) {
		this.saleDays = saleDays;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	
}
