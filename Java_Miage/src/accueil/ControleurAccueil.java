package accueil;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurAccueil {

	@FXML
	private Button params;
	
	@FXML 
	private Button voirStocks;
	
	@FXML
	private Button voirChaines;
	
	@FXML
	private void clicBoutonParams(ActionEvent event) throws IOException {
		Parent parametres = FXMLLoader.load(getClass().getResource("/params/VueParams.fxml"));
		Scene parametres_scene = new Scene(parametres);
		Stage parametres_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		parametres_stage.setScene(parametres_scene);
		parametres_stage.show();
	}
	
	@FXML
	private void clicBoutonStocks(ActionEvent event) throws IOException {
		Parent stocks = FXMLLoader.load(getClass().getResource("/stocks/VueStocks.fxml"));
		Scene stocks_scene = new Scene(stocks);
		Stage stocks_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stocks_stage.setScene(stocks_scene);
		stocks_stage.show();
	}
	
	@FXML
	private void clicBoutonChaines(ActionEvent event) throws IOException {
		Parent chaines = FXMLLoader.load(getClass().getResource("/chaines/VueChaines.fxml"));
		Scene chaines_scene = new Scene(chaines);
		Stage chaines_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		chaines_stage.setScene(chaines_scene);
		chaines_stage.show();
	}
}
