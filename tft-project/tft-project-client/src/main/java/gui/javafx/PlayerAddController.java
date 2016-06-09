package gui.javafx;

import java.util.List;

import javax.swing.JOptionPane;

import delegate.PlayerServicesDelegate;
import delegate.ServicesBasicDelegate;
import domain.Club;
import domain.Player;
import enumeration.AgeRange;
import enumeration.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class PlayerAddController {

	@FXML
	TableView<Player> tablePlayer;
	@FXML
	private TableColumn<Player, String> colFullName;
	@FXML
	private TableColumn<Player, Integer> colAge;
	@FXML
	private TableColumn<Player, Gender> colGender;
	@FXML
	private TableColumn<Player, AgeRange> colLevel;
	@FXML
	private TableColumn<Player, Club> colClub;

	@FXML
	private ComboBox<Gender> comboGender;

	@FXML
	private ComboBox<AgeRange> comboAgeRange;

	@FXML
	private TextField textFullName;

	@FXML
	private TextField textAge;
	@FXML
	private Label labelTitle;
	@FXML
	private TextField textFilter;

	private Boolean isUpdate;

	private Integer id;

	public PlayerAddController() {
		System.out.println("Constructeur");
		isUpdate = false;
	}

	@FXML
	/**
	 * va être executer automatiquement aprés le construteur
	 */
	void initialize() {

		colFullName.setCellValueFactory(new PropertyValueFactory<Player, String>("fullName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Player, Gender>("gender"));
		colAge.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		colLevel.setCellValueFactory(new PropertyValueFactory<Player, AgeRange>("ageRange"));
		colClub.setCellValueFactory(new PropertyValueFactory<Player, Club>("club"));

		TableColumn colName = new TableColumn("Name");
		TableColumn colCity = new TableColumn("City");
		colClub.getColumns().addAll(colName, colCity);

		colCity.setCellValueFactory(new PropertyValueFactory<Player, Club>("club"));
		colCity.setCellFactory(new Callback<TableColumn<Player, Club>, TableCell<Player, Club>>() {

			@Override
			public TableCell<Player, Club> call(TableColumn<Player, Club> param) {

				TableCell<Player, Club> cityCell = new TableCell<Player, Club>() {

					@Override
					protected void updateItem(Club item, boolean empty) {
						if (item != null) {
							Label cityLabel = new Label(item.getCity());
							setGraphic(cityLabel);
						}
					}
				};
				return cityCell;
			}
		});
		colName.setCellValueFactory(new PropertyValueFactory<Player, Club>("club"));
		colName.setCellFactory(new Callback<TableColumn<Player, Club>, TableCell<Player, Club>>() {

			@Override
			public TableCell<Player, Club> call(TableColumn<Player, Club> param) {

				TableCell<Player, Club> cityCell = new TableCell<Player, Club>() {

					@Override
					protected void updateItem(Club item, boolean empty) {
						if (item != null) {
							Label cityLabel = new Label(item.getName());
							setGraphic(cityLabel);
						}
					}
				};
				return cityCell;
			}

		});

		comboGender.setItems(FXCollections.observableArrayList(Gender.values()));
		comboGender.getItems().setAll(Gender.values());

		comboAgeRange.setItems(FXCollections.observableArrayList(AgeRange.values()));
		comboAgeRange.getItems().setAll(AgeRange.values());

		refresh(null);

	}

	private void refresh(List<Player> players) {
		tablePlayer.getItems().clear();
		if (players == null)
			players = new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class);
		if (players != null) {
			ObservableList<Player> data = FXCollections.observableArrayList(players);
			tablePlayer.setItems(data);
		}
		textAge.clear();
		textFullName.clear();
	}

	@FXML
	void actionClickSave(ActionEvent event) {
		System.out.println("je suis dans save ");
		Player player = new Player(textFullName.getText(), comboGender.getValue(), Integer.parseInt(textAge.getText()),
				comboAgeRange.getValue());
		if (isUpdate) {
			player.setId(id);
			new ServicesBasicDelegate<Player>().doCrud().update(player);
			System.out.println("je suis dans update id => " + player.getId());
			isUpdate = false;
			labelTitle.setText("Add player");
		} else {
			new ServicesBasicDelegate<Player>().doCrud().add(player);
		}
		refresh(null);
	}

	@FXML
	void actionClickUpdate(ActionEvent event) {
		System.out.println("je suis dans update ");
		Player e = tablePlayer.getSelectionModel().getSelectedItem();
		if (e != null) {
			id = e.getId();
			textFullName.setText(e.getFullName());
			textAge.setText(e.getAge() + "");
			comboAgeRange.setValue(e.getAgeRange());
			comboGender.setValue(e.getGender());
			labelTitle.setText("Update player < " + e.getFullName() + " >");
			isUpdate = true;
		}
	}

	@FXML
	void actionClickDelete(ActionEvent event) {
		System.out.println("je suis dans delete ");
		Player e = tablePlayer.getSelectionModel().getSelectedItem();
		if (e != null) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this player?", null,
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {

				new ServicesBasicDelegate<Player>().doCrud().delete(e.getId(), Player.class);
				refresh(null);
			}
		}
	}

	@FXML
	void actionKeyReleasedFilter(KeyEvent event) {
		System.out.println("Search => " + textFilter.getText());
		List<Player> players = new PlayerServicesDelegate().getProxy().findPlayerByWord(textFilter.getText());
		System.out.println("result => "+players);
		refresh(players);
	}
}