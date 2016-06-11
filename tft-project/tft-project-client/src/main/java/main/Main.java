package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("pages/" + "Court.fxml"))));
		primaryStage.show();
		this.primaryStage = primaryStage;
	}

	public static Window getPrimaryStage() {
		return primaryStage;
	}

}
