package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.controller.CompetitionController;

public class MainCompetition extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private static Stage primaryStage;
	private static domain.Competition comp;

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		primaryStage
				.setScene(new Scene(FXMLLoader.load(MainCompetition.class.getResource("pages/" + "Competition.fxml"))));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}

	public static Window getPrimaryStage() {
		return primaryStage;
	}

}
