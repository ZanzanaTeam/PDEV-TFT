package gui.javafx;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import delegate.ServicesBasicDelegate;
import domain.Contest;
import domain.Match;
import domain.Referee;
import domain.Training;
import enumeration.CompetitionLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RefereeController implements Initializable {

	@FXML

	Label lblFullName;
	Label lblAge;
	Label lblCompLevel;
	ComboBox<Match> cmbMatchs;
	ComboBox<Training> cmbTrainings;
	ComboBox<Contest> cmbContests;
	ObservableList<Referee> observableList ; 


	public RefereeController() {
		System.out.println("RefereeController");
	}

	@FXML
	private TableView<Referee> tableReferee;
	@FXML
	private TableColumn<Referee, String> columnReferee;
	ObservableList<Training> listTrainings = FXCollections.observableArrayList();
	ObservableList<Match> listMatchs = FXCollections.observableArrayList();
	ObservableList<Contest> listContests = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTable();
		tableReferee.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showRefereeDetails(newValue));

	}

	private void showRefereeDetails(Referee newValue) {
		System.out.println(newValue.getFullName());
		lblFullName.setText(newValue.getFullName());
		lblAge.setText(newValue.getAge().toString());
		lblCompLevel.setText(newValue.getCompetitionLevel().toString());
		cmbMatchs.setItems(listMatchs);
		cmbMatchs.getSelectionModel().selectFirst();
		cmbTrainings.setItems(listTrainings);
		cmbTrainings.getSelectionModel().selectFirst();
		cmbContests.setItems(listContests);
		cmbContests.getSelectionModel().selectFirst();
		
	}

	private void initTable() {
		Referee ref = new Referee("fullName", 30,CompetitionLevel.International, null,null,null);
		new ServicesBasicDelegate<Referee>().doCrud().add(ref);
		observableList = FXCollections.
				 observableList(new ServicesBasicDelegate<Referee>()
				 .doCrud().findAll(Referee.class));
		columnReferee.setCellValueFactory(new PropertyValueFactory<Referee, String>("fullName"));
		tableReferee.setItems(observableList);

	}
}
