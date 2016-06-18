package gui.javafx;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

import delegate.ServicesBasicDelegate;
import domain.MatchReport;
import domain.MatchSingle;
import domain.Player;
import enumeration.WeatherState;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import utility.GoogleDrive;

public class MatchReportController {
	/**
	 * Generate Match Report
	 * 
	 */
	private Boolean isUpdate;
	private Integer id;
	private Integer match_id;
	List<Integer> setRange = new ArrayList<>();

	@FXML
	private Label labelCompetition;

	@FXML
	private Label labelCourt;

	@FXML
	private Label labelReferee;

	@FXML
	private Label labelPlayer1;

	@FXML
	private Label labelPlayer2;

	@FXML
	private Label labelDateMatch;

	@FXML
	private ComboBox<WeatherState> cmbWeather;
	@FXML
	private ComboBox<Integer> cmbSet1Player1;
	@FXML
	private ComboBox<Integer> cmbSet1Player2;
	@FXML
	private ComboBox<Integer> cmbSet2Player1;
	@FXML
	private ComboBox<Integer> cmbSet2Player2;
	@FXML
	private ComboBox<Integer> cmbSet3Player1;
	@FXML
	private ComboBox<Integer> cmbSet3Player2;
	@FXML
	private ComboBox<Player> cmbWinner;
	@FXML
	private TextArea txtareaPlayers;

	private MatchSingle matchSingle;
	
	/**
	 * Match Report create
	 * 
	 */

	public MatchReportController() {
		for (int i = 0; i < 8; i++) {
			setRange.add(i);
		}
		System.out.println(setRange);
		System.out.println("Constructeur");
	}

	@FXML
	void initialize() {

		matchSingle = new ServicesBasicDelegate<MatchSingle>().doCrud().findById(getMatch_id(),
				MatchSingle.class);

		labelCourt.setText(matchSingle.getCourt().getName());
		labelReferee.setText(matchSingle.getReferee().getFullName());
		labelPlayer1.setText(matchSingle.getPlayer().getFullName());
		labelPlayer2.setText(matchSingle.getPlayer2().getFullName());
		labelCompetition.setText(matchSingle.getCompetition().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		labelDateMatch.setText(sdf.format(matchSingle.getDateMatch()));
		cmbWeather.setItems(FXCollections.observableArrayList(WeatherState.values()));
		cmbWeather.getItems().setAll(WeatherState.values());

		cmbSet1Player1.setItems(FXCollections.observableArrayList(setRange));
		cmbSet1Player2.setItems(FXCollections.observableArrayList(setRange));

		cmbSet2Player1.setItems(FXCollections.observableArrayList(setRange));

		cmbSet2Player2.setItems(FXCollections.observableArrayList(setRange));

		cmbSet3Player1.setItems(FXCollections.observableArrayList(setRange));

		cmbSet3Player2.setItems(FXCollections.observableArrayList(setRange));

		cmbWinner.setItems(
				FXCollections.observableArrayList(new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class)));

	}

	@FXML
	void actionClickSave(ActionEvent event) {

		System.out.println("je suis dans save ");

		System.out.println(txtareaPlayers.getText());

		MatchReport matchReport = new MatchReport("", txtareaPlayers.getText(), new Date());
		matchReport.setPlayer1(matchSingle.getPlayer());
		matchReport.setPlayer2(matchSingle.getPlayer2());
		matchReport.setCourt(matchSingle.getCourt());
		matchReport.setMatch_game(matchSingle);
		new ServicesBasicDelegate<MatchReport>().doCrud().add(matchReport);
		
		try {
			new Pdf().createPdf("pdfws.pdf", matchReport);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			GoogleDrive.uplodeGoogleDrive("D:/pdfws.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	public Integer getMatch_id() {
		return match_id;
	}

	public void setMatch_id(Integer match_id) {
		this.match_id = match_id;
	}

}