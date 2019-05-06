package pastProgress;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardGridLoader extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardGrid.fxml"));
		Parent root = fxmlLoader.load();
//		   Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		//LoginPageController controller = fxmlLoader.getController();
		//String host = "localhost";
		//int port = 8888;
		//Client client = new Client(host, port);
		//controller.setClient(client);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		//primaryStage.sizeToScene();
		//primaryStage.setTitle("Battleships Login & Signup");
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}



