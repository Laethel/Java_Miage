package stocks;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ElementDAO;
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
import utils.Path;
import utils.Path.Way;

public class ControleurStocks implements Initializable{
	
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
	private TableColumn<Element, Number> qte;
	
	@FXML
	private TableColumn<Element, String> unite;
	
	@FXML
	private TableColumn<Element, String> achat;
	
	@FXML
	private TableColumn<Element, String> vente;
	
	private ElementDAO dao = new ElementDAO();
	private ObservableList<Element> elements;
	
	public void initialize(URL url, ResourceBundle rb) {
		this.elements = FXCollections.observableArrayList(dao.findAll());
		
		code.setCellValueFactory(new PropertyValueFactory<Element, String>("Code"));
		nom.setCellValueFactory(new PropertyValueFactory<Element, String>("Nom"));
		qte.setCellValueFactory(new PropertyValueFactory<Element, Number>("Qte"));
		unite.setCellValueFactory(new PropertyValueFactory<Element, String>("Unite"));
		achat.setCellValueFactory(new PropertyValueFactory<Element, String>("PrixAchat"));
		vente.setCellValueFactory(new PropertyValueFactory<Element, String>("PrixVente"));
		
		tabStocks.setItems(elements);
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
