package zTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import params.ControleurParams;
import utils.Path;
import utils.Path.Way;

/**
 * @author Quentin Beaussart et Damian Riquart
 * 
 */
public class ControleurTestProd {
	
    /**
     * Constante stockant le chemin vers le fichiers CSV stochant les informations des chaines de production
     */
    private static final String CSV_FILE_PATH_CHAINES = ControleurParams.pathCh;
    
	/**
	 * Le code de la chaine
	 */
	@FXML
	private TextField code;
	
	/**
	 * Le nom de la chaine
	 */
	@FXML
	private TextField nom;
	
	/**
	 * Les �l�ments en entr�e de la chaine
	 */
	@FXML
	private TextField elemEntree;
	
	/**
	 * Les �l�ments en sortie de la chaine
	 */
	@FXML
	private TextField elemSortie;
	
	/**
	 * Le niveau d'activit� de la chaine
	 */
	@FXML
	private TextField nivActivite;

	/**
	 * Le bouton "Retour", permettan de revenir sur l'�cran des chaines
	 */
	@FXML
	private Button retour;
	
	/**
	 * Le bouton "Valider", permettant de sauvegarder les changements effectu�s aux chaines
	 */
	@FXML
	private Button valider;
	
	/**
	 * @param event
	 * @throws IOException
	 * M�thode d�clench�e lors du clic sur le bouton "Retour", permettant de revenir � l'�cran des chaines
	 */
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.CHAINES);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * M�thode d�clench�e lors du clic sur le bouton "Valider", permettant de sauvegarder l'ajout ou la modification d'une chaine de production
	 */
	@FXML 
	private void clicBoutonValider(ActionEvent event) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_CHAINES, true));
		bw.write(code.getText()+";" +nom.getText() +";" + elemEntree.getText()+";" +elemSortie.getText()+";" +nivActivite.getText()+"\n");
		bw.close();
		clicBoutonRetour(event);
	}
}
