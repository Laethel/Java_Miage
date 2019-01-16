package test; 
  
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage; 
  
public class Main extends Application { 
  
	@Override 
	public void start(Stage primaryStage) throws Exception { 
		final Rectangle rectangle = new Rectangle(50, 50, 150, 100); 
		rectangle.setFill(Color.RED); 
		final Pane root = new Pane(); 
		root.getChildren().setAll(rectangle); 
		final Scene scene = new Scene(root, 300, 250); 
		primaryStage.setTitle("Test dans Eclipse"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	} 
  
	public static void main(String... args) { 
		Application.launch(args); 
	} 
}