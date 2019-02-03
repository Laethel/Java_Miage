package ajoutElem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Element;
import params.ControleurParams;
import stocks.ControleurStocks;
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
		BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_ELEMENT, true));
		bw.write(code.getText()+";" +nom.getText() +";" + unite.getText()+";" +qte.getText()+";" +achat.getText()+";" +vente.getText()+"\n");
		bw.flush();
		clicBoutonRetour(event);
	}
}
