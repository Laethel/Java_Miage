package params;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.MainApp;
import utils.Path;
import utils.Path.Way;

/**
 * @author Quentin Beaussart et Damian Riquart
 * 
 */
public class ControleurParams {

	/**
	 * Bouton permettant d'importer le fichier CSV où sont stockés les éléments
	 */
	@FXML
	private Button boutonImportElems;
	
	/**
	 * Bouton permettant d'importer le fichier CSV où sont stockés les chaines
	 */
	@FXML
	private Button boutonImportChaines;
	
	/**
	 * Bouton permettant d'importer le fichier CSV où sont stockés les membres du personnel
	 */
	@FXML
	private Button boutonImportPersonnel;

	/**
	 * Bouton permettant de valider et de retourner à l'acceuil
	 */
	@FXML
	private Button boutonOk;
	
	/**
	 * Champ de texte indiquant le chemin vers le fichier CSV où sont stockés les éléments
	 */
	@FXML
	private Label pathElement;
	
	/**
	 * Champ de texte indiquant le chemin vers le fichier CSV où sont stockés les chaines
	 */
	@FXML
	private Label pathChaine;
	
	/**
	 * Champ de texte indiquant le chemin vers le fichier CSV où sont stockés les membres du personnel
	 */
	@FXML
	private Label pathPersonnel;

	/**
	 * Instance de l'application
	 */
	private MainApp mainApp;
	
	/**
	 * Le fichier qui sera ouvert par l'utilisateur lors de l'exploration de ses dossiers et fichiers pour l'import
	 */
	private File fichier;
	
	/**
	 * Constante ou est stockée le chemin vers le fichier CSV des éléments
	 */
	public static String pathElem;
	
	/**
	 * Constante ou est stockée le chemin vers le fichier CSV des chaines
	 */
	public static String pathCh;
	
	/**
	 * Constante ou est stockée le chemin vers le fichier CSV des membres du personnel
	 */
	public static String pathPers;

	/**
	 * Le fichier correspondant au chemin stocké pour les éléments par l'utilisateur
	 */
	private static File loadCsvElem;
	
	/**
	 * Le fichier correspondant au chemin stocké pour les chaines par l'utilisateur
	 */
	private static File loadCsvCh;
	
	/**
	 * Le fichier correspondant au chemin stocké pour les membres du personnel par l'utilisateur
	 */
	private static File loadCsvPers;
	
	/**
	 * Le dossier d'installation des ressources de l'application
	 */
	private static File gestionProduction;
	
	/**
	 * Le fichier de compte-rendu de la production
	 */
	private static File crProd;

	/**
	 * @param main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	/**
	 * Méthode permettant l'installation des fichiers de ressource nécessaires au bon fonctionnement de l'application
	 * @throws IOException
	 */
	public static void install() throws IOException {
		System.out.println(System.getProperty("user.home"));
		gestionProduction = new File(System.getProperty("user.home") +"/Gestion production");
		if (!gestionProduction.exists() && !gestionProduction.isDirectory()) {
			gestionProduction.mkdirs();
			System.out.println("Dossier crée sur l'ordinateur : " + gestionProduction.getPath());
		}
		loadCsvElem = new File(System.getProperty("user.home") +"/Gestion production/loadCsvElem.txt");
		if (!loadCsvElem.isFile() && !loadCsvElem.isDirectory()) {
			loadCsvElem.createNewFile();
			System.out.println("Fichier crée sur l'ordinateur : " + loadCsvElem.getPath());
		}
		loadCsvCh = new File(System.getProperty("user.home") +"/Gestion production/loadCsvCh.txt");
		if (!loadCsvCh.isFile() && !loadCsvCh.isDirectory()) {
			loadCsvCh.createNewFile();
			System.out.println("Fichier crée sur l'ordinateur : " + loadCsvCh.getPath());
		}
		loadCsvPers = new File(System.getProperty("user.home") +"/Gestion production/loadCsvPers.txt");
		if (!loadCsvPers.isFile() && !loadCsvPers.isDirectory()) {
			loadCsvPers.createNewFile();
			System.out.println("Fichier crée sur l'ordinateur : " + loadCsvPers.getPath());
		}
		crProd = new File(System.getProperty("user.home") +"/Gestion production/crProd.csv");
		if (!crProd.isFile() && !crProd.isDirectory()) {
			crProd.createNewFile();
			System.out.println("Fichier crée sur l'ordinateur : " + crProd.getPath());
		}
	}
	
	/**
	 * Méthode permettant d'importer le fichier CSV ou sont stockés les éléments
	 * @throws IOException 
	 */
	@FXML
	private void clicBoutonImportElem() throws IOException {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		String filePathElement = this.fichier.getAbsolutePath();
		System.out.println(filePathElement);
		this.pathElement.setText(filePathElement);
		ControleurParams.pathElem = filePathElement;
		

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(loadCsvElem));				
			bw.write(filePathElement + "\n");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur d'inscription dans le fichier de sauvegarde des chemins (Elements)");
		}
	}
	
	/**
	 * Méthode permettant d'importer le fichier CSV où sont stockés les chaines
	 */
	@FXML
	private void clicBoutonImportChaine() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		String filePathChaine = this.fichier.getAbsolutePath();
		System.out.println(filePathChaine);
		this.pathChaine.setText(filePathChaine);
		ControleurParams.pathCh = filePathChaine;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(loadCsvCh));				
			bw.write(filePathChaine + "\n");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur d'inscription dans le fichier de sauvegarde des chemins (Chaines)");
		}
	}
	
	/**
	 * Méthode permettant d'importer le fichier CSV où sont stockés les membres du personnel
	 */
	@FXML
	private void clicBoutonImportPersonnel() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		String filePathPersonnel = this.fichier.getAbsolutePath();
		System.out.println(filePathPersonnel);
		this.pathPersonnel.setText(filePathPersonnel);
		ControleurParams.pathCh = filePathPersonnel;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(loadCsvPers));				
			bw.write(filePathPersonnel + "\n");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur d'inscription dans le fichier de sauvegarde des chemins (Personnel)");
		}
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * M�thode d�clench�e lors du clic sur le bouton "OK" permettant de retourner � l'acceuil
	 */
	@FXML
	private void clicBoutonOk(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	
	/**
	 * @return
	 * Renvoie le fichier ou est stock� le chemin vers le fichier CSV des �l�ments
	 */
	public static File getLoadCsvElem() {
		return loadCsvElem;
	}

	/**
	 * @return
	 * Renvoie le fichier ou est stock� le chemin vers le fichier CSV des chaines
	 */
	public static File getLoadCsvCh() {
		return loadCsvCh;
	}
	
	/**
	 * @return
	 * Renvoie le fichier ou est stock� le chemin vers le fichier du CR de production
	 */
	public static File getCrProd() {
		return crProd;
	}
}

