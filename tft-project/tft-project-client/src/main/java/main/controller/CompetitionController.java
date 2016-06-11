package main.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;
import domain.Match;
import domain.Player;
import domain.Referee;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

public class CompetitionController implements Initializable {
	private ServicesBasicDelegate<Match> proxy;
	@FXML
	TableView<Match> tabMatch;
	@FXML
	TableColumn<Match, Date> colDate;

	@FXML
	Label lbCompetition;
	@FXML
	ComboBox<Competition> cbCompettion;
	@FXML
	ComboBox<Player> cb11;
	@FXML
	ComboBox<Player> cb12;
	@FXML
	ComboBox<Player> cb21;
	@FXML
	ComboBox<Player> cb22;
	@FXML
	ComboBox<Court> cbCourt;
	@FXML
	ComboBox<Referee> cbRefree;
	@FXML
	TextField tfDate;

	@FXML
	Button btnAdd;

	private Match matchSelected;
	private Competition competitionSelected;;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		competitionSelected = null;
		cbCompettion.setItems(FXCollections
				.observableArrayList(new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class)));
		cbCompettion.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<Competition>) (observableValue, oldValue, newValue) -> {
					System.out.println("there");
					if(cbCompettion.getValue()!=(null)){
					competitionSelected = cbCompettion.getSelectionModel().getSelectedItem();
					System.out.println("here");
					refreshTable();
					
					}
				});
		cbCourt.setItems(
				FXCollections.observableArrayList(new ServicesBasicDelegate<Court>().doCrud().findAll(Court.class)));
		cbCourt.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<Court>) (observableValue, oldValue, newValue) -> {
					matchSelected.setCourt(cbCourt.getSelectionModel().getSelectedItem());
					update(matchSelected);
				});
		cbRefree.setItems(FXCollections
				.observableArrayList(new ServicesBasicDelegate<Referee>().doCrud().findAll(Referee.class)));
		cbRefree.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<Referee>) (observableValue, oldValue, newValue) -> {
					matchSelected.setReferee(cbRefree.getSelectionModel().getSelectedItem());
					update(matchSelected);
				});

refreshTable();
		tabMatch.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<Match>) (observableValue, oldValue, newValue) -> {
			
					if (tabMatch.getSelectionModel().getSelectedItem()!= null){
						matchSelected = tabMatch.getSelectionModel().getSelectedItem();
						if(matchSelected.getReferee()!= null) {
						System.out.println(matchSelected.getId());
						for (Referee referee : cbRefree.getItems()) {
							if (referee.getId().equals(matchSelected.getReferee().getId())) {
								cbRefree.setValue(referee);
							}
						}
						
						}else {	cbRefree.setValue(null);}
						if(matchSelected.getReferee()!= null) {
						for (Court court : cbCourt.getItems()) {
							if (court.getId().equals(matchSelected.getCourt().getId())) {
								cbCourt.setValue(court);
							}}
						}else {	cbCourt.setValue(null);}
					}
				});
		colDate.setCellValueFactory(new PropertyValueFactory<Match, Date>("dateMatch"));

		// colDate.setCellValueFactory(new PropertyValueFactory("dateMatch"));
		// colDate.setCellFactory(TextFieldTableCell.<Match,Date>forTableColumn(new
		// DateStringConverter()));

		// tabMatch.setEditable(true);

		// colDate.setCellFactory(TextFieldTableCell.<Match> forTableColumn());
		// colDate.setOnEditCommit((CellEditEvent<Match,String> t) -> {
		// ((Match)
		// t.getTableView().getItems().get(t.getTablePosition().getRow()))
		// .setDateMatch(stringToDate(t.getNewValue()));
		// update(matchSelected);
		// });

	}

	private boolean update(Match match) {
		return getProxy().doCrud().update(match);

	}

	private void Insert(Match match) {
		if (getProxy().doCrud().add(match)) {
			refreshCB();
			/////////////////************************************************************////////////////////////////////////////
			tfDate.setText("");
			tfDate.setPromptText("New Match");
		}

	}

	private void refreshCB() {
		cbCompettion.setItems(FXCollections
				.observableArrayList(new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class)));
		/////////////////////////////999999999999999999999999999999//////////////////////////////////
		if (competitionSelected != null) {
			for (Competition compettion : cbCompettion.getItems()) {
				if (compettion.getId()==(competitionSelected.getId())) {
					cbCompettion.setValue(compettion);
				}
			}
competitionSelected=cbCompettion.getValue();
refreshTable();
		}
		
//		Competition competitionUpdated=new ServicesBasicDelegate<Competition>().doCrud().findById(competitionSelected.getId(), Competition.class);
		
	}

	@FXML
	void btnAddAction() {
		Match lmatch;

		if (!tfDate.getText().isEmpty()) {
			lmatch = new Match();
			lmatch.setDateMatch(stringToDate(tfDate.getText()));
			if (!(lmatch.getDateMatch()==null)) {
				lmatch.setCompetition(competitionSelected);
				Insert(lmatch);
			} 

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getPrimaryStage());
			alert.setTitle("Date is missed");
			alert.setHeaderText("missing given");
			alert.setContentText("Date of new Match missed");
			alert.showAndWait();
		}
	}

	@FXML
	void btnRemoveAction() {
		if (matchSelected != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("delete Match \"" + matchSelected.getDateMatch() + "\"");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				// ... user chose OK

				getProxy().doCrud().delete(matchSelected.getId(), Match.class);
				refreshCB();
//				tabMatch.getItems().remove(matchSelected);
			} else {

			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No \"Match\" Selected");
			alert.setContentText("Please select a Match in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	void linkMatchesAction() {
	}

	ServicesBasicDelegate<Match> getProxy() {
		if (proxy == null)
			proxy = new ServicesBasicDelegate<Match>();
		return proxy;
	}

	Date stringToDate(String string) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getPrimaryStage());
			alert.setTitle("!date format");
			alert.setHeaderText("wrong given");
			alert.setContentText("Date format should be \"dd-MM-yyyy HH:mm\"");
			alert.showAndWait();

		}
		return date;
	}

	void refreshTable(){
		if (competitionSelected != null) {
System.out.println("refresh table");
			tabMatch.setItems(FXCollections.observableArrayList(competitionSelected.getMatchs()));
		}
		else {
			tabMatch.setItems(FXCollections.observableArrayList());
		}
	}
}
