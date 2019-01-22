package params;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	@FXML
	private void clicBoutonImportElem() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		String filePathElement = fichier.getAbsolutePath();
		System.out.println(filePathElement);
		this.pathElement.setText(filePathElement);
		ControleurParams.pathElem = filePathElement;
	}
	
	@FXML
	private void clicBoutonImportChaine() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		String filePathChaine = fichier.getAbsolutePath();
		System.out.println(filePathChaine);
		this.pathChaine.setText(filePathChaine);
		ControleurParams.pathCh = filePathChaine;
	}
	
	@FXML
	private void clicBoutonOk(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
}

