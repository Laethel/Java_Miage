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

public class ControleurParams {

	@FXML
	private Button boutonImportElems;
	
	@FXML
	private Button boutonImportChaines;

	@FXML
	private Button boutonOk;
	
	@FXML
	private Label pathElement;
	
	@FXML
	private Label pathChaine;

	private MainApp mainApp;
	
	private File fichier;
	
	public static String pathElem;
	public static String pathCh;

	private static File loadCsvElem = new File("./src/utils/loadCsvElem.txt");
	private static File loadCsvCh = new File("./src/utils/loadCsvCh.txt");

	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
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
	
	@FXML
	private void clicBoutonOk(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	
	public static File getLoadCsvElem() {
		return loadCsvElem;
	}

	public static File getLoadCsvCh() {
		return loadCsvCh;
	}
}

