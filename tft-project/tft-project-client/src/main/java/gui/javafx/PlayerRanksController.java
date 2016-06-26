package gui.javafx;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import gui.modele.Country;
import gui.modele.enumeration.StatusRanks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modele.PlayerRank;
import utility.RankingInternational;

public class PlayerRanksController {

	@FXML
	private TableView<PlayerRank> tablePlayer;

	@FXML
	private TableColumn<PlayerRank, StatusRanks> colStatus;

	@FXML
	private TableColumn<PlayerRank, Integer> colRank;

	@FXML
	private TableColumn<PlayerRank, String> colFullName;

	@FXML
	private TableColumn<PlayerRank, Integer> colAge;

	@FXML
	private TableColumn<PlayerRank, Integer> colPoint;

	@FXML
	private TableColumn<PlayerRank, Integer> colCountPlace;

	@FXML
	private ComboBox<Country> comboCountry;
	private String gender;

	public PlayerRanksController() {
		// TODO Auto-generated constructor stub
		System.out.println("Constructeur Classement ATP");
		gender = "atp";
	}

	@FXML
	void initialize() {
		colStatus.setCellValueFactory(new PropertyValueFactory<PlayerRank, StatusRanks>("status"));
		// SETTING THE CELL FACTORY FOR THE ALBUM ART
		colStatus.setCellFactory(
				new Callback<TableColumn<PlayerRank, StatusRanks>, TableCell<PlayerRank, StatusRanks>>() {
					@Override
					public TableCell<PlayerRank, StatusRanks> call(TableColumn<PlayerRank, StatusRanks> param) {
						TableCell<PlayerRank, StatusRanks> cell = new TableCell<PlayerRank, StatusRanks>() {
							@Override
							public void updateItem(StatusRanks item, boolean empty) {
								if (item != null) {
									HBox box = new HBox();
									box.setSpacing(10);
									VBox vbox = new VBox();

									ImageView imageview = new ImageView();
									imageview.setFitHeight(16);
									imageview.setFitWidth(16);
									String icon = "";
									if (item == StatusRanks.DOWN) {
										icon = "down";
									} else if (item == StatusRanks.UP) {
										icon = "up";
									}
									imageview.setImage(
											new Image(PlayerRanksController.class.getResource("iconFx/").toString()
													+ icon + ".png"));
									box.getChildren().addAll(imageview, vbox);
									// SETTING ALL THE GRAPHICS COMPONENT FOR
									// CELL
									setGraphic(box);
								}
							}
						};
						return cell;
					}

				});

		colCountPlace.setCellValueFactory(new PropertyValueFactory<PlayerRank, Integer>("countPlace"));
		colRank.setCellValueFactory(new PropertyValueFactory<PlayerRank, Integer>("rank"));
		colFullName.setCellValueFactory(new PropertyValueFactory<PlayerRank, String>("name"));
		colAge.setCellValueFactory(new PropertyValueFactory<PlayerRank, Integer>("age"));
		// colPoint
		TableColumn<PlayerRank, String> colDiff = new TableColumn<PlayerRank, String>("Diff");
		TableColumn<PlayerRank, Integer> colTotal = new TableColumn<PlayerRank, Integer>("Total");
		colPoint.getColumns().addAll(colDiff, colTotal);
		colDiff.setCellValueFactory(new PropertyValueFactory<PlayerRank, String>("winPoint"));
		colTotal.setCellValueFactory(new PropertyValueFactory<PlayerRank, Integer>("point"));
		colDiff.setCellFactory(new Callback<TableColumn<PlayerRank, String>, TableCell<PlayerRank, String>>() {
			@Override
			public TableCell<PlayerRank, String> call(TableColumn<PlayerRank, String> param) {
				TableCell<PlayerRank, String> cell = new TableCell<PlayerRank, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						Label label = new Label(item);
						try {
							if (Integer.parseInt(item) > 0) {
								label.setStyle("-fx-text-fill: green;");
							} else {
								label.setStyle("-fx-text-fill: red;");
							}
						} catch (Exception ee) {
						}
						setGraphic(label);
					}
				};
				return cell;
			}

		});

		comboCountry.setItems(FXCollections.observableArrayList(Country.getCountry()));

		try {
			refresh(RankingInternational.getClassement(new Date(), "0", "0" , gender));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	@FXML
	void actionComboCountry(ActionEvent event) {
		System.out.println("combo");
		try {

			refresh(RankingInternational.getClassement(new Date(), comboCountry.getValue().getCode(), "0" , gender));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void actionATPButton(ActionEvent event) {
		try {
			gender = "atp";
			String country = (comboCountry.getValue() != null) ? comboCountry.getValue().getCode() : "0";
			refresh(RankingInternational.getClassement(new Date(), country, "0", gender));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	@FXML
	void actionWTAButton(ActionEvent event) {
		try {
			gender = "wta";
			String country = (comboCountry.getValue() != null) ? comboCountry.getValue().getCode() : "0";
			refresh(RankingInternational.getClassement(new Date(), country, "0", gender));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	private void refresh(List<PlayerRank> playerRanks) {
		tablePlayer.getItems().clear();
		if (playerRanks != null) {
			ObservableList<PlayerRank> data = FXCollections.observableArrayList(playerRanks);
			tablePlayer.setItems(data);
		}
	}

}
