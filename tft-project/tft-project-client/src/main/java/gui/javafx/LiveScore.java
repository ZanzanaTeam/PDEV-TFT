package gui.javafx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import delegate.LiveScoreDelegate;
import domain.MatchSingle;
import domain.Player;
import gui.modele.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class LiveScore {

	@FXML
	private FlowPane flowContainer;

	private List<MatchSingle> matchSingles;

	public LiveScore() throws ParseException {

		// matchSingles = new ArrayList<MatchSingle>();
		// Player player = new Player("player1", Gender.Male, 20,
		// AgeRange.Adulte);
		// Player player2 = new Player("player2", Gender.Male, 20,
		// AgeRange.Adulte);
		//
		// MatchSingle matchSingle = new MatchSingle(player, player2);
		// matchSingle.setDateMatch(new Date());
		// matchSingle.setLiveScore("4:3#0:0#15:30#0:1");
		// matchSingles.add(matchSingle);
		//
		// matchSingle = new MatchSingle(player, player2);
		// matchSingle.setDateMatch(new Date());
		// matchSingle.setLiveScore("6:3#1:0#15:30#1:0");
		// matchSingles.add(matchSingle);
		//
		// matchSingle = new MatchSingle(player, player2);
		// matchSingle.setDateMatch(new Date());
		// matchSingle.setLiveScore("0:0#0:0#40:Av#1:0");
		// matchSingles.add(matchSingle);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date =  sdf.parse("2016-06-18");
		matchSingles = new LiveScoreDelegate().getProxy().getMatchByDate(date);
		System.out.println(matchSingles);
	}

	@FXML
	void initialize() {
		flowContainer.setVgap(8);
		flowContainer.setHgap(4);

		for (MatchSingle matchSingle : matchSingles) {
			addTableScore(matchSingle);
		}
	}

	private void addTableScore(MatchSingle matchSingle) {
		TableView<Score> tableView = new TableView<Score>();
		tableView.setMaxWidth(280);
		tableView.setMaxHeight(80);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// tableView.setPadding(new Insets(10, 0, 0, 10));
		TableColumn<Score, Player> colPlayer = new TableColumn<Score, Player>("");
		colPlayer.setMinWidth(100);
		TableColumn<Score, Integer> colCurrentManche = new TableColumn<Score, Integer>("");
		TableColumn<Score, String> colPoint = new TableColumn<Score, String>("Pt");
		TableColumn<Score, Boolean> colIndServices = new TableColumn<Score, Boolean>("#");
		TableColumn<Score, String> colManches = new TableColumn<Score, String>("");
		colManches.setMinWidth(50);
		Score score = new Score();
		Score score2 = new Score();

		score.setPlayer(matchSingle.getPlayer());
		score2.setPlayer(matchSingle.getPlayer2());

		String[] scoreArray = matchSingle.getLiveScore().split("T");

		// 1er partie (Manches)
		String[] manchesArray = scoreArray[0].split("S");
		if (manchesArray.length > 0) {
			for (String manche : manchesArray) {
				score.getManches().add(Integer.parseInt(manche.split(":")[0]));
				score2.getManches().add(Integer.parseInt(manche.split(":")[1]));
			}
		}

		// 2eme partie CurrentManche;
		score.setCurrentManche(Integer.parseInt(scoreArray[1].split(":")[0]));
		score2.setCurrentManche(Integer.parseInt(scoreArray[1].split(":")[1]));

		// 3eme partie point
		score.setPoint(scoreArray[2].split(":")[0]);
		score2.setPoint(scoreArray[2].split(":")[1]);

		// 3eme partie Service
		score.setIndServices((Integer.parseInt(scoreArray[3].split(":")[0]) == 1) ? true : false);
		score2.setIndServices((Integer.parseInt(scoreArray[3].split(":")[1]) == 1) ? true : false);

		System.out.println(score);
		System.out.println(score2);

		colIndServices.setCellValueFactory(new PropertyValueFactory<Score, Boolean>("indServices"));
		colIndServices.setCellFactory(new Callback<TableColumn<Score, Boolean>, TableCell<Score, Boolean>>() {
			@Override
			public TableCell<Score, Boolean> call(TableColumn<Score, Boolean> param) {
				TableCell<Score, Boolean> cell = new TableCell<Score, Boolean>() {
					@Override
					public void updateItem(Boolean item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();

							ImageView imageview = new ImageView();
							imageview.setFitHeight(16);
							imageview.setFitWidth(16);
							if (item) {
								imageview.setImage(new Image(
										PlayerRanksController.class.getResource("iconFx/ball.png").toString()));
								box.getChildren().addAll(imageview, vbox);
							}
							// SETTING ALL THE GRAPHICS COMPONENT FOR
							// CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});
		colPlayer.setCellValueFactory(new PropertyValueFactory<Score, Player>("player"));
		colManches.setCellValueFactory(new PropertyValueFactory<Score, String>("manches"));
		colCurrentManche.setCellValueFactory(new PropertyValueFactory<Score, Integer>("currentManche"));
		colPoint.setCellValueFactory(new PropertyValueFactory<Score, String>("point"));

		tableView.getColumns().addAll(colIndServices, colPlayer, colManches, colCurrentManche, colPoint);

		ObservableList<Score> data = FXCollections.observableArrayList(score, score2);
		tableView.setItems(data);

		flowContainer.getChildren().add(tableView);
	}
}
