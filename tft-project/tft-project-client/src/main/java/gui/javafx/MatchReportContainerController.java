package gui.javafx;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.MatchSingle;
import domain.Player;
import domain.Referee;
import gui.MainFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class MatchReportContainerController {

	@FXML
	private TableView<MatchSingle> tableMatch;

	@FXML
	private TableColumn<MatchSingle, Integer> colId;
	@FXML
	private TableColumn<MatchSingle, Date> colDate;
	@FXML
	private TableColumn<MatchSingle, Referee> colReferee;
	@FXML
	private TableColumn<MatchSingle, Competition> colCompetition;

	private List<MatchSingle> matchSingles;

	public MatchReportContainerController() {
		matchSingles = new ServicesBasicDelegate<MatchSingle>().doCrud().findAll(MatchSingle.class);
	}

	@FXML
	void initialize() {
		colId.setCellValueFactory(new PropertyValueFactory<MatchSingle, Integer>("id"));
		colDate.setCellValueFactory(new PropertyValueFactory<MatchSingle, Date>("dateMatch"));
		colReferee.setCellValueFactory(new PropertyValueFactory<MatchSingle, Referee>("referee"));
		colCompetition.setCellValueFactory(new PropertyValueFactory<MatchSingle, Competition>("competition"));

		ObservableList<MatchSingle> data = FXCollections.observableArrayList(matchSingles);
		tableMatch.setItems(data);
	}

	@FXML
	private void actionClickGenerate(ActionEvent event) throws IOException {
		System.out.println("je suis dans generer");

		FXMLLoader fxmlLoader = new FXMLLoader(MatchReportController.class.getResource("matchreport.fxml"));

		MatchReportController matchReportController = new MatchReportController();
		MatchSingle e = tableMatch.getSelectionModel().getSelectedItem();
		if(e == null) return;
		matchReportController.setMatch_id(e.getId());
		
		fxmlLoader.setController(matchReportController);
		
		BorderPane root = (BorderPane) fxmlLoader.load();
		if (MainFrame.fxPanel != null)
			MainFrame.fxPanel.setVisible(false);
		MainFrame.fxPanel = new JFXPanel();
		Scene scene = new Scene(root);
		MainFrame.fxPanel.setScene(scene);

		MainFrame.jframe.getContentPane().add(MainFrame.fxPanel, BorderLayout.CENTER);
		MainFrame.jframe.getContentPane().revalidate();
		MainFrame.jframe.getContentPane().repaint();

	}

}
