package gui.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

public class ListCompetitionController implements Initializable {

	@FXML
	ComboBox<Competition> comboCompetition;

	@FXML
	Label lblName;
	@FXML
	Label lblStartDate;
	@FXML
	Label lblEndDate;
	@FXML
	Label lblCountry;
	@FXML
	Label lblLevel;
	@FXML
	Label lblSite;

	ObservableList<Competition> listDataCompetetion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listDataCompetetion = FXCollections
				.observableList(new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class));

		comboCompetition.setItems(listDataCompetetion);

		comboCompetition.setConverter(new StringConverter<Competition>() {
			@Override
			public String toString(Competition object) {

				if (object == null) {
					System.out.println("null");
					return "[none]";
				}
				
			
				lblName.setText(object.getName());
				lblStartDate.setText(object.getStartDate().toString());
				lblEndDate.setText(object.getEndDate().toString());
				lblCountry.setText(object.getCountry());
				lblLevel.setText(object.getCompetitionLevel().toString());
				lblSite.setText(object.getSite());
				
				return object.getName();
			}

			@Override
			public Competition fromString(String string) {
				throw new RuntimeException("not required for non editable ComboBox");
			}

		});
		
		

	}

}
