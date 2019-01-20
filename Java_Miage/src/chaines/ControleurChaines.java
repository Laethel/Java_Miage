package chaines;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurChaines {

	@FXML
	private Button retour;
	
	@FXML
	private Button testProd;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Parent accueil = FXMLLoader.load(getClass().getResource("/accueil/VueAccueil.fxml"));
		Scene accueil_scene = new Scene(accueil);
		Stage accueil_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		accueil_stage.setScene(accueil_scene);
		accueil_stage.show();
	}
	
	@FXML 
	private void clicBoutonTestProd(ActionEvent event) throws IOException {
		Parent testProd = FXMLLoader.load(getClass().getResource("/testProd/VueTestProd.fxml"));
		Scene testProd_scene = new Scene(testProd);
		Stage testProd_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		testProd_stage.setScene(testProd_scene);
		testProd_stage.show();
	}
}
