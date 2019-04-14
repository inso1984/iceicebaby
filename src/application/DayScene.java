package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DayScene {

	private Main main;
	
	public DayScene(Main main) {
		this.main = main;
	}
	
	public Pane buildScene() {
		// Hintergrundbild im Fenster
        StackPane root = new StackPane();
        root.setId("pane");
        SaleDay saleDay = new SaleDay();
        
        
        Button cancel = new Button("Spiel verlassen");
        addCancelEvent(cancel);
        root.getChildren().add(cancel);
        return root;
	}
	
	
	private void addCancelEvent(Button cancel) {
		cancel.setOnAction((event) -> {
			StartScene start = new StartScene(this.main);
			main.getScene().setRoot(start.buildStartScene());
		});
	}
	
}
