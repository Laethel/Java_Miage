package chaines;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import modele.Chaine;
import modele.Element;
import params.ControleurParams;
import utils.Path;
import utils.Path.Way;

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 */
public class ControleurChaines implements Initializable{
	    
	private static File crProd = ControleurParams.getCrProd();
	private final String CHEMIN_SEMAINE = System.getProperty("user.home")+"/Gestion production/chaineSemaine_";

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
	 * La colonne indiquant les éléments en sortie de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> sortieTC;
	
	/**
	 * La colonne indiquant le temps de production de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> tempsProdTC;
	
	/**
	 * La colonne indiquant le nombre d'ouvrier qualifiés requis pour la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> nbQualifTC;
	
	/**
	 * La colonne indiquant le le nombre d'ouvrier non qualifiés requis pour la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> nbNonQualifTC;
	
	/**
	 * La colonne indiquant le niveau d'activit� de la chaine
	 */
	@FXML
	private TableColumn<Chaine, String> nivActTC;
	
	/**
	 * Le champ de texte du code de la chaine
	 */
	@FXML
	private TextField codeTF;
	
	/**
	 * Le champ de texte du nom de la chaine
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
	private TextField tempsProdTF;
	
	@FXML
	private TextField nbQualifTF;
	
	@FXML
	private TextField nbNonQualifTF;
	
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
	private Button testerSemainesAll;
	
	@FXML
	private Button resetTest;
	
	@FXML
	private Button semainePrec;
	
	@FXML
	private Button semaineSuiv;
	
	@FXML
	private Label semaineAct;
			
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
		this.chaines = FXCollections.observableArrayList(daoC.findAll()); // chargement
		
		codeTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Code"));
		nomTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("Nom"));
		entreeTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("sEntree"));
		sortieTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("sSortie"));
		tempsProdTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("tempsProd"));
		nbQualifTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("nbQualif"));
		nbNonQualifTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("nbNonQualif"));
		nivActTC.setCellValueFactory(new PropertyValueFactory<Chaine, String>("nivAct"));
		tabChaines.setItems(chaines);
		
		elements = daoE.findAll();
		entreeCB.setItems(FXCollections.observableArrayList(elements));
		sortieCB.setItems(FXCollections.observableArrayList(elements));		
		formatCB();
		
		this.bbForm = codeTF.textProperty().isEmpty().or(nomTF.textProperty().isEmpty().or(elemEntreeTF.textProperty().isEmpty())
				.or(elemSortieTF.textProperty().isEmpty().or(tempsProdTF.textProperty().isEmpty().or(nbQualifTF.textProperty().isEmpty()
						.or(nbNonQualifTF.textProperty().isEmpty().or(nivActiviteTF.textProperty().isEmpty()))))));		
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
		this.semainePrec.setDisable(true);
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
	
	@FXML
	private void handleClickTableView(MouseEvent click) {
		
		oldChaine = tabChaines.getSelectionModel().getSelectedItem();
	    if (oldChaine != null) {
	    	codeTF.setText(oldChaine.getCode());
	        nomTF.setText(oldChaine.getNom());
	        elemEntreeTF.setText(oldChaine.getSEntree());
	        elemSortieTF.setText(oldChaine.getSSortie());
	        tempsProdTF.setText(Integer.toString(oldChaine.getTempsProd())); 
	        nbQualifTF.setText(Integer.toString(oldChaine.getNbQualif())); 
	        nbNonQualifTF.setText(Integer.toString(oldChaine.getNbNonQualif())); 
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
				elemSortieTF.getText(),Integer.parseInt(tempsProdTF.getText()), Integer.parseInt(nbQualifTF.getText()), 
						Integer.parseInt(nbNonQualifTF.getText()),Integer.parseInt(nivActiviteTF.getText()));
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
				elemSortieTF.getText(),Integer.parseInt(tempsProdTF.getText()), Integer.parseInt(nbQualifTF.getText()), 
				Integer.parseInt(nbNonQualifTF.getText()),Integer.parseInt(nivActiviteTF.getText()));
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
		//Réinitialise les stocks à leur état pré-test 
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
		Alert alert = new Alert(AlertType.CONFIRMATION, "Test de production réinitialisé !"					
				, ButtonType.OK);
		alert.showAndWait();
	}
	
	@FXML 
	private void clicBoutonSupprimerChaine(ActionEvent event) throws IOException {
		Chaine ch = new Chaine(codeTF.getText(), nomTF.getText(), elemEntreeTF.getText(), 
				elemSortieTF.getText(),Integer.parseInt(tempsProdTF.getText()), Integer.parseInt(nbQualifTF.getText()), 
				Integer.parseInt(nbNonQualifTF.getText()),Integer.parseInt(nivActiviteTF.getText()));
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
			if (stocktmp.get(i).getQte() < 0 ) {
				if (stocktmp.get(i).getPrixAchat().equals("NA")) {
					productionPossible = false;
					elemManquant = stocktmp.get(i);
					break;
				} else {
					listeAchat.add(stocktmp.get(i));

					resultat -= Integer.parseInt(stocktmp.get(i).getPrixAchat()) * stocktmp.get(i).getQte();
				}
			} else {
				if (!stocktmp.get(i).getPrixVente().equals("NA")) {
					resultat += Integer.parseInt(stocktmp.get(i).getPrixVente());
				}
			}
		}
		
		if (productionPossible) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Essai de production terminé. Résultat obtenu : " + resultat + ".\n \n"
					+ "Un compte-rendu de la production a été généré dans un fichier externe (" + crProd.getPath() + ") \n \n"
					+ "Voulez vous mettre à jour les stocks ?"					
					, ButtonType.YES, ButtonType.NO);
			
			//méthode de getPath, vérif fichier, si pas là, créer
			// Génération du compte-rendu de production
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(crProd),"UTF-8");
			BufferedWriter writerCr = new BufferedWriter(out);
			CSVPrinter csvPrinterCr = new CSVPrinter(writerCr, CSVFormat.DEFAULT);
			
			
			writerCr.write("COMPTE RENDU DE LA PRODUCTION \n");
			writerCr.write("\nRÉSULTAT :;" + Integer.toString(resultat));
			writerCr.write("\n \n");
			writerCr.write("CHAINES ACTIVES;ÉLÉMENTS CONSOMMÉS;ÉLÉMENTS PRODUITS \n");
			for (Chaine ch : chaines) {
				writerCr.write(ch.getNom() +"(" + ch.getCode()+") - Niveau " + ch.getNivAct() + ";");
				String sElEntree = "";
				for (Element el : ch.getlEntree()) {
					double qteConsommee = el.getQte() * ch.getNivAct();
					sElEntree += el.getNom() +"(" + qteConsommee+"), ";
					stocktmp.get(stocktmp.indexOf(el)).ajouterQteConsommee(qteConsommee);
				}
				sElEntree = sElEntree.substring(0, sElEntree.lastIndexOf(','));
				writerCr.write(sElEntree + ";");
				
				String sElSortie = "";
				for (Element el : ch.getlSortie()) {
					double qteProduite = el.getQte() * ch.getNivAct();
					sElSortie += el.getNom() +"(" + qteProduite+"), ";
					stocktmp.get(stocktmp.indexOf(el)).ajouterQteProduite(qteProduite);
				}
				sElSortie = sElSortie.substring(0, sElSortie.lastIndexOf(','));
				writerCr.write(sElSortie + "; \n");
			}

			//ETAT DU STOCK TEMPORAIRE
			writerCr.write("\nETAT DU STOCK TEMPORAIRE :\n");
			writerCr.write("CODE;NOM;QUANTITÉ;UNITÉ;QUANTITÉ CONSOMMÉE;QUANTITÉ PRODUITE;% DEMANDE\n");
			for(Element el : stocktmp) {
				String statdemande = "NA";
				if (el.getDemande() != 0.0) {
					statdemande = Double.toString(Math.floor((el.getQteProduite()/el.getDemande())*100)) + "%";
				}
				writerCr.write(el.getCode() + ";" + el.getNom() + ";" + el.getQte() + ";" + el.getUnite() + ";" + el.getQteConsommee() + ";" + 
						el.getQteProduite() + ";" + statdemande + "\n");
			}

			writerCr.write("\nLISTE D'ACHATS \n");
			writerCr.write("CODE;NOM;QUANTITÉ À ACHETER;UNITÉ;PRIX UNITAIRE;MONTANT \n");
			double totalAchat = 0.0;
			for(Element i : listeAchat) {
				writerCr.write(i.getCode() +";" + i.getNom() + ";" + Math.abs(i.getQte()) + ";" + i.getUnite() + ";" +  i.getPrixAchat() + ";" + Math.abs(i.getQte()) * Integer.parseInt(i.getPrixAchat()) +"\n");
				totalAchat += Math.abs(i.getQte()) * Integer.parseInt(i.getPrixAchat());
			}
			writerCr.write(";;;;MONTANT TOTAL :;" + totalAchat);
			csvPrinterCr.close();
			writerCr.close();
			java.awt.Desktop.getDesktop().open(crProd);			
			
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				// Mise à jour du stock
				for (int i = 0; i < elements.size(); i++) {
					daoE.update(elements.get(i), stocktmp.get(i));
					elements.set(elements.indexOf(elements.get(i)), stocktmp.get(i));
				}			
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Impossible de finaliser l'essai de production. \n"
					+ "Le prix d'achat de certains éléments n'est pas renseigné : "	+ elemManquant.getNom()				
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
    	tempsProdTF.clear();
    	nbQualifTF.clear();
    	nbNonQualifTF.clear();
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
	
	@FXML
	private void clicBoutonSemaineSuiv(ActionEvent event) throws IOException {
		int i = Integer.parseInt(semaineAct.getText());
		i++;
		semaineAct.setText(Integer.toString(i));
		this.semainePrec.setDisable(false);
		File chSemaine = new File(CHEMIN_SEMAINE +i +".csv");
		if (!chSemaine.isFile() && !chSemaine.isDirectory()) {
			chSemaine.createNewFile();
			System.out.println("Fichier crée sur l'ordinateur : " + chSemaine.getPath());
		}
		daoC.setCSV_FILE_PATH_CHAINE(chSemaine.getPath());
		this.chaines = FXCollections.observableArrayList(daoC.findAll()); // chargement
		tabChaines.setItems(chaines);
		tabChaines.refresh();
		if(i == 8) {
			this.semaineSuiv.setDisable(true);
			daoC.setCSV_FILE_PATH_CHAINE(CHEMIN_SEMAINE + "8.csv");
			this.chaines = FXCollections.observableArrayList(daoC.findAll()); // chargement
			tabChaines.setItems(chaines);
			tabChaines.refresh();
		}
	}
	
	@FXML
	private void clicBoutonSemainePrec(ActionEvent event) throws IOException {
		int i = Integer.parseInt(semaineAct.getText());
		i--;
		if(i>1) {			
			semaineAct.setText(Integer.toString(i));
			this.semaineSuiv.setDisable(false);
			File chSemaine = new File(CHEMIN_SEMAINE +i +".csv");
			if (!chSemaine.isFile() && !chSemaine.isDirectory()) {
				chSemaine.createNewFile();
				System.out.println("Fichier crée sur l'ordinateur : " + chSemaine.getPath());
			}
			daoC.setCSV_FILE_PATH_CHAINE(chSemaine.getPath());
			this.chaines = FXCollections.observableArrayList(daoC.findAll()); // chargement
			tabChaines.setItems(chaines);
			tabChaines.refresh();
		}
		if(i == 1) {
			semaineAct.setText(Integer.toString(i));
			this.semainePrec.setDisable(true);
			this.semaineSuiv.setDisable(false);
			daoC.setCSV_FILE_PATH_CHAINE(ControleurParams.pathCh);
			this.chaines = FXCollections.observableArrayList(daoC.findAll()); // chargement
			tabChaines.setItems(chaines);
			tabChaines.refresh();
		}
	}
	
	@FXML
	private void clicBoutonTesterAll(ActionEvent event) throws IOException{
		this.chaines.removeAll(chaines);
		daoC.setCSV_FILE_PATH_CHAINE(ControleurParams.pathCh);
		this.chaines.addAll(FXCollections.observableArrayList(daoC.findAll()));
		for(int i = 2;i<9;i++) {
			File semaine = new File(CHEMIN_SEMAINE +i +".csv");
			if(semaine.exists()) {
				daoC.setCSV_FILE_PATH_CHAINE(semaine.getPath());
			}
			System.out.println(i);
			this.chaines.addAll(FXCollections.observableArrayList(daoC.findAll()));
		}
		clicBoutonTesterChaine(event);	
		clicBoutonSemaineSuiv(event);
		clicBoutonSemainePrec(event);
	}
	
}
