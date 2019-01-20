package testProd;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurTestProd {

	@FXML
	private Button retour;
	
	@FXML
	private Button valider;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Parent chaines = FXMLLoader.load(getClass().getResource("/chaines/VueChaines.fxml"));
		Scene chaines_scene = new Scene(chaines);
		Stage chaines_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		chaines_stage.setScene(chaines_scene);
		chaines_stage.show();
	}
	
	@FXML 
	private void clicBoutonValider(ActionEvent event) throws IOException {

	}
}
