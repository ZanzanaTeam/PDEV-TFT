package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage stage = primaryStage ; 
		SplitPane root = FXMLLoader.load(getClass().getResource("/gui/javafx/ListCompetitionView.fxml"));
	    
        Scene scene = new Scene(root, 800, 800);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
	
		

	}
}