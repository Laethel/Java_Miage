package testProd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import params.ControleurParams;
import utils.Path;
import utils.Path.Way;

public class ControleurTestProd {
	
    private static final String CSV_FILE_PATH_CHAINES = ControleurParams.pathCh;
    
	@FXML
	private TextField code;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField elemEntree;
	
	@FXML
	private TextField elemSortie;
	
	@FXML
	private TextField nivActivite;

	@FXML
	private Button retour;
	
	@FXML
	private Button valider;
	
	@FXML 
	private void clicBoutonRetour(ActionEvent event) throws IOException {
		Path.goTo(event, Way.CHAINES);
	}
	
	@FXML 
	private void clicBoutonValider(ActionEvent event) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_CHAINES, true));
		bw.write(code.getText()+";" +nom.getText() +";" + elemEntree.getText()+";" +elemSortie.getText()+";" +nivActivite.getText()+"\n");
		bw.flush();
		clicBoutonRetour(event);
	}
}
