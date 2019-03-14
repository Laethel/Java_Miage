package chaines;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ChaineDAO;
import dao.ElementDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import modele.Chaine;
import modele.Element;
import utils.Path;
import utils.Path.Way;

public class ControleurChaines implements Initializable{
	    
	@FXML
	private Button retour;
	
	@FXML
	private Button testProd;
	
	@FXML
	TableView<Chaine> tabChaines;
	
	@FXML
	private TableColumn<Chaine, String> codeTC;
	
	@FXML
	private TableColumn<Chaine, String> nomTC;
	
	@FXML
	private TableColumn<Chaine, String> entreeTC;
	
	@FXML
	private TableColumn<Chaine, String> sortieTC;
	
	@FXML
	private ChoiceBox<Element> entreeCB;
	
	@FXML
	private ChoiceBox<Element> sortieCB;
		
	private ChaineDAO daoC = new ChaineDAO();
	private ElementDAO daoE = new ElementDAO();
	
	private ObservableList<Chaine> chaines;
		
	public void initialize(URL url, ResourceBundle rb) {
		this.chaines = FXCollections.observableArrayList(daoC.findAll());
		
		codeTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Code"));
		nomTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Nom"));
		entreeTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("sEntree"));
		sortieTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("sSortie"));
		tabChaines.setItems(chaines);
		
		ArrayList<Element> elements = daoE.findAll();
		entreeCB.setItems(FXCollections.observableArrayList(elements));
		sortieCB.setItems(FXCollections.observableArrayList(elements));		
		formatCB();
		
	}
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	@FXML 
	private void clicBoutonTestProd(ActionEvent event) throws IOException {
		Path.goTo(event, Way.TEST_PROD);
	}
	
	private void formatCB() {
		entreeCB.setConverter(new StringConverter<Element>() {
		    @Override
		    public String toString(Element elem) {
		        return elem.getCode() + " - " + elem.getNom();
		    }
		    
		    @Override
		    // Pas utile
		    public Element fromString(String s) {
		        return null ;
		    }
		});
		
		sortieCB.setConverter(new StringConverter<Element>() {
		    @Override
		    public String toString(Element elem) {
		        return elem.getCode() + " - " + elem.getNom();
		    }
		    
		    @Override
		    // Pas utile
		    public Element fromString(String s) {
		        return null ;
		    }
		});
	}
}
