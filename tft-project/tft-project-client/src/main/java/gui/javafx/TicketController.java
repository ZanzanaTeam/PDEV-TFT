package gui.javafx;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import delegate.ServicesBasicDelegate;
import delegate.TicketServicesDelegate;
import domain.Competition;
import domain.Match;
import domain.Ticket;
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

public class TicketController {

	@FXML
	TableView<Ticket> tableTicket;
	@FXML
	private TableColumn<Ticket, Match> colMatch;
	@FXML
	private TableColumn<Ticket, String> colTitle;
	@FXML
	private TableColumn<Ticket, Integer> colQuantity;
	@FXML
	private TableColumn<Ticket, Float> colPrice;

	@FXML
	private Label labelTitle;
	@FXML
	private TextField textTitle;
	@FXML
	private ComboBox<Match> comboMatch;
	@FXML
	private ComboBox<Match> comboByMatch;
	@FXML
	private TextField textQuantity;
	@FXML
	private TextField textPrice;

	private Boolean isUpdate;

	private Integer id;

	public TicketController() {
		System.out.println("Instance Ticket Controller");
		isUpdate = false;
	}

	/**
	 * va être executer automatiquement aprés le construteur
	 */

	@FXML
	void initialize() {
		System.out.println("Init Ticket Controller");
		colTitle.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Title"));
		colMatch.setCellValueFactory(new PropertyValueFactory<Ticket, Match>("Match"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("Quantity"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Ticket, Float>("Price"));

		List<Match> matchs = new ServicesBasicDelegate<Match>().doCrud().findAll(Match.class);
		if (matchs != null) {
			comboMatch.setItems(FXCollections.observableArrayList(matchs));
		}
		List<Match> listMatchs = new ServicesBasicDelegate<Match>().doCrud().findAll(Match.class);
		if (listMatchs != null) {
			comboByMatch.setItems(FXCollections.observableArrayList(matchs));
		}
		refresh(null);
	}

	private void refresh(List<Ticket> tickets) {
		tableTicket.getItems().clear();
		if (tickets == null)
			tickets = new ServicesBasicDelegate<Ticket>().doCrud().findAll(Ticket.class);
		System.out.println("Liste => " + tickets);
		if (tickets != null) {
			ObservableList<Ticket> data = FXCollections.observableArrayList(tickets);
			tableTicket.setItems(data);
		}
		textTitle.clear();
		comboMatch.getSelectionModel().clearSelection();
		textQuantity.clear();
		textPrice.clear();
	}

	@FXML
	void actionClickSave(ActionEvent event) {
		System.out.println("je suis dans save ");
		Ticket ticket = new Ticket(textTitle.getText(), null, Integer.parseInt(textQuantity.getText()),
				Float.parseFloat(textPrice.getText()));

		if (comboMatch.getValue() != null) {
			ticket.setMatch(comboMatch.getValue());
		}
		if (isUpdate) {
			ticket.setId(id);
			new ServicesBasicDelegate<Ticket>().doCrud().update(ticket);
			System.out.println("je suis dans update id => " + ticket.getId());
			isUpdate = false;
			labelTitle.setText("Add ticket");
		} else {
			new ServicesBasicDelegate<Ticket>().doCrud().add(ticket);
		}
		refresh(null);
	}

	@FXML
	void actionClickUpdate(ActionEvent event) {
		System.out.println("je suis dans update ");
		Ticket t = tableTicket.getSelectionModel().getSelectedItem();
		if (t != null) {
			id = t.getId();
			textTitle.setText(t.getTitle());
			comboMatch.setValue(t.getMatch());
			textQuantity.setText(t.getQuantity().toString());
			textPrice.setText(t.getPrice().toString());
			labelTitle.setText("Update Ticket < " + t.getTitle() + " >");
			isUpdate = true;
		}
	}

	@FXML
	void actionClickDelete(ActionEvent event) {
		System.out.println("je suis dans delete ");
		Ticket e = tableTicket.getSelectionModel().getSelectedItem();
		if (e != null) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this Ticket?", null,
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {

				new ServicesBasicDelegate<Ticket>().doCrud().delete(e.getId(), Ticket.class);
				refresh(null);
			}
		}
	}

	@FXML
	void actionClickFilterCometition(ActionEvent event) {
		System.out.println("je suis dans filter ");
		Match match = comboByMatch.getSelectionModel().getSelectedItem();
		if (match != null) {
			List<Ticket> tickets = new TicketServicesDelegate().getProxy().findTicketByMatch(match);
			System.out.println("result => " + tickets);
			refresh(tickets);
		}
	}
}
