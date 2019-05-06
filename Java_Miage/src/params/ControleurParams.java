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
	 * Bouton permettant d'importer le fichier CSV o� sont stock�s les �l�ments
	 */
	@FXML
	private Button boutonImportElems;
	
	/**
	 * Bouton permettant d'importer le fichier CSV o� sont stock�s les chaines
	 */
	@FXML
	private Button boutonImportChaines;

	/**
	 * Bouton permettant de valider et de retourner � l'acceuil
	 */
	@FXML
	private Button boutonOk;
	
	/**
	 * Champ de texte indiquant le chemin vers le fichier CSV o� sont stock�s les �l�ments
	 */
	@FXML
	private Label pathElement;
	
	/**
	 * Champ de texte indiquant le chemin vers le fichier CSV o� sont stock�s les chaines
	 */
	@FXML
	private Label pathChaine;

	/**
	 * Instance de l'application
	 */
	private MainApp mainApp;
	
	/**
	 * Le fichier qui sera ouvert par l'utilisateur lors de l'exploration de ses dossiers et fichiers pour l'import
	 */
	private File fichier;
	
	/**
	 * Constante ou est stock�e le chemin vers le fichier CSV des �l�ments
	 */
	public static String pathElem;
	/**
	 * Constante ou est stock�e le chemin vers le fichier CSV des chaines
	 */
	public static String pathCh;

	/**
	 * Le fichier correspondant au chemin stock� pour les �l�ments par l'utilisateur
	 */
	private static File loadCsvElem = new File("./src/utils/loadCsvElem.txt");
	/**
	 * Le fichier correspondant au chemin stock� pour les chaines par l'utilisateur
	 */
	private static File loadCsvCh = new File("./src/utils/loadCsvCh.txt");

	/**
	 * @param main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	/**
	 * M�thode permettant d'importer le fichier CSV o� sont stock�s les �l�ments
	 */
	@FXML
	private void clicBoutonImportElem() {
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
	 * M�thode permettant d'importer le fichier CSV o� sont stock�s les chaines
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
}

