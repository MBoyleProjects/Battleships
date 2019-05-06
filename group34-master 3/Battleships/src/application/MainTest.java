package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardGridUpdated.fxml"));
			Parent root = fxmlLoader.load();
			BoardScreenController controller = fxmlLoader.getController();
				
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Battleships Play Grids");
			primaryStage.show();
				
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
		
		
	}


