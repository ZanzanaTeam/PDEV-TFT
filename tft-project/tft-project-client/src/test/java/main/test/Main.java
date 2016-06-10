package main.test;

import java.util.List;

import delegate.ServicesBasicDelegate;
import domain.Club;
import domain.Competition;
import domain.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.interfaces.basic.ServicesBasicRemote;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		
//		List<Competition> clubs = new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
//		System.out.println("count => "+clubs.size());
//		System.out.println(clubs);
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