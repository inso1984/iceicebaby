package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Ice Ice Baby!");
			
	        StackPane root = new StackPane();
	        root.setId("pane");
	        
	        VBox main = new VBox();
	        main.setId("main");
	        			
			Label label1 = new Label("Ice Ice Baby!");
			Text text1 = new Text("ztztwrzt");
			Button button1 = new Button("Spiel starten");
			
			main.getChildren().add(label1);
			main.getChildren().add(text1);
			main.getChildren().add(button1);
			
	        root.getChildren().addAll(main);			
			
			Scene scene = new Scene(root,1200,800);
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
}
