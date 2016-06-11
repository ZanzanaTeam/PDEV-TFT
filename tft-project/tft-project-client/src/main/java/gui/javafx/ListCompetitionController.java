package gui.javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	@FXML
	TableView<Match> tableMatch;
	@FXML
	TableColumn<Match, String> columnDate;
	@FXML
	TableColumn<Match, String> columnCourt;
	@FXML
	TableColumn<Match, String> columnReferee;

	ObservableList<Competition> listDataCompetetion;



	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		
		Competition cp = new Competition();
		cp.setName("Competition 2");
		List<Competition> listsComp = new ArrayList<Competition>();
		listsComp = new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
		listDataCompetetion = FXCollections.observableList(listsComp);
		System.out.println(listDataCompetetion.toString());
		comboCompetition.setItems(listDataCompetetion);

		comboCompetition.setConverter(new StringConverter<Competition>() {
			@Override
			public String toString(Competition object) {

				if (object == null) {
					System.out.println("null");
					return "[none]";
				}

			
				System.out.println(object.getId());

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

	public void initMatchTable(Integer idCompetition) {
				System.out.println("Size : "+listDataMatch.size());
		System.out.println(listDataMatch.toString());
		columnDate.setCellValueFactory(new PropertyValueFactory<Match, String>("dateMatch"));
		//columnCourt.setCellValueFactory(new PropertyValueFactory<Match, String>("court"));
		// columnReferee.setCellValueFactory(new PropertyValueFactory<Match,
		// String>("referee"));
		tableMatch.setItems(listDataMatch);

	}
}
