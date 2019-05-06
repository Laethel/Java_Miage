package accueil;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.Path;
import utils.Path.Way;

/**
 * @author Quentin Beaussart et Damian Riquart
 * 
 */
public class ControleurAccueil {

	/**
	 * Le bouton menant aux Paramètres
	 */
	@FXML
	private Button params;
	
	/**
	 * Le bouton menant aux Stocks
	 */
	@FXML 
	private Button voirStocks;
	
	/**
	 * Le bouton menant aux Chaines
	 */
	@FXML
	private Button voirChaines;
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette méthode permet de diriger l'utilisateur vers l'écran de Paramètres, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonParams(ActionEvent event) throws IOException {
		Path.goTo(event, Way.PARAMS);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette méthode permet de diriger l'utilisateur vers l'écran de Stocks, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonStocks(ActionEvent event) throws IOException {
		Path.goTo(event, Way.STOCKS);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette méthode permet de diriger l'utilisateur vers l'écran de Chaines, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonChaines(ActionEvent event) throws IOException {
		Path.goTo(event, Way.CHAINES);
	}
}
