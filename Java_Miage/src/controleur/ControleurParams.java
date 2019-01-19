package controleur;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControleurParams {

	@FXML
	private Button boutonImport;


	private MainApp mainApp;
	private File fichier;

	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	@FXML
	private void clicBoutonImport() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
	}
	
}

