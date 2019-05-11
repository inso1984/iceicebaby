package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartScene implements ApplicationScene{
	private Main main;
	public StartScene(Main main) {
		this.main = main;
	}
	
	public Pane buildScene() {		
		
		// Hintergrundbild im Fenster
        StackPane root = new StackPane();
        root.setId("pane");
        
        // Container
        VBox main = new VBox();
        main.setId("main");
        
        // Container-Inhalt
		Label label1 = new Label("Ice Ice Baby!");
		Text text1 = new Text("Beweise dein Talent als Eisverkäufer ...\n");
		text1.setId("text");
		Button startButton = new Button("Spiel starten");
		// startet die Funktion um eine neue Scene zu erstellen
		addStartFunction(startButton);
		
		// Inhalt in den Container einfügen
		main.getChildren().add(label1);
		main.getChildren().add(text1);
		main.getChildren().add(startButton);
        root.getChildren().addAll(main);			
		
		
		return root;
	}	
	
	// Funktion zum starten einer neuen Tagesscene, wird durch klicken auf den Starbutton aufgerufen
	private void addStartFunction(Button startButton) {
		startButton.setOnAction((event) -> {
			RulesScene scene = new RulesScene(this.main);
		    main.getScene().setRoot(scene.buildScene());
		});
	}
}
