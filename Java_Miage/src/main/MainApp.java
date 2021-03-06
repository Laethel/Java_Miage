package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import params.ControleurParams;

//La MainApp va nous permettre de lancer chaque page FXML
/**
 * @author Quentin Beaussart et Damian Riquart
 *
 */
public class MainApp extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/accueil/VueAccueil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setX(20.0);
        primaryStage.setY(20.0);
        primaryStage.show();
        
        //récupère les précédents CSV utilisés automatiquement
        BufferedReader brElem = new BufferedReader(new FileReader(ControleurParams.getLoadCsvElem()));
        String prevElem = brElem.readLine();
        System.out.println(prevElem);
        ControleurParams.pathElem = prevElem;
        brElem.close();
        
        BufferedReader brCh = new BufferedReader(new FileReader(ControleurParams.getLoadCsvCh()));
        String prevCh = brCh.readLine();
        System.out.println(prevCh);
        ControleurParams.pathCh = prevCh;
        brCh.close();
        
        BufferedReader brPers = new BufferedReader(new FileReader(ControleurParams.getLoadCsvPers()));
        String prevPers = brPers.readLine();
        System.out.println(prevPers);
        ControleurParams.pathPers = prevPers;
        brPers.close();
        
	}

	/**
	 * @param args Les arguments
	 * Cette méthode permet le lancement de l'application
	 * @throws IOException une erreur de lancement de l'application
	 */
	public static void main(String[] args) throws IOException {
		ControleurParams.install();
		launch(args);
	}
}