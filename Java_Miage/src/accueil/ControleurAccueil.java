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
	 * Le bouton menant aux Param�tres
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
	 * Le bouton menant au Personnel
	 */
	@FXML
	private Button voirPersonnel;
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette m�thode permet de diriger l'utilisateur vers l'�cran de Param�tres, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonParams(ActionEvent event) throws IOException {
		Path.goTo(event, Way.PARAMS);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette m�thode permet de diriger l'utilisateur vers l'�cran de Stocks, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonStocks(ActionEvent event) throws IOException {
		Path.goTo(event, Way.STOCKS);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette m�thode permet de diriger l'utilisateur vers l'�cran de Chaines, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonChaines(ActionEvent event) throws IOException {
		Path.goTo(event, Way.CHAINES);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Cette m�thode permet de diriger l'utilisateur vers l'�cran du Personnel, lorsqu'il clique sur le bouton
	 */
	@FXML
	private void clicBoutonPersonnel(ActionEvent event) throws IOException {
		Path.goTo(event, Way.PERSONNEL);
	}
}
