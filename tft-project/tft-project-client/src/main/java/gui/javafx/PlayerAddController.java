package gui.javafx;

import java.util.List;

import delegate.ServicesBasicDelegate;
import domain.Club;
import domain.Player;
import enumeration.AgeRange;
import enumeration.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
//	@FXML
//	private TableColumn<Player, Club> colCity;
	
	public PlayerAddController() {
		System.out.println("Constructeur");
	}

	@FXML
	/**
	 * va être executer automatiquement aprés le construteur
	 */
	 void initialize(){
		
		colFullName.setCellValueFactory(new PropertyValueFactory<Player,String>("fullName"));	
		colGender.setCellValueFactory(new PropertyValueFactory<Player,Gender>("gender"));
		colAge.setCellValueFactory(new PropertyValueFactory<Player,Integer>("age"));	
		colLevel.setCellValueFactory(new PropertyValueFactory<Player,AgeRange>("ageRange"));
		colClub.setCellValueFactory(new PropertyValueFactory<Player,Club>("club"));
		
		TableColumn colName = new TableColumn("Name");
		TableColumn colCity = new TableColumn("City");
		colClub.getColumns().addAll(colName, colCity);

		colCity.setCellValueFactory(new PropertyValueFactory<Player,Club>("club"));
		colCity.setCellFactory(new Callback<TableColumn<Player, Club>, TableCell<Player, Club>>(){

	        @Override
	        public TableCell<Player, Club> call(TableColumn<Player, Club> param) {

	            TableCell<Player, Club> cityCell = new TableCell<Player, Club>(){

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
		colName.setCellValueFactory(new PropertyValueFactory<Player,Club>("club"));		
		colName.setCellFactory(new Callback<TableColumn<Player, Club>, TableCell<Player, Club>>(){

	        @Override
	        public TableCell<Player, Club> call(TableColumn<Player, Club> param) {

	            TableCell<Player, Club> cityCell = new TableCell<Player, Club>(){

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
		List<Player> players = new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class);
		if (players != null){
			ObservableList<Player> data = FXCollections.observableArrayList(players);
			tablePlayer.setItems(data);
		}
	}
	
	@FXML
	void actionClickSave(ActionEvent event) {
		System.out.println("je suis la ");
	}

	@FXML
	void actionClickClose(ActionEvent event) {

	}
}