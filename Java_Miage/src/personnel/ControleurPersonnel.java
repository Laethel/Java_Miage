package personnel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonnelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Personnel;
import params.ControleurParams;
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
	 * Le bouton permettant d'afficher les heures de main d'oeuvre disponibles
	 */
	@FXML
	private Button mainOeuvreDispo;
	
	/**
	 * Le bouton permettant d'afficher les fiches de temps du personnel
	 */
	@FXML
	private Button ficheTemps;
	
	/**
	 * Le pattern DAO pour les membres du personnel
	 */
	private PersonnelDAO dao = new PersonnelDAO();
	/**
	 * La liste des membres du personnel
	 */
	private ObservableList<Personnel> personnel;
	
	double heuresQualif = 0;
	double heuresNonQualif = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.personnel = FXCollections.observableArrayList(dao.findAll());
		for(Personnel p : personnel) {
			if(p.getQualif().equals("Oui")) {
				this.heuresQualif += p.getHeuresSemaine();
			}else {
				this.heuresNonQualif += p.getHeuresSemaine();
			}
		}
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
	
	/**
	 * Methode declenchee lors du clic sur le bouton "Main d'oeuvre disponible", qui affiche des informations relatives au personnel disponible
	 */
	@FXML
	private void clicBoutonMainOeuvre() {
		Alert alert = new Alert(AlertType.INFORMATION,"Heures de main d'oeuvre disponibles par semaine : \n \n"
				+ "Heures totales (Personnel qualifié) : "+heuresQualif +" heures. \n"
				+ "Heures totales (Personnel non qualifié) : "+heuresNonQualif+" heures."			
				, ButtonType.OK);
		alert.showAndWait();
	}


	/**
	 * Methode retournant le nombres d'heures qualifiees disponibles
	 * @return
	 */
	public double getHeuresQualif() {
		return heuresQualif;
	}


	/**
	 * Methode retournant le nombres d'heures qualifiees disponibles
	 * @return
	 */
	public double getHeuresNonQualif() {
		return heuresNonQualif;
	}
	
	/**
	 * Methode declenchee lors du clic sur le bouton "Fiches de temps", qui ouvre les fiches de temps du personnel
	 * @throws IOException
	 */
	@FXML
	private void clicBoutonFicheTemps() throws IOException {
        BufferedReader brPers = new BufferedReader(new FileReader(ControleurParams.getLoadCsvPers()));
        String prevPers = brPers.readLine();
        File ficheTemps = new File(prevPers);
		Alert alert = new Alert(AlertType.INFORMATION,"Fiches de temps. \n \n"	
				+"WORK IN PROGRESS"
				, ButtonType.OK);
		alert.showAndWait();
		java.awt.Desktop.getDesktop().open(ficheTemps);	
		brPers.close();
		
	}
	
}
