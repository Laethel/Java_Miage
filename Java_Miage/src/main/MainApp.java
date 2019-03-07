package main;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import params.ControleurParams;

//La MainApp va nous permettre de lancer chaque page FXML
public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/accueil/VueAccueil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
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
        
	}

	public static void main(String[] args) {
		launch(args);
	}
}