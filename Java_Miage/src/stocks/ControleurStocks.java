package stocks;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurStocks {

	@FXML
	private Button retour;
	
	@FXML
	private Button ajouterElem;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Parent accueil = FXMLLoader.load(getClass().getResource("/accueil/VueAccueil.fxml"));
		Scene accueil_scene = new Scene(accueil);
		Stage accueil_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		accueil_stage.setScene(accueil_scene);
		accueil_stage.show();
	}
	
	@FXML 
	private void clicBoutonAjoutElem(ActionEvent event) throws IOException {
		Parent ajoutElem = FXMLLoader.load(getClass().getResource("/ajoutElem/VueAjoutElem.fxml"));
		Scene ajoutElem_scene = new Scene(ajoutElem);
		Stage ajoutElem_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ajoutElem_stage.setScene(ajoutElem_scene);
		ajoutElem_stage.show();
	}
}
