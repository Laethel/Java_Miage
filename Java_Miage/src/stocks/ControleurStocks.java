package stocks;

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
import modele.Element;
import params.ControleurParams;
import utils.Path;
import utils.Path.Way;

public class ControleurStocks implements Initializable{
	
    private static final String CSV_FILE_PATH_ELEMENT = ControleurParams.pathElem;

	@FXML
	private Button retour;
	
	@FXML
	private Button ajouterElem;
	
	@FXML
	private TableView <Element> tabStocks;
	
	@FXML
	private TableColumn<Element, String> code;
	
	@FXML
	private TableColumn<Element, String> nom;
	
	@FXML
	private TableColumn<Element, String> qte;
	
	@FXML
	private TableColumn<Element, String> unite;
	
	@FXML
	private TableColumn<Element, String> achat;
	
	@FXML
	private TableColumn<Element, String> vente;
	
	ObservableList<Element> elems = FXCollections.observableArrayList();
	
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println(CSV_FILE_PATH_ELEMENT);
		
		if(CSV_FILE_PATH_ELEMENT != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_ELEMENT));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));
		        
				code.setCellValueFactory(new PropertyValueFactory<Element, String>("Code"));
				nom.setCellValueFactory(new PropertyValueFactory<Element, String>("Nom"));
				qte.setCellValueFactory(new PropertyValueFactory<Element, String>("Qte"));
				unite.setCellValueFactory(new PropertyValueFactory<Element, String>("Unite"));
				achat.setCellValueFactory(new PropertyValueFactory<Element, String>("PrixAchat"));
				vente.setCellValueFactory(new PropertyValueFactory<Element, String>("PrixVente"));
				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            String qte = csvRecord.get(2);
		            String unite = csvRecord.get(3);
		            String prixAchat = csvRecord.get(4);
		            String prixVente = csvRecord.get(5);
		            Element elem = new Element(code, nom, qte, unite, prixAchat, prixVente);
		            System.out.println(elem.toString());
		            elems.add(elem);
		        }
				
		        //Ajoute les données à la table

				tabStocks.setItems(elems);
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
	private void clicBoutonAjoutElem(ActionEvent event) throws IOException {
		Path.goTo(event, Way.AJOUT_ELEM);
	}
}
