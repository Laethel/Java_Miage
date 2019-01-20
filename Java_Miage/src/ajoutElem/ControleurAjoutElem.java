package ajoutElem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurAjoutElem {
	
	@FXML
	private Button retour;
	
	@FXML
	private Button valider;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Parent stocks = FXMLLoader.load(getClass().getResource("/stocks/VueStocks.fxml"));
		Scene stocks_scene = new Scene(stocks);
		Stage stocks_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stocks_stage.setScene(stocks_scene);
		stocks_stage.show();
	}
	
	@FXML 
	private void clicBoutonValider(ActionEvent event) throws IOException {

	}

}
