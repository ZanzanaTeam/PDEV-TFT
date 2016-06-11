package main.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import delegate.ServicesBasicDelegate;
import domain.Court;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Main;

public class CourtController implements Initializable {
	private ServicesBasicDelegate<Court> proxy;
	@FXML
	TableView<Court> tabCourt;
	@FXML
	TableColumn<Court, String> colName;
	@FXML
	TableColumn<Court, String> colAddress;
	@FXML
	TableColumn<Court, String> colLatitude;
	@FXML
	TableColumn<Court, String> colLongitude;

	@FXML
	TextField tfName;
	@FXML
	TextField tfAddress;
	@FXML
	TextField tfLongitude;
	@FXML
	TextField tfLatitude;
	@FXML
	TextField tfSearch;
	@FXML
	RadioButton rbName;
	@FXML
	RadioButton rbAddress;

	private Court objectSelected;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refreshList();
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colLatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
		colLongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));

		tabCourt.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<Object>) (observableValue, oldValue, newValue) -> {
					objectSelected = tabCourt.getSelectionModel().getSelectedItem();
					if (objectSelected != null) {
						System.out.println(objectSelected.getId());
					}
				});

		tabCourt.setEditable(true);
		colName.setCellFactory(TextFieldTableCell.<Court> forTableColumn());
		colName.setOnEditCommit((CellEditEvent<Court, String> t) -> {
			((Court) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			update(objectSelected);
		});
		colAddress.setCellFactory(TextFieldTableCell.<Court> forTableColumn());
		colAddress.setOnEditCommit((CellEditEvent<Court, String> t) -> {
			((Court) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
			update(objectSelected);
		});

	}

	private List<Court> find(String param, String value) {

		List<Court> courts = null;
		System.out.println(param + "/////" + value);
		courts = (param == null) ? (getProxy().doCrud().findAll(Court.class))
				: (getProxy().doCrud().findBy(Court.class, param, value));
		;

		return courts;
	}

	private boolean update(Court court) {
		return (getProxy().doCrud().update(court));

	}

	private void Insert(Court court) {
		if (getProxy().doCrud().add(court)) {
			refreshList();
			tfName.setText("");
			tfName.setPromptText("new entry");
			tfAddress.setText("");
			tfAddress.setPromptText("new entry");
			tfLatitude.setText("");
			tfLatitude.setPromptText("new entry");
			tfLongitude.setText("");
			tfLongitude.setPromptText("new entry");
		}

	}

	private void refreshList() {
		String param = null;
		String value = null;
		if (!tfSearch.getText().isEmpty()) {
			if (rbName.isSelected()) {
				param = "name";
				value = tfSearch.getText();
			} else {
				param = "address";
				value = tfSearch.getText();
			}

		}
		tabCourt.setItems(FXCollections.observableArrayList(find(param, value)));

	}

	@FXML
	void btnAddAction() {
		Court court;
		String message = "";
		System.out.println("******" + tfName.getText() + "*******");

		System.out.println("******" + tfName.getText() + "*******");
		message += ((!tfName.getText().isEmpty()) ? "" : "Name ");
		message += ((!tfAddress.getText().isEmpty()) ? "" : "Address ");
		message += ((!tfLatitude.getText().isEmpty()) ? "" : "Latitude ");
		message += ((!tfLongitude.getText().isEmpty()) ? "" : "Longitude ");
		if (message.isEmpty()) {
			try {
				court = new Court(tfName.getText(), tfAddress.getText(), Double.parseDouble(tfLatitude.getText()),
						Double.parseDouble(tfLongitude.getText()));
				Insert(court);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(Main.getPrimaryStage());
				alert.setTitle(message + " missed");
				alert.setHeaderText("wrong given");
				alert.setContentText("longitude and latitude must be numeric");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getPrimaryStage());
			alert.setTitle(message + " missed");
			alert.setHeaderText("missing given");
			alert.setContentText(message + "are missed");
			alert.showAndWait();
		}
	}

	@FXML
	void btnRemoveAction() {
		if (objectSelected != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("delete Court \"" + objectSelected.getName() + "\"");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				// ... user chose OK
				
				getProxy().doCrud().delete(objectSelected.getId(), Court.class);
				tabCourt.getItems().remove(objectSelected);
			} else {

			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(Main.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No \"Court\" Selected");
			alert.setContentText("Please select a Court in the table.");
			alert.showAndWait();
		}
	}

	

	@FXML
	void linkMatchesAction() {
	}

	@FXML
	void Action() {
		refreshList();
	}
	ServicesBasicDelegate<Court> getProxy(){
		if(proxy==null)
			proxy=new ServicesBasicDelegate<Court>();
		return proxy;
	}
}
