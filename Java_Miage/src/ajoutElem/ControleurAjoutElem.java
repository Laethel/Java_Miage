package ajoutElem;

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

public class ControleurAjoutElem {
	
    private static final String CSV_FILE_PATH_ELEMENT = ControleurParams.pathElem;
	
	@FXML
	private TextField code;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField unite;
	
	@FXML
	private TextField qte;
	
	@FXML
	private TextField achat;
	
	@FXML
	private TextField vente;
	
	@FXML
	private Button retour;
	
	@FXML
	private Button valider;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.STOCKS);
	}
	
	@FXML 
	private void clicBoutonValider(ActionEvent event) throws IOException {	
	}
}
