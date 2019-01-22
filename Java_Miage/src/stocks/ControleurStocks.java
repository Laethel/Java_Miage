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
import utils.Path;
import utils.Path.Way;

public class ControleurStocks {

	@FXML
	private Button retour;
	
	@FXML
	private Button ajouterElem;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.ACCUEIL);
	}
	
	@FXML 
	private void clicBoutonAjoutElem(ActionEvent event) throws IOException {
		Path.goTo(event, Way.AJOUT_ELEM);
	}
}
