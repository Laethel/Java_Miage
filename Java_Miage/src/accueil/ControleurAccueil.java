package accueil;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.Path;
import utils.Path.Way;

public class ControleurAccueil {

	@FXML
	private Button params;
	
	@FXML 
	private Button voirStocks;
	
	@FXML
	private Button voirChaines;
	
	@FXML
	private void clicBoutonParams(ActionEvent event) throws IOException {
		Path.goTo(event, Way.PARAMS);
	}
	
	@FXML
	private void clicBoutonStocks(ActionEvent event) throws IOException {
		Path.goTo(event, Way.STOCKS);
	}
	
	@FXML
	private void clicBoutonChaines(ActionEvent event) throws IOException {
		Path.goTo(event, Way.CHAINES);
	}
}
