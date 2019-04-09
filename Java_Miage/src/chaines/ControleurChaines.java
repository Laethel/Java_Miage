package chaines;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import dao.ChaineDAO;
import dao.ElementDAO;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	    
	//private static File listeAchats = new File("./src/utils/listeAchats.csv");
	private static File crProd = new File("./src/utils/crProd.csv");

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
	private TableColumn<Chaine, String> nivActTC;
	
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
	
	@FXML
	private Button resetTest;
			
	private ChaineDAO daoC = new ChaineDAO();
	private ElementDAO daoE = new ElementDAO();
	
	private ObservableList<Chaine> chaines;
	private Chaine oldChaine;
	
	ArrayList<Element> elements;
	
	private BooleanBinding bbForm;
	private BooleanBinding bbElemEntree;
	private BooleanBinding bbElemSortie;

	ArrayList<Element> elementsSave = new ArrayList<Element>();
	
		
	public void initialize(URL url, ResourceBundle rb) {	
		this.chaines = FXCollections.observableArrayList(daoC.findAll());
		
		codeTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Code"));
		nomTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Nom"));
		entreeTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("sEntree"));
		sortieTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("sSortie"));
		nivActTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("nivAct"));
		tabChaines.setItems(chaines);
		
		elements = daoE.findAll();
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
		
		elementsSave.clear();
		for (Element e : elements) {
			elementsSave.add(e);
		}
		
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
				elemSortieTF.getText(), Integer.parseInt(nivActiviteTF.getText()));
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
				elemSortieTF.getText(), Integer.parseInt(nivActiviteTF.getText()));
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
	private void clicBoutonResetTest(ActionEvent event) throws IOException {
		//R�initialise les stocks � leur �tat pr�-test 
		ArrayList<Element> toRemove = new ArrayList<Element>();
		ArrayList<Element> toAdd = new ArrayList<Element>();

		for (Element e : elements) {
			daoE.delete(e);
			toRemove.add(e);
		}
		for (Element e : elementsSave) {
			daoE.create(e);
			toAdd.add(e);
		}
		elements.removeAll(toRemove);
		elements.addAll(toAdd);
		
		setDisableButtons(true);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Test de production r�initialis� !"					
				, ButtonType.OK);
		alert.showAndWait();
	}
	
	@FXML 
	private void clicBoutonSupprimerChaine(ActionEvent event) throws IOException {
		Chaine ch = new Chaine(codeTF.getText(), nomTF.getText(), elemEntreeTF.getText(), 
				elemSortieTF.getText(), Integer.parseInt(nivActiviteTF.getText()));
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
		loadElemEntreeSortie();
		
		ArrayList<Element> stocktmp = daoE.findAll();
		ArrayList<Element> listeAchat = new ArrayList<Element>();
		
		int resultat = 0;
		boolean productionPossible = true;
		Element elemManquant = null;
		
		
		for (Chaine ch : chaines) {
			for (Element el : ch.getlEntree()) {
				double qteASoustraire = el.getQte() * ch.getNivAct();
				stocktmp.get(stocktmp.indexOf(el)).soustraireStock(qteASoustraire);
			}
			
			for (Element el : ch.getlSortie()) {
				double qteAAjouter = el.getQte() * ch.getNivAct();
				stocktmp.get(stocktmp.indexOf(el)).ajouterStock(qteAAjouter);
			}
		}

		
		for (int i = 0; i < stocktmp.size(); i++) {
			System.out.println(stocktmp.get(i));
			if (stocktmp.get(i).getQte() < 0 ) {
				if (stocktmp.get(i).getPrixAchat().equals("NA")) {
					productionPossible = false;
					elemManquant = stocktmp.get(i);
					break;
				} else {
					listeAchat.add(stocktmp.get(i));

					resultat -= Integer.parseInt(stocktmp.get(i).getPrixAchat()) * stocktmp.get(i).getQte();
					System.out.println(Integer.parseInt(stocktmp.get(i).getPrixAchat()) * stocktmp.get(i).getQte());
				}
			} else {
				if (!stocktmp.get(i).getPrixVente().equals("NA")) {
					resultat += Integer.parseInt(stocktmp.get(i).getPrixVente());
				}
			}
		}
		
		if (productionPossible) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Essai de production termin�. R�sultat obtenu : " + resultat + ".\n \n"
					+ "Voulez-vous exporter le compte-rendu de la production dans un fichier externe (.csv) ? \n \n"
					+ "(Les stocks seront automatiquement mis � jour.)"					
					, ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				// Mise � jour du stock
				for (int i = 0; i < elements.size(); i++) {
					daoE.update(elements.get(i), stocktmp.get(i));
					elements.set(elements.indexOf(elements.get(i)), stocktmp.get(i));
				}
				
				// G�n�ration du compte-rendu de production
				BufferedWriter writerCr = new BufferedWriter(new FileWriter(crProd));
				CSVPrinter csvPrinterCr = new CSVPrinter(writerCr, CSVFormat.DEFAULT);
				writerCr.write("Compte-rendu de la production : \n");
				writerCr.write("Chaines actives; Elements consomm�s; Elements produits \n");
				for (Chaine ch : chaines) {
					writerCr.write(ch.getNom() +"(" + ch.getCode()+") - Niveau " + ch.getNivAct() + ";");
					for (Element el : ch.getlEntree()) {
						double qteConsommee = el.getQte() * ch.getNivAct();
						writerCr.write(el.getNom() +"(" + qteConsommee+"), ");
					}
					writerCr.write(";");
					for (Element el : ch.getlSortie()) {
						double qteProduite = el.getQte() * ch.getNivAct();
						writerCr.write(el.getNom() +"(" + qteProduite+"), ");
					}
					writerCr.write("; \n");
				}
				writerCr.write("\n Total consomm�; Total produit \n");
				//TODO
				//Grouper les �l�ments produits ici ?
				writerCr.write("\n R�sultat \n");
				writerCr.write(Integer.toString(resultat));
				writerCr.write("\n \n \n");
				writerCr.write("Liste d'achats \n");
				writerCr.write("Code; Nom ; Quantit� � acheter \n");
				for(Element i : listeAchat) {
					System.out.println(i);
					writerCr.write(i.getCode() +";" + i.getNom() + ";" + Math.abs(i.getQte()) +" " + i.getUnite() +"\n");

				}
				
				csvPrinterCr.close();
				writerCr.close();
				Alert alertCrProd = new Alert(AlertType.CONFIRMATION, "Compte-rendu de production g�n�r�. (" + crProd.getPath() + ") \n \n"
						+ "Voulez vous y acc�der d�s maintenant ?"					
						, ButtonType.YES, ButtonType.NO);
				alertCrProd.showAndWait();
				
				//Ouvre le CR
				if (alertCrProd.getResult() == ButtonType.YES) {
					java.awt.Desktop.getDesktop().open(crProd);
				}
				
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Impossible de finaliser l'essai de production. \n"
					+ "Le prix d'achat de certains �l�ments n'est pas renseign� : "	+ elemManquant.getNom()				
					, ButtonType.OK);
			alert.showAndWait();
		}
		
	}
	
	private void loadElemEntreeSortie() {
		for (Chaine ch : chaines) {
			
			ch.clearLEntree();
			ch.clearLSortie();
			
			String[] sElemsEntree = ch.getSEntree().split("\\),\\(");
			
			for (String s : sElemsEntree) {
				s = s.replace("(", "");
				s = s.replace(")", "");
								
				String[] sElem = s.split(",");
				
				Element elem = daoE.find(sElem[0]);
				elem.setQte(Double.parseDouble(sElem[1]));
				ch.addlEntree(elem);
			}
			
			String[] sElemsSortie = ch.getSSortie().split("\\),\\(");
			
			for (String s : sElemsSortie) {
				s = s.replace("(", "");
				s = s.replace(")", "");
								
				String[] sElem = s.split(",");
				
				Element elem = daoE.find(sElem[0]);
				elem.setQte(Double.parseDouble(sElem[1]));
				ch.addlSortie(elem);
			}			
		}	
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
	}
	
	private void setDisableButtons(boolean pDisable) {
		this.modifierChaine.setDisable(pDisable);
		this.annulerModifChaine.setDisable(pDisable);
		this.supprimerChaine.setDisable(pDisable);
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
