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

public class ControleurStocks implements Initializable{
	
	@FXML
	private Button retour;
		
	@FXML
	private TableView <Element> tabStocks;
	
	@FXML
	private TableColumn<Element, String> codeTC;
	
	@FXML
	private TableColumn<Element, String> nomTC;
	
	@FXML
	private TableColumn<Element, Number> qteTC;
	
	@FXML
	private TableColumn<Element, String> uniteTC;
	
	@FXML
	private TableColumn<Element, String> achatTC;
	
	@FXML
	private TableColumn<Element, String> venteTC;
	
	@FXML
	private TextField codeTF;
	
	@FXML
	private TextField nomTF;
	
	@FXML
	private TextField uniteTF;
	
	@FXML
	private TextField qteTF;
	
	@FXML
	private TextField achatTF;
	
	@FXML
	private TextField venteTF;
	
	@FXML
	private Button ajouterElem;
	
	@FXML
	private Button modifierElem;
	
	@FXML
	private Button annulerModifElem;
	
	@FXML
	private Button supprimerElem;
	
	private ElementDAO dao = new ElementDAO();
	private ObservableList<Element> elements;
	
	private Element oldElement;
	
	private BooleanBinding bb;
		
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
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
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
	
	@FXML 
	private void clicBoutonAnnulerModificationElem(ActionEvent event) throws IOException {
		clearTextField();
		setDisableButtons();
	}
	
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
	
	private void clearTextField() {
    	codeTF.clear();
    	nomTF.clear();
    	uniteTF.clear();
    	qteTF.clear();
    	achatTF.clear();
    	venteTF.clear();
	}
	
	private void setDisableButtons() {
		this.modifierElem.setDisable(true);
		this.annulerModifElem.setDisable(true);
		this.supprimerElem.setDisable(true);
	}
}
