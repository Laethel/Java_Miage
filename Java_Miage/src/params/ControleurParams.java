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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.MainApp;

public class ControleurParams {

	@FXML
	private Button boutonImportElems;
	
	@FXML
	private Button boutonImportChaines;

	@FXML
	private Button boutonOk;

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
	
	@FXML
	private void clicBoutonOk(ActionEvent event) throws IOException {
		Parent accueil = FXMLLoader.load(getClass().getResource("/accueil/VueAccueil.fxml"));
		Scene accueil_scene = new Scene(accueil);
		Stage accueil_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		accueil_stage.setScene(accueil_scene);
		accueil_stage.show();
	}
	
}

