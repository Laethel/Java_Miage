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

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 */
public class ControleurChaines implements Initializable{
	    
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
	private TableColumn<Chaine, String> codeTC;
	
	/**
	 * La colonne indiquant le nom de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> nomTC;
	
	/**
	 * La colonne indiquant les �l�ments en entr�e de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> entreeTC;
	
	/**
	 * La colonne indiquant les �l�ments en sortie de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> sortieTC;
	
	/**
	 * La colonne indiquant le niveau d'activit� de la chaine
	 */
	@FXML
	private TextField codeTF;
	
	/**
	 * La colonne indiquant le resultat de la chaine
	 */
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
		
		setDisableButtons(true);		
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
	    
	    setDisableButtons(false);
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
	private void clicBoutonAjouterElemEntree(ActionEvent event) throws IOException {
		if (elemEntreeTF.getText().isEmpty()) {
			elemEntreeTF.setText("(" + entreeCB.getValue().getCode() + "," + elemEntreeQteTF.getText() + ")");	
		} else {
			elemEntreeTF.setText(elemEntreeTF.getText() + ",(" + entreeCB.getValue().getCode() + "," + elemEntreeQteTF.getText() + ")");	
		}
	}
	
	@FXML 
	private void clicBoutonAjouterElemSortie(ActionEvent event) throws IOException {
		if (elemSortieTF.getText().isEmpty()) {
			elemSortieTF.setText("(" + sortieCB.getValue().getCode() + "," + elemSortieQteTF.getText() + ")");	
		} else {
			elemSortieTF.setText(elemSortieTF.getText() + ",(" + sortieCB.getValue().getCode() + "," + elemSortieQteTF.getText() + ")");	
		}
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
		Chaine ch = new Chaine(codeTF.getText(), nomTF.getText(), elemEntreeTF.getText(), 
				elemSortieTF.getText(), Integer.parseInt(nivActiviteTF.getText()), resultatTF.getText());
		if(daoC.update(oldChaine,ch)) {
			chaines.set(chaines.indexOf(oldChaine), ch);
			clearTextField();
			setDisableButtons(true);
		} else {
			// Message d'erreur
		};
	}
	
	@FXML 
	private void clicBoutonAnnulerModificationChaine(ActionEvent event) throws IOException {
		clearTextField();
		setDisableButtons(true);
	}
	
	@FXML 
	private void clicBoutonSupprimerChaine(ActionEvent event) throws IOException {
		Chaine ch = new Chaine(codeTF.getText(), nomTF.getText(), elemEntreeTF.getText(), 
				elemSortieTF.getText(), Integer.parseInt(nivActiviteTF.getText()), resultatTF.getText());
		if(daoC.delete(ch)) {
			chaines.remove(ch);
			clearTextField();
			setDisableButtons(true);
		} else {
			// Message d'erreur
		};
	}
	
	@FXML 
	private void clicBoutonTesterChaine(ActionEvent event) throws IOException {
	}
	
	private void clearTextField() {
    	codeTF.clear();
    	nomTF.clear();
    	entreeCB.getSelectionModel().select(-1);
    	elemEntreeQteTF.clear();
    	elemEntreeTF.clear();
    	sortieCB.getSelectionModel().select(-1);
    	elemSortieQteTF.clear();
    	elemSortieTF.clear();
    	nivActiviteTF.clear();
    	resultatTF.clear();
	}
	
	private void setDisableButtons(boolean pDisable) {
		this.modifierChaine.setDisable(pDisable);
		this.annulerModifChaine.setDisable(pDisable);
		this.supprimerChaine.setDisable(pDisable);
		this.testerChaine.setDisable(pDisable);
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
