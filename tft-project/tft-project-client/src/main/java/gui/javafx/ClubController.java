package gui.javafx;

import java.util.List;

import javax.swing.JOptionPane;

import delegate.ServicesBasicDelegate;
import domain.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class ClubController {

	@FXML
	TableView<Club> tableClub;
	@FXML
	private TableColumn<Club, String> colName;
	@FXML
	private TableColumn<Club, String> colCity;
	@FXML
	private TableColumn<Club, String> colAddress;

	@FXML
	private TextField textName;

	@FXML
	private TextField textCity;

	@FXML
	private TextField textAddress;

	@FXML
	private Label labelTitle;
	@FXML
	private TextField textFilter;

	private Boolean isUpdate;

	private Integer id;

	public ClubController() {
		System.out.println("Constructeur");
		isUpdate = false;
	}

	@FXML
	/**
	 * va être executer automatiquement aprés le construteur
	 */
	void initialize() {

		colName.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
		colCity.setCellValueFactory(new PropertyValueFactory<Club, String>("city"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Club, String>("address"));
		refresh(null);

	}

	private void refresh(List<Club> clubs) {
		tableClub.getItems().clear();
		if (clubs == null)
			clubs = new ServicesBasicDelegate<Club>().doCrud().findAll(Club.class);
		if (clubs != null) {
			ObservableList<Club> data = FXCollections.observableArrayList(clubs);
			tableClub.setItems(data);
		}
		textAddress.clear();
		textCity.clear();
		textName.clear();
	}

	@FXML
	void actionClickSave(ActionEvent event) {
		System.out.println("je suis dans save ");
		Club club = new Club(textName.getText(), textCity.getText(), textAddress.getText());
		if (isUpdate) {
			club.setId(id);
			new ServicesBasicDelegate<Club>().doCrud().update(club);
			isUpdate = false;
			labelTitle.setText("Add Club");
		} else {
			new ServicesBasicDelegate<Club>().doCrud().add(club);
		}
		refresh(null);
	}

	@FXML
	void actionClickUpdate(ActionEvent event) {
		System.out.println("je suis dans update ");
		Club e = tableClub.getSelectionModel().getSelectedItem();
		if (e != null) {
			id = e.getId();
			textName.setText(e.getName());
			textCity.setText(e.getCity());
			textAddress.setText(e.getAddress());
			labelTitle.setText("Update Club < " + e.getName() + " >");
			isUpdate = true;
		}
	}

	@FXML
	void actionClickDelete(ActionEvent event) {
		System.out.println("je suis dans delete ");
		Club e = tableClub.getSelectionModel().getSelectedItem();
		if (e != null) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this club?", null,
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {

				new ServicesBasicDelegate<Club>().doCrud().delete(e.getId(), Club.class);
				refresh(null);
			}
		}
	}

	@FXML
	void actionKeyReleasedFilter(KeyEvent event) {
		System.out.println("Search => " + textFilter.getText());
		// List<Player> players = new
		// PlayerServicesDelegate().getProxy().findPlayerByWord(textFilter.getText());
		// System.out.println("result => " + players);
		// refresh(players);
	}
}