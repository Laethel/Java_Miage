package stocks;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ElementDAO;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modele.Element;
import utils.Path;
import utils.Path.Way;

/**
 * @author Quentin Beaussart et Damian Riquart
 * 
 */
public class ControleurStocks implements Initializable{
	
	/**
	 * Le bouton "Retour" permettant de revenir à l'acceuil de l'application
	 */
	@FXML
	private Button retour;
		
	/**
	 * Le tableau contenant les différentes colonnes d'information des éléments
	 */
	@FXML
	private TableView <Element> tabStocks;
	
	/**
	 * La colonne indiquant le code de l'élément
	 */
	@FXML
	private TableColumn<Element, String> codeTC;
	
	/**
	 * La colonne indiquant le nom de l'élément
	 */
	@FXML
	private TableColumn<Element, String> nomTC;
	
	/**
	 * La colonne indiquant la quantité de l'élément
	 */
	@FXML
	private TableColumn<Element, Number> qteTC;
	
	/**
	 * La colonne indiquant l'unité de l'élément
	 */
	@FXML
	private TableColumn<Element, String> uniteTC;
	
	/**
	 * La colonne indiquant le prix d'achat de l'élément
	 */
	@FXML
	private TableColumn<Element, String> achatTC;
	
	/**
	 * La colonne indiquant le prix de vente de l'élément
	 */
	@FXML
	private TableColumn<Element, String> venteTC;
	
	/**
	 * Le champ de texte indiquant le code de l'élément
	 */
	@FXML
	private TextField codeTF;
	
	/**
	 * Le champ de texte indiquant le nom de l'élément
	 */
	@FXML
	private TextField nomTF;
	
	/**
	 * Le champ de texte indiquant l'unité de l'élément
	 */
	@FXML
	private TextField uniteTF;
	
	/**
	 * Le champ de texte indiquant la quantité de l'élément
	 */
	@FXML
	private TextField qteTF;
	
	/**
	 * Le champ de texte indiquant le prix d'achat de l'élément
	 */
	@FXML
	private TextField achatTF;
	
	/**
	 * Le champ de texte indiquant le prix de vente de l'élément
	 */
	@FXML
	private TextField venteTF;
	
	/**
	 * Le bouton permettant d'ajouter un nouvel élément
	 */
	@FXML
	private Button ajouterElem;
	
	/**
	 * Le bouton permettant de modifier un élément
	 */
	@FXML
	private Button modifierElem;
	
	/**
	 * Le bouton permettant d'annuler la modification d'un élément
	 */
	@FXML
	private Button annulerModifElem;
	
	/**
	 * Le bouton permettant de supprimer un élément
	 */
	@FXML
	private Button supprimerElem;
	
	/**
	 * Le pattern DAO pour les éléments
	 */
	private ElementDAO dao = new ElementDAO();
	/**
	 * La liste des éléments
	 */
	private ObservableList<Element> elements;
	
	/**
	 * 
	 */
	private Element oldElement;
	
	/**
	 * 
	 */
	private BooleanBinding bb;
		
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	public void initialize(URL url, ResourceBundle rb) {
		this.bb = codeTF.textProperty().isEmpty().or(nomTF.textProperty().isEmpty())
				.or(qteTF.textProperty().isEmpty()).or(uniteTF.textProperty().isEmpty()).or(achatTF.textProperty().isEmpty())
				.or(venteTF.textProperty().isEmpty()).or(modifierElem.disableProperty().not());
		
		this.elements = FXCollections.observableArrayList(dao.findAll());		
		codeTC.setCellValueFactory(new PropertyValueFactory<Element, String>("Code"));
		nomTC.setCellValueFactory(new PropertyValueFactory<Element, String>("Nom"));
		qteTC.setCellValueFactory(new PropertyValueFactory<Element, Number>("Qte"));
		uniteTC.setCellValueFactory(new PropertyValueFactory<Element, String>("Unite"));
		achatTC.setCellValueFactory(new PropertyValueFactory<Element, String>("PrixAchat"));
		venteTC.setCellValueFactory(new PropertyValueFactory<Element, String>("PrixVente"));		
		tabStocks.setItems(elements);
			
		this.ajouterElem.disableProperty().bind(bb);
		this.modifierElem.setDisable(true);
		this.annulerModifElem.setDisable(true);
		this.supprimerElem.setDisable(true);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Retour", ramenant l'utilisateur vers la page d'Acceuil
	 */
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	/**
	 * @param click
	 */
	@FXML
	private void handleClickTableView(MouseEvent click) {
		
		oldElement = tabStocks.getSelectionModel().getSelectedItem();
	    if (oldElement != null) {
	    	codeTF.setText(oldElement.getCode());
	        nomTF.setText(oldElement.getNom());
	        uniteTF.setText(oldElement.getUnite());
	        qteTF.setText(Double.toString(oldElement.getQte()));
	        achatTF.setText(oldElement.getPrixAchat());
	        venteTF.setText(oldElement.getPrixVente());
	        	
	        this.modifierElem.setDisable(false);
	    	this.annulerModifElem.setDisable(false);
	    	this.supprimerElem.setDisable(false);
	     }
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Ajouter un élément", qui permet d'ajouter un élément
	 */
	@FXML 
	private void clicBoutonAjoutElem(ActionEvent event) throws IOException {
		Element elem = new Element(codeTF.getText(), nomTF.getText(), Double.parseDouble(qteTF.getText()), 
				uniteTF.getText(), achatTF.getText(), venteTF.getText());
		if(dao.create(elem)) {
			elements.add(elem);
			clearTextField();
		} else {
			// Message d'erreur
		};
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Modifier un élément", qui permet de modifier un élément existant
	 */
	@FXML 
	private void clicBoutonModifierElem(ActionEvent event) throws IOException {
		Element elem = new Element(codeTF.getText(), nomTF.getText(), Double.parseDouble(qteTF.getText()), 
				uniteTF.getText(), achatTF.getText(), venteTF.getText());
		if(dao.update(oldElement, elem)) {
			elements.set(elements.indexOf(oldElement), elem);
			clearTextField();
			setDisableButtons();
		} else {
			// Message d'erreur
		};
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Annuler la modification", qui permet d'annuler la modification d'un élément
	 */
	@FXML 
	private void clicBoutonAnnulerModificationElem(ActionEvent event) throws IOException {
		clearTextField();
		setDisableButtons();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Supprimer un élément", qui permet de supprimer un élément
	 */
	@FXML 
	private void clicBoutonSupprimerElem(ActionEvent event) throws IOException {
		Element elem = new Element(codeTF.getText(), nomTF.getText(), Double.parseDouble(qteTF.getText()), 
				uniteTF.getText(), achatTF.getText(), venteTF.getText());
		if(dao.delete(elem)) {
			elements.remove(elem);
			clearTextField();
			setDisableButtons();
		} else {
			// Message d'erreur
		};
	}
	
	/**
	 * Supprime le contenu des champs de texte de l'élément
	 */
	private void clearTextField() {
    	codeTF.clear();
    	nomTF.clear();
    	uniteTF.clear();
    	qteTF.clear();
    	achatTF.clear();
    	venteTF.clear();
	}
	
	/**
	 * Rend les boutons Supprimer, Modifier et Annuler non cliquables
	 */
	private void setDisableButtons() {
		this.modifierElem.setDisable(true);
		this.annulerModifElem.setDisable(true);
		this.supprimerElem.setDisable(true);
	}
}
