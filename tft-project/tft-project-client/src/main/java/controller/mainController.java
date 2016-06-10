package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class mainController implements Initializable{

	@FXML
	private MenuItem listPlayer ; 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listPlayer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
			}
		});
		
	}

}
