package gui.javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import delegate.CompetitionServicesDelegate;
import delegate.ServicesBasicDelegate;
import domain.Competition;
import enumeration.CompetitionLevel;
import formatDate.FormatDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class CompetitionController implements Initializable {

	@FXML
	private TextField txtName;
	@FXML
	private DatePicker txtStartDate;
	@FXML
	private DatePicker txtEndDate;
	@FXML
	private Label labelTitle;
	@FXML
	private TextField textFilter;
	@FXML
	private ComboBox<CompetitionLevel> comboLevel;
	@FXML
	private TextField comboSite;
	@FXML
	private TextField comboCountry;

	@FXML
	private TableView<Competition> tableCompetition;
	@FXML
	private Button btnSave1;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private TableColumn<Competition, String> nameCompetition;
	@FXML
	private TableColumn<Competition, String> startDate;
	@FXML
	private TableColumn<Competition, String> endDate;
	@FXML
	private TableColumn<Competition, String> countryCompet;
	@FXML
	private TableColumn<Competition, String> levelComp;
	@FXML
	private TableColumn<Competition, String> siteCompt;
	ObservableList<Competition> observableList;

	private Boolean isUpdate;

	private Integer id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		isUpdate = false;
		initTable();

		comboLevel.getItems().setAll(CompetitionLevel.values());

		btnSave1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("je suis dans save ");

				Competition Competition = new Competition(txtName.getText(),
						FormatDate.ToUtilDate(txtStartDate.getValue()), FormatDate.ToUtilDate(txtEndDate.getValue()),
						comboCountry.getText(), comboSite.getText(), comboLevel.getSelectionModel().getSelectedItem(),
						null);
				if (isUpdate) {
					Competition.setId(id);
					new ServicesBasicDelegate<Competition>().doCrud().update(Competition);

					isUpdate = false;
					labelTitle.setText("Add Competition");
				} else {
					new ServicesBasicDelegate<Competition>().doCrud().add(Competition);
				}
				refresh(null);

			}
		});

		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Competition e = tableCompetition.getSelectionModel().getSelectedItem();
				System.out.println(e.getName());
				if (e != null) {
					id = e.getId();
					txtName.setText(e.getName());

					comboCountry.setText(e.getCountry());
					comboSite.setText(e.getSite());
					txtStartDate.setValue(FormatDate.asLocalDate(e.getStartDate()));
					txtEndDate.setValue(FormatDate.asLocalDate(e.getEndDate()));
					comboLevel.setValue(e.getCompetitionLevel());
					labelTitle.setText("Update player < " + e.getName() + " >");
					isUpdate = true;
				}

			}
		});

		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("je suis dans delete ");
				Competition cp = tableCompetition.getSelectionModel().getSelectedItem();
				if (cp != null) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this player?",
							null, JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {

						new ServicesBasicDelegate<Competition>().doCrud().delete(cp.getId(), Competition.class);
						refresh(null);
					}
				}

			}
		});
		textFilter.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				System.out.println("Search => " + textFilter.getText());
				List<Competition> competitions = new CompetitionServicesDelegate().getProxy()
						.findCompetitionByWord(textFilter.getText());
				System.out.println("result => " + competitions);
				refresh(competitions);

			}
		});
	}

	void initTable() {

		observableList = FXCollections
				.observableList(new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class));

		nameCompetition.setCellValueFactory(new PropertyValueFactory<Competition, String>("name"));
		startDate.setCellValueFactory(new PropertyValueFactory<Competition, String>("startDate"));
		endDate.setCellValueFactory(new PropertyValueFactory<Competition, String>("endDate"));
		countryCompet.setCellValueFactory(new PropertyValueFactory<Competition, String>("country"));
		levelComp.setCellValueFactory(new PropertyValueFactory<Competition, String>("competitionLevel"));
		siteCompt.setCellValueFactory(new PropertyValueFactory<Competition, String>("Site"));

		tableCompetition.setItems(observableList);
	}

	private void refresh(List<Competition> competitions) {
		tableCompetition.getItems().clear();
		if (competitions == null)
			competitions = new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
		if (competitions != null) {
			ObservableList<Competition> data = FXCollections.observableArrayList(competitions);
			tableCompetition.setItems(data);
		} else {
			System.out.println("Liste Vide");
		}
		txtName.clear();
		comboCountry.clear();
		comboSite.clear();

	}

}
