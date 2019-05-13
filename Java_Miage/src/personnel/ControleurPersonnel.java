package personnel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonnelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Personnel;
import utils.Path;
import utils.Path.Way;

public class ControleurPersonnel implements Initializable{
	
	/**
	 * Le bouton "Retour" permettant de revenir à l'acceuil de l'application
	 */
	@FXML
	private Button retour;
		
	/**
	 * Le tableau contenant les diff�rentes colonnes d'information des membres du personnel
	 */
	@FXML
	private TableView <Personnel> tabPersonnel;
	
	/**
	 * La colonne indiquant le prénom d'un membre du personnel
	 */
	@FXML
	private TableColumn<Personnel, String> prenomTC;
	
	/**
	 * La colonne indiquant le nom d'un membre du personnel
	 */
	@FXML
	private TableColumn<Personnel, String> nomTC;
	
	/**
	 * La colonne indiquant si un membre du personnel est qualifié
	 */
	@FXML
	private TableColumn<Personnel, String> qualifTC;
	
	/**
	 * La colonne indiquant le nombre d'heures travaillées par semaine par un membre du personnel
	 */
	@FXML
	private TableColumn<Personnel, Number> heuresSemaineTC;
	
	/**
	 * Le pattern DAO pour les membres du personnel
	 */
	private PersonnelDAO dao = new PersonnelDAO();
	/**
	 * La liste des membres du personnel
	 */
	private ObservableList<Personnel> personnel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.personnel = FXCollections.observableArrayList(dao.findAll());	
		
		prenomTC.setCellValueFactory(new PropertyValueFactory<Personnel, String>("Prenom"));
		nomTC.setCellValueFactory(new PropertyValueFactory<Personnel, String>("Nom"));
		qualifTC.setCellValueFactory(new PropertyValueFactory<Personnel, String>("Qualif"));
		heuresSemaineTC.setCellValueFactory(new PropertyValueFactory<Personnel, Number>("HeuresSemaine"));
		
		tabPersonnel.setItems(personnel);
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 * Methode declenchee lors du clic sur le bouton "Retour", ramenant l'utilisateur vers la page d'Acceuil
	 */
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}

}
