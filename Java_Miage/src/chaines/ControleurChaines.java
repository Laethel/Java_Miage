package chaines;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Chaine;
import params.ControleurParams;
import utils.Path;
import utils.Path.Way;

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 */
public class ControleurChaines implements Initializable{
	
    /**
     * Constante contenant le chemin vers le fichier CSV des chaines de production
     */
    private static final String CSV_FILE_PATH_CHAINE = ControleurParams.pathCh;
    
	/**
	 * Le bouton "Retour"
	 */
	@FXML
	private Button retour;
	
	/**
	 * Le bouton permettant d'ajouter une chaine de production ou d'en modifier les param�tres
	 */
	@FXML
	private Button testProd;
	
	/**
	 * Le tableau contenant les diff�rentes colonnes d'information des chaines
	 */
	@FXML
	TableView<Chaine> tabChaines;
	
	/**
	 * La colonne indiquant le code de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> code;
	
	/**
	 * La colonne indiquant le nom de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> nom;
	
	/**
	 * La colonne indiquant les �l�ments en entr�e de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> entree;
	
	/**
	 * La colonne indiquant les �l�ments en sortie de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> sortie;
	
	/**
	 * La colonne indiquant le niveau d'activit� de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> nivAct;
	
	/**
	 * La colonne indiquant le resultat de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> resultat;
	
	/**
	 * Liste des chaines de production
	 */
	ObservableList<Chaine> chaines = FXCollections.observableArrayList();
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println(CSV_FILE_PATH_CHAINE);
		
		if(CSV_FILE_PATH_CHAINE != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_CHAINE));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';').withNullString("").withIgnoreSurroundingSpaces());
		        
				code.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Code"));
				nom.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Nom"));
				entree.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Entree"));
				sortie.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Sortie"));
				nivAct.setCellValueFactory(new PropertyValueFactory<Chaine, String>("NivAct"));
				resultat.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Resultat"));
				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            String entree = csvRecord.get(2);
		            String sortie = csvRecord.get(3);
		            String nivAct;
		            //Si le niveau d'activit� n'est pas pr�cis�, le d�finit � "1" par d�faut
		            if (csvRecord.isSet("NivActivite")) {
		            	nivAct = csvRecord.get(4);
		            } else {
		            	nivAct = "1";
		            }

		            Chaine chaine = new Chaine(code, nom, entree, sortie, nivAct);
		            chaines.add(chaine);
		        }
				
		        //Ajoute les donn�es � la table
				tabChaines.setItems(chaines);
				csvParser.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * M�thode d�clench�e lors du clic sur le bouton "Retour", ramenant l'utilisateur vers la page d'Acceuil
	 */
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	

	/**
	 * @param event
	 * @throws IOException
	 * M�thode d�clench�e lors du clic sur le bouton "Tester une production", amenant l'utilisateur vers l'�cran d'ajout de chaine
	 */
	@FXML 
	private void clicBoutonTestProd(ActionEvent event) throws IOException {
		Path.goTo(event, Way.TEST_PROD);
	}
}
