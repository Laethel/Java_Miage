package chaines;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ChaineDAO;
import dao.ElementDAO;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	private TextField codeTF;
	
	@FXML
	private TextField nomTF;
	
	@FXML
	private TextField elemEntreeQteTF;
	
	@FXML
	private TextField elemEntreeTF;
	
	@FXML
	private TextField elemSortieQteTF;
	
	@FXML
	private TextField elemSortieTF;
	
	@FXML
	private TextField nivActiviteTF;
	
	@FXML
	private TextField resultatTF;
	
	@FXML
	private ChoiceBox<Element> entreeCB;
	
	@FXML
	private ChoiceBox<Element> sortieCB;
	
	@FXML
	private Button ajouterElemEntree;
	
	@FXML
	private Button ajouterElemSortie;
	
	@FXML
	private Button reinitElemEntree;
	
	@FXML
	private Button reinitElemSortie;
	
	@FXML
	private Button ajouterChaine;
	
	@FXML
	private Button modifierChaine;
	
	@FXML
	private Button annulerModifChaine;
	
	@FXML
	private Button supprimerChaine;
	
	@FXML
	private Button testerChaine;
			
	private ChaineDAO daoC = new ChaineDAO();
	private ElementDAO daoE = new ElementDAO();
	
	private ObservableList<Chaine> chaines;
	private Chaine oldChaine;
	
	private BooleanBinding bbForm;
	private BooleanBinding bbElemEntree;
	private BooleanBinding bbElemSortie;
		
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
		
		this.bbForm = codeTF.textProperty().isEmpty().or(nomTF.textProperty().isEmpty().or(elemEntreeTF.textProperty().isEmpty())
				.or(elemSortieTF.textProperty().isEmpty().or(nivActiviteTF.textProperty().isEmpty())));		
		this.bbElemEntree = entreeCB.getSelectionModel().selectedItemProperty().isNull().or(elemEntreeQteTF.textProperty().isEmpty());
		this.bbElemSortie = sortieCB.getSelectionModel().selectedItemProperty().isNull().or(elemSortieQteTF.textProperty().isEmpty());
		
		this.ajouterChaine.disableProperty().bind(bbForm);
		this.ajouterElemEntree.disableProperty().bind(bbElemEntree);
		this.ajouterElemSortie.disableProperty().bind(bbElemSortie);
		
		this.modifierChaine.setDisable(true);
		this.annulerModifChaine.setDisable(true);
		this.supprimerChaine.setDisable(true);
		this.testerChaine.setDisable(true);
		
	}
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	@FXML
	private void handleClickTableView(MouseEvent click) {
		
		oldChaine = tabChaines.getSelectionModel().getSelectedItem();
	    if (oldChaine != null) {
	    	codeTF.setText(oldChaine.getCode());
	        nomTF.setText(oldChaine.getNom());
	        elemEntreeTF.setText(oldChaine.getSEntree());
	        elemSortieTF.setText(oldChaine.getSSortie());
	        nivActiviteTF.setText(Integer.toString(oldChaine.getNivAct()));
	        resultatTF.setText(oldChaine.getResultat());              
	     }
	}
	
	@FXML 
	private void clicBoutonReinitElemEntree(ActionEvent event) throws IOException {
		elemEntreeTF.clear();
	}
	
	@FXML 
	private void clicBoutonReinitElemSortie(ActionEvent event) throws IOException {
		elemSortieTF.clear();
	}
	
	@FXML 
	private void clicBoutonAjoutChaine(ActionEvent event) throws IOException {
		Chaine ch = new Chaine(codeTF.getText(), nomTF.getText(), elemEntreeTF.getText(), 
				elemSortieTF.getText(), Integer.parseInt(nivActiviteTF.getText()), resultatTF.getText());
		if(daoC.create(ch)) {
			chaines.add(ch);
			clearTextField();
		} else {
			// Message d'erreur
		};
	}
	
	@FXML 
	private void clicBoutonModifierChaine(ActionEvent event) throws IOException {
	}
	
	@FXML 
	private void clicBoutonAnnulerModificationChaine(ActionEvent event) throws IOException {
	}
	
	@FXML 
	private void clicBoutonSupprimerChaine(ActionEvent event) throws IOException {
	}
	
	@FXML 
	private void clicBoutonTesterChaine(ActionEvent event) throws IOException {
	}
	
	private void clearTextField() {
    	codeTF.clear();
    	nomTF.clear();
    	entreeCB.getSelectionModel().select(0);
    	elemEntreeQteTF.clear();
    	elemEntreeTF.clear();
    	sortieCB.getSelectionModel().select(0);
    	elemSortieQteTF.clear();
    	elemSortieTF.clear();
    	nivActiviteTF.clear();
    	resultatTF.clear();
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
