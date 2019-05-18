package utils;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Quentin Beaussart et Damian Riquart
 * 
 */
public class Path {

	
	
	/**
	 * Enumeration listant les chemins vers les differentes pages de l'application
	 *
	 */
	public enum Way {	
		ACCUEIL("/accueil/VueAccueil.fxml"), 
		AJOUT_ELEM("/ajoutElem/VueAjoutElem.fxml"),
		CHAINES("/chaines/VueChaines.fxml"),
		PARAMS("/params/VueParams.fxml"),
		STOCKS("/stocks/VueStocks.fxml"),
		TEST_PROD("/testProd/VueTestProd.fxml"),
		PERSONNEL("/personnel/VuePersonnel.fxml");
	    
	    private String chemin;
	     
	    private Way(String chemin) {  
	        this.chemin = chemin ;  
	   }  
	     
	    public String getChemin() {  
	        return this.chemin ;  
	   }  
	    
	}

	/**
	 * @param actionEvent une action utilisateur
	 * @param way Un chemin vers une autre page
	 * Methode prenant en parametres un des elements de l'enum "Way", permettant d'acceder a une des pages de l'application
	 */
	public static void goTo(ActionEvent actionEvent, Way way) {
		try {
			Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			Parent elementPageParent = FXMLLoader.load(Path.class.getResource(way.getChemin()));
			Scene elementPageScene = new Scene(elementPageParent);
			Stage sceneActuel = stage;
			sceneActuel.setScene(elementPageScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
