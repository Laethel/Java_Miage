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

public class ControleurChaines implements Initializable{
	
    private static final String CSV_FILE_PATH_CHAINE = ControleurParams.pathCh;
    
	@FXML
	private Button retour;
	
	@FXML
	private Button testProd;
	
	@FXML
	TableView<Chaine> tabChaines;
	
	@FXML
	private TableColumn<Chaine, String> code;
	
	@FXML
	private TableColumn<Chaine, String> nom;
	
	@FXML
	private TableColumn<Chaine, String> entree;
	
	@FXML
	private TableColumn<Chaine, String> sortie;
	
	@FXML
	private TableColumn<Chaine, String> nivAct;
	
	@FXML
	private TableColumn<Chaine, String> resultat;
	
	ObservableList<Chaine> chaines = FXCollections.observableArrayList();
	
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
		            //Si le niveau d'activité n'est pas précisé, le définit à "1" par défaut
		            if (csvRecord.isSet("NivActivite")) {
		            	nivAct = csvRecord.get(4);
		            } else {
		            	nivAct = "1";
		            }

		            Chaine chaine = new Chaine(code, nom, entree, sortie, nivAct);
		            chaines.add(chaine);
		        }
				
		        //Ajoute les données à la table
				tabChaines.setItems(chaines);
				csvParser.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	@FXML 
	private void clicBoutonTestProd(ActionEvent event) throws IOException {
		Path.goTo(event, Way.TEST_PROD);
	}
}
