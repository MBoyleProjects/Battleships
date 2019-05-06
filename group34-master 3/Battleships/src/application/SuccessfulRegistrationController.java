package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfulRegistrationController {
	@FXML
	private Button backToLoginButton;

	@FXML
	private Button backToRegisterButton;

	private Client client;
	private Main myGUI;

	@FXML
	void backToLogin(ActionEvent event) {

		Platform.runLater(new Runnable() { //we do this because javafx needs to run on the javafx thread, else it wont work.
		@Override
		public void run() {
			myGUI.changeToLoginPage();
		}
			
		}	
	);

	}

	@FXML
	void backToRegister(ActionEvent event) {

		Platform.runLater(new Runnable() { //we do this because javafx needs to run on the javafx thread, else it wont work.
		@Override
		public void run() {
			myGUI.changeToRegisterScreen();
		}
			
		}	
	);

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;

	}

	public void setMain(Main main) {
		this.myGUI = main;
	}

}
