package chaines;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import dao.ChaineDAO;
import dao.ElementDAO;
import dao.PersonnelDAO;
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
	
	/**
	 * Le fichier de compte-rendu de la production
	 */
	private static File crProd = ControleurParams.getCrProd();
	
	/**
	 * Le chemin du dossier qui contient les fichiers de chaine par semaine
	 */
	private final String CHEMIN_SEMAINE = System.getProperty("user.home")+"/Gestion production/chaineSemaine_";
	
	/**
	 * Entête des fichiers chaines
	 */
	private final String ENTETE_CHAINE = "Code;Nom;Entree (code,qte);Sortie (code,qte);Temps;Personnels non qualifies;Personnels qualifies;NivActivite \n";

	@FXML
	/**
	 * Bouton de retour à l'accueil
	 */
	private Button retour;
	
	/**
	 * Le bouton permettant d'ajouter une chaine de production ou d'en modifier les parametres
	 */
	@FXML
	private Button testProd;
	
	/**
	 * Le tableau contenant les differentes colonnes d'information des chaines
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
	 * La colonne indiquant les elements en entree de la chaine
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
	
	/**
	 * Le champ de texte de la quantité d'un élèment en entrée
	 */
	@FXML
	private TextField elemEntreeQteTF;
	
	/**
	 * Le champ de texte des elements en entrée de la chaine
	 */
	@FXML
	private TextField elemEntreeTF;
	
	/**
	 * Le champ de texte de la quantité d'un élèment en sortie
	 */
	@FXML
	private TextField elemSortieQteTF;
	
	/**
	 * Le champ de texte des elements en sortie de la chaine
	 */
	@FXML
	private TextField elemSortieTF;
	
	/**
	 * Le champ de text de temps de production
	 */
	@FXML
	private TextField tempsProdTF;
	
	/**
	 * Le champ de texte du nombre de personnes qualifiées nécessaires
	 */
	@FXML
	private TextField nbQualifTF;
	
	/**
	 * Le champ de texte du nombre de personnes non qualifiées nécessaires
	 */
	@FXML
	private TextField nbNonQualifTF;
	
	/**
	 * Le champ de texte du niveau d'activité de la chaine
	 */
	@FXML
	private TextField nivActiviteTF;
	
	/**
	 * La liste déroulante des éléments en entrée de la chaine
	 */
	@FXML
	private ChoiceBox<Element> entreeCB;
	
	/**
	 * La liste déroulante des éléments en sortie de la chaine
	 */
	@FXML
	private ChoiceBox<Element> sortieCB;
	
	/**
	 * Bouton pour ajouter un élèment en entrée
	 */
	@FXML
	private Button ajouterElemEntree;
	
	/**
	 * Bouton pour ajouter un élèment en entrée
	 */
	@FXML
	private Button ajouterElemSortie;
	
	/**
	 * Bouton pour réinitialiser la liste des éléments en entrée
	 */
	@FXML
	private Button reinitElemEntree;
	
	/**
	 * Bouton pour réinitialiser la liste des éléments en entrée
	 */
	@FXML
	private Button reinitElemSortie;
	
	/**
	 * Bouton pour ajouter une nouvelle chaîne
	 */
	@FXML
	private Button ajouterChaine;
	
	/**
	 * Bouton pour modifier une chaîne
	 */
	@FXML
	private Button modifierChaine;
	
	/**
	 * Bouton pour annuler une modification sur une chaîne
	 */
	@FXML
	private Button annulerModifChaine;
	
	/**
	 * Bouton pour supprimer une chaîne
	 */
	@FXML
	private Button supprimerChaine;
	
	/**
	 * Bouton pour tester une production jusqu'à la semaine selectionnée
	 */
	@FXML
	private Button testerChaine;
	
	/**
	 * Bouton pour tester une production pour toutes les semaines
	 */
	@FXML
	private Button testerSemainesAll;
	
	/**
	 * Bouton pour réinitialiser un test de production
	 */
	@FXML
	private Button resetTest;
	
	/**
	 * Bouton pour charger le fichier de la semaine précédente
	 */
	@FXML
	private Button semainePrec;
	
	/**
	 * Bouton pour charger le fichier de la semaine suivante
	 */
	@FXML
	private Button semaineSuiv;
	
	/**
	 * Label qui affiche la semaine selectionnée
	 */
	@FXML
	private Label semaineAct;
		
	/**
	 * Objet DAO pour communiquer avec les fichiers csv
	 */
	private ChaineDAO daoC = new ChaineDAO();
	private ElementDAO daoE = new ElementDAO();
	private PersonnelDAO daoP = new PersonnelDAO();
	
	/**
	 * ObservableListe qui contient les chaines d'une semaine
	 */
	private ObservableList<Chaine> chaines;
	
	/**
	 * Variable qui stock l'ancienne valeur d'une chaine en cas de modification
	 */
	private Chaine oldChaine;
	
	/**
	 * ArrayList qui contient tous les éléments du stock
	 */
	ArrayList<Element> elements;
	
	/**
	 * Permet de rendre accessible les boutons du formulaire en fon
	 */
	private BooleanBinding bbForm;
	private BooleanBinding bbElemEntree;
	private BooleanBinding bbElemSortie;

	/**
	 * Permet de réinitialiser le stock des éléments après un test de production
	 */
	ArrayList<Element> elementsSave = new ArrayList<Element>();
	
	/**
	 * Variable qui stock le total des heures des ouvriers non qualifiés et qualifiés disponible
	 */
	double heuresNonQualifDispo;
	double heuresQualifDispo;
	

	/**
	 * Initialiser les données à afficher sur l'écran des chaines
	 */
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
		
		this.heuresQualifDispo = daoP.heuresQualifDispo();
		this.heuresNonQualifDispo = daoP.heuresNonQualifDispo();
		System.out.println("Temps qualifié disponible par semaine : "+heuresQualifDispo);
		System.out.println("Temps non qualifié disponible par semaine : "+heuresNonQualifDispo);
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
	 * Méthode déclenchée lors du clic sur une ligne du table, charge le formulaire d'une chaine
	 */
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Réinitialiser" des éléments en entrée, remet à zéro le champ des éléments en entrée 
	 */
	@FXML 
	private void clicBoutonReinitElemEntree(ActionEvent event) throws IOException {
		elemEntreeTF.clear();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Réinitialiser" des éléments en sortie, remet à zéro le champ des éléments en sortie 
	 */
	@FXML 
	private void clicBoutonReinitElemSortie(ActionEvent event) throws IOException {
		elemSortieTF.clear();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Ajouter" des éléments en entrée, ajoute un élément dans la liste des éléments en entrée
	 */
	@FXML 
	private void clicBoutonAjouterElemEntree(ActionEvent event) throws IOException {
		if (elemEntreeTF.getText().isEmpty()) {
			elemEntreeTF.setText("(" + entreeCB.getValue().getCode() + "," + elemEntreeQteTF.getText() + ")");	
		} else {
			elemEntreeTF.setText(elemEntreeTF.getText() + ",(" + entreeCB.getValue().getCode() + "," + elemEntreeQteTF.getText() + ")");	
		}
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Ajouter" des éléments en sortie, ajoute un élément dans la liste des éléments en sortie
	 */
	@FXML 
	private void clicBoutonAjouterElemSortie(ActionEvent event) throws IOException {
		if (elemSortieTF.getText().isEmpty()) {
			elemSortieTF.setText("(" + sortieCB.getValue().getCode() + "," + elemSortieQteTF.getText() + ")");	
		} else {
			elemSortieTF.setText(elemSortieTF.getText() + ",(" + sortieCB.getValue().getCode() + "," + elemSortieQteTF.getText() + ")");	
		}
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Ajouter", permet de créer une nouvelle chaine
	 */
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Modifier", permet de modifier une chaine
	 */
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Annuler modif.", permet d'annuler les modifications d'une chaine
	 */
	@FXML 
	private void clicBoutonAnnulerModificationChaine(ActionEvent event) throws IOException {
		clearTextField();
		setDisableButtons(true);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Réinitialiser le test", permet de réinitialiser les stocks 
	 * après un test de production
	 */
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Supprimer.", permet de supprimer une chaine
	 */
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Permet de calculer le résultat d'une production et de générer son compte rendu
	 */
	private void testerChaine(ActionEvent event) throws IOException {
		loadElemEntreeSortie();
		
		ArrayList<Element> stocktmp = daoE.findAll();
		ArrayList<Element> listeAchat = new ArrayList<Element>();
		
		int resultat = 0;
		boolean productionPossible = true;
		boolean heuresOk = true;
		Element elemManquant = null;
		double tempsQualifNecessaire = 0.0;
		double tempsNonQualifNecessaire = 0.0;
		
		
		for (Chaine ch : chaines) {
			tempsQualifNecessaire += ch.getTempsProd()*ch.getNbQualif();
			tempsNonQualifNecessaire += ch.getTempsProd()*ch.getNbNonQualif();
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
		System.out.println("Temps qualifié nécessaire : "+tempsQualifNecessaire);
		System.out.println("Temps qualifié disponible : "+heuresQualifDispo);
		System.out.println("Temps non qualifié nécessaire : "+tempsNonQualifNecessaire);
		System.out.println("Temps non qualifié disponible : "+heuresNonQualifDispo);
		
		if(tempsQualifNecessaire > heuresQualifDispo || tempsNonQualifNecessaire > heuresNonQualifDispo) {
			heuresOk = false;
		}
		
		if (productionPossible && heuresOk) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Essai de production terminé. Résultat obtenu : " + resultat + ".\n \n"
					+ "Temps de main d'oeuvre qualifiée nécessaire : " + tempsQualifNecessaire + " heures. \n"
					+ "Temps de main d'oeuvre non qualifiée nécessaire : " + tempsNonQualifNecessaire + " heures. \n \n"
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
		}

		if(!heuresOk) {
			Alert alert = new Alert(AlertType.WARNING, "Impossible de finaliser l'essai de production. \n \n"
					+ "La main d'oeuvre nécessaire n'est pas disponible pour faire fonctionner toutes les chaines : \n \n"
					+ "Temps qualifié nécessaire : "+tempsQualifNecessaire +" heures\n"
					+ "Temps qualifié disponible : "+heuresQualifDispo +" heures \n \n"
					+ "Temps non qualifié nécessaire : "+tempsNonQualifNecessaire +" heures \n"
					+ "Temps non qualifié disponible : "+heuresNonQualifDispo +" heures \n"	
					, ButtonType.OK);
			alert.showAndWait();
		}
		if(!productionPossible) {
			Alert alert = new Alert(AlertType.WARNING, "Impossible de finaliser l'essai de production. \n"
					+ "Le prix d'achat de certains éléments n'est pas renseigné : "	+ elemManquant.getNom()				
					, ButtonType.OK);
			alert.showAndWait();
		}
		
	}
	
	/**
	 * Méthode qui permet de charger les éléments en entrée / sortie de chaque chaine
	 */
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
	
	/**
	 * Méthode qui permet de réinitialiser les champs du formulaire
	 */
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
	
	/**
	 * Désactive les boutons de modification, annul modif et de suppression en fonction du contenu du formulaire
	 */
	private void setDisableButtons(boolean pDisable) {
		this.modifierChaine.setDisable(pDisable);
		this.annulerModifChaine.setDisable(pDisable);
		this.supprimerChaine.setDisable(pDisable);
	}
		
	/**
	 * Permet de formater le contenu des listes déroulantes
	 */
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Semaine suivante.", permet de charger le fichier des chaines de la semaine suivante
	 */
	@FXML
	private void clicBoutonSemaineSuiv(ActionEvent event) throws IOException {
		int i = Integer.parseInt(semaineAct.getText());
		i++;
		semaineAct.setText(Integer.toString(i));
		this.semainePrec.setDisable(false);
		File chSemaine = new File(CHEMIN_SEMAINE +i +".csv");
		if (!chSemaine.isFile() && !chSemaine.isDirectory()) {
			chSemaine.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(chSemaine));
			bw.write(ENTETE_CHAINE);
			bw.close();
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Semaine précédente.", permet de charger le fichier des chaines de la semaine précédente
	 */
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
				BufferedWriter bw = new BufferedWriter(new FileWriter(chSemaine));
				bw.write(ENTETE_CHAINE);
				bw.close();
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
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Tester la production de toutes les semaines", 
	 * permet de calculer le résultat et le compte rendu d'une production de toutes les semaines
	 */
	@FXML
	private void clicBoutonTesterAll(ActionEvent event) throws IOException{
		this.chaines.removeAll(chaines);
		daoC.setCSV_FILE_PATH_CHAINE(ControleurParams.pathCh);
		this.chaines.addAll(FXCollections.observableArrayList(daoC.findAll()));
		this.heuresNonQualifDispo = daoP.heuresNonQualifDispo()*8;
		this.heuresQualifDispo = daoP.heuresQualifDispo()*8;
		for(int i = 2;i<9;i++) {
			File semaine = new File(CHEMIN_SEMAINE +i +".csv");
			if(semaine.exists()) {
				daoC.setCSV_FILE_PATH_CHAINE(semaine.getPath());
			}
			this.chaines.addAll(FXCollections.observableArrayList(daoC.findAll()));
		}
		testerChaine(event);	
		clicBoutonSemaineSuiv(event);
		clicBoutonSemainePrec(event);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Méthode déclenchée lors du clic sur le bouton "Tester la production de la semaine", 
	 * permet de calculer le résultat et le compte rendu d'une production jusqu'à la semaine sélectionnée 
	 */
	@FXML
	private void clicBoutonTesterSemaine(ActionEvent event) throws IOException{
		this.chaines.removeAll(chaines);
		int semaineActuelle = Integer.parseInt(semaineAct.getText());
		daoC.setCSV_FILE_PATH_CHAINE(ControleurParams.pathCh);
		this.chaines.addAll(FXCollections.observableArrayList(daoC.findAll()));
		this.heuresNonQualifDispo = daoP.heuresNonQualifDispo()*semaineActuelle;
		this.heuresQualifDispo = daoP.heuresQualifDispo()*semaineActuelle;
		for(int i = 2;i<=semaineActuelle;i++) {
			File semaine = new File(CHEMIN_SEMAINE +i +".csv");
			if(semaine.exists()) {
				daoC.setCSV_FILE_PATH_CHAINE(semaine.getPath());
			}
			this.chaines.addAll(FXCollections.observableArrayList(daoC.findAll()));
		}
		testerChaine(event);	
		clicBoutonSemaineSuiv(event);
		clicBoutonSemainePrec(event);
	}
	
}
