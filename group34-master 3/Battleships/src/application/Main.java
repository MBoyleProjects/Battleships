package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	Stage primaryStage;
	Client client;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		String host = "localhost";
		int port = 8888;
		client = new Client(host, port, this);// this points to itself
		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			System.exit(0);
		});

		changeToLoginPage();
	}

	public void changeToLoginPage() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
			Parent root = fxmlLoader.load();
			LoginPageController controller = fxmlLoader.getController();
			controller.setClient(client);
			controller.setMain(this);// pointing to this instance
			this.client.setLoginPageController(controller);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Battleships Login & Signup");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeToRegisterScreen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
			Parent root = fxmlLoader.load();
			RegisterUserController controller = fxmlLoader.getController();
			controller.setClient(client);
			controller.setMain(this);
			this.client.setRegisterUserController(controller);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Battleships Register User");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeToRegisterSuccessfulScreen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SuccessfulRegistration.fxml"));
			Parent root = fxmlLoader.load();
			SuccessfulRegistrationController controller = fxmlLoader.getController();
			controller.setClient(client);
			controller.setMain(this);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Successfully Registered!");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeToGameScreen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardGridUpdated.fxml"));
			Parent root = fxmlLoader.load();
			BoardScreenController controller = fxmlLoader.getController();
			controller.setClient(client);
			controller.setMain(this);
			client.setBoardScreenController(controller);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Battleships Board Grid");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gameStartResponse(String success) {
		if (success.equals("true")) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardGridUpdated.fxml"));
				Parent root = fxmlLoader.load();
				BoardScreenController controller = fxmlLoader.getController();
				controller.setClient(client);
				controller.setMain(this);
				client.setBoardScreenController(controller);

				controller.startGameResponse(success);

				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.sizeToScene();
				primaryStage.setTitle("Battleships Board Grid");
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void endGameWin() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameEndWin.fxml"));
			Parent root = fxmlLoader.load();
			endGameController controller = fxmlLoader.getController();
			controller.setClient(client);
			controller.setMain(this);
			client.setEndGameController(controller);


			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Congratulations! You Won!");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void endGameLoss() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameEndLoss.fxml"));
			Parent root = fxmlLoader.load();
			endGameController controller = fxmlLoader.getController();
			controller.setClient(client);
			controller.setMain(this);
			client.setEndGameController(controller);


			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("You Lost - Better Luck Next Time!");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
//	public void serverDisconnectError() {
//		System.out.println("the server disconnect error method has been called");
//		Alert alert = new Alert(AlertType.WARNING);
//		alert.setTitle("Warning Dialog");
//		alert.setHeaderText("Look, a Warning Dialog");
//		alert.setContentText("Careful with the next step!");
//
//		alert.show();
//		
//		
//	}
		
	
	public static void main(String[] args) {
		launch(args);
	}
}
