package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginPageController {

	//loginPage GUI
	@FXML
	private TextField usernameBox;
	@FXML
	private PasswordField passwordBox;
	@FXML
	private Button LogInButton;
	@FXML
	private Button toRegisterPageButton;
	@FXML
	private Text errorText;
	@FXML
	private Text loginInvalidText;
	@FXML
	private Text loginSuccessful;

	private Client client; 
	private Main myGUI; 

	// method that takes in username and password and attempts to log in the user.
	@FXML
	protected void loginAttempt(MouseEvent event) {
		errorText.setVisible(false);
		loginSuccessful.setVisible(false);
		loginInvalidText.setVisible(false);

		if (!usernameBox.getText().isEmpty() && !passwordBox.getText().isEmpty()) { // check if user/pass boxes have
																					// text in them
			String username = usernameBox.getText();
			String password = passwordBox.getText();
			System.out.println("Login Attempted with Username: " + username + " and Password: " + password);

			this.client.loginRequest(username, password);
		} else {
			System.out.println("Invalid Login. Please try again, or Register if you are a new user!");
			errorText.setVisible(true);

		}
	}

	@FXML
	//this method opens the new Scene to allow registration
	protected void changeToRegisterPage(ActionEvent event) { //change name to change to register screen
	
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				myGUI.changeToRegisterScreen();
								}	
				}	
		);
	}
	
	public void loginInvalid() {
		errorText.setVisible(false);
		loginSuccessful.setVisible(false);
		loginInvalidText.setVisible(true);
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
