package gui.javafx;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;

import delegate.RefereeServicesDelegate;
import delegate.ServicesBasicDelegate;
import domain.Court;
import domain.Match;
import domain.Referee;
import enumeration.CompetitionLevel;
import enumeration.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class RefereeController {
	/**
	 * Crud Referee
	 * 
	 */
	@FXML
	TableView<Referee> tableReferee;
	@FXML
	private TableColumn<Referee, String> colFullName;
	@FXML
	private TableColumn<Referee, Integer> colAge;
	@FXML
	private TableColumn<Referee, Gender> colGender;
	@FXML
	private TableColumn<Referee, CompetitionLevel> colLevel;

	@FXML
	private ComboBox<Gender> comboGender;

	@FXML
	private ComboBox<CompetitionLevel> comboCompLevel;

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

	/**
	 * Match List
	 * 
	 */

	@FXML
	private TableView<Match> tableMatch;

	@FXML
	private TableColumn<Match, Date> colDateTableMatch;

	@FXML
	private TableColumn<Match, Court> colCourtTableMatch;

	public RefereeController() {
		System.out.println("Constructeur");
		isUpdate = false;
	}

	@FXML
	/**
	 * va être executer automatiquement aprés le construteur
	 */
	void initialize() {

		colFullName.setCellValueFactory(new PropertyValueFactory<Referee, String>("fullName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Referee, Gender>("gender"));
		colAge.setCellValueFactory(new PropertyValueFactory<Referee, Integer>("age"));
		colLevel.setCellValueFactory(new PropertyValueFactory<Referee, CompetitionLevel>("competitionLevel"));

		comboGender.setItems(FXCollections.observableArrayList(Gender.values()));
		comboGender.getItems().setAll(Gender.values());

		comboCompLevel.setItems(FXCollections.observableArrayList(CompetitionLevel.values()));
		comboCompLevel.getItems().setAll(CompetitionLevel.values());
		refresh(null);
	}

	private void refresh(List<Referee> referees) {
		tableReferee.getItems().clear();
		if (referees == null)
			referees = new ServicesBasicDelegate<Referee>().doCrud().findAll(Referee.class);
		if (referees != null) {
			ObservableList<Referee> data = FXCollections.observableArrayList(referees);
			tableReferee.setItems(data);
		}
		textAge.clear();
		textFullName.clear();
	}

	@FXML
	void actionClickSave(ActionEvent event) {
		System.out.println("je suis dans save ");
		Referee referee = new Referee(textFullName.getText(), Integer.parseInt(textAge.getText()),
				comboGender.getValue(), comboCompLevel.getValue());
		if (isUpdate) {
			referee.setId(id);
			new ServicesBasicDelegate<Referee>().doCrud().update(referee);
			System.out.println("je suis dans update id => " + referee.getId());
			isUpdate = false;
			labelTitle.setText("Add Referee");
		} else {
			new ServicesBasicDelegate<Referee>().doCrud().add(referee);
		}
		refresh(null);
	}

	@FXML
	void actionClickUpdate(ActionEvent event) {
		System.out.println("je suis dans update ");
		Referee e = tableReferee.getSelectionModel().getSelectedItem();
		if (e != null) {
			id = e.getId();
			textFullName.setText(e.getFullName());
			textAge.setText(e.getAge() + "");
			comboCompLevel.setValue(e.getCompetitionLevel());
			comboGender.setValue(e.getGender());
			labelTitle.setText("Update Referee < " + e.getFullName() + " >");
			isUpdate = true;
		}
	}

	@FXML
	void actionClickDelete(ActionEvent event) {
//		System.out.println("je suis dans delete ");
//		Referee e = tableReferee.getSelectionModel().getSelectedItem();
//		if (e != null) {
//			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Referee?", null,
//					JOptionPane.YES_NO_OPTION);
//			if (result == JOptionPane.YES_OPTION) {
//
//				new ServicesBasicDelegate<Referee>().doCrud().delete(e.getId(), Referee.class);
//				refresh(null);
//			}
//		}
		try {
			
			new Pdf().createPdf("file1.pdf");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void actionKeyReleasedFilter(KeyEvent event) {
		System.out.println("Search => " + textFilter.getText());
		List<Referee> referees = new RefereeServicesDelegate().getProxy().findRefereeByWord(textFilter.getText());
		System.out.println("result => " + referees);
		refresh(referees);
	}
	
	@FXML
	void actionMouseClickedTableReferee(MouseEvent event){
		Referee e = tableReferee.getSelectionModel().getSelectedItem();
		System.out.println(e);
	}
}