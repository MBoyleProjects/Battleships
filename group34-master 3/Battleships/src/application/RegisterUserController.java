package application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterUserController {

	@FXML
	private TextField RegUsernameBox;
	@FXML
	private PasswordField RegPasswordBox;
	@FXML
	private PasswordField confirmPasswordBox;
	@FXML
	private Text passwordMatchText;
	@FXML
	private Text existingUserText;
	@FXML
	private Button registerButton;
	@FXML
	private Button backButton;

	private Client client;
	private Main myGUI; 

	@FXML
	// this method registers the new user
	protected void registerUser(ActionEvent event) {
		existingUserText.setVisible(false);
		passwordMatchText.setVisible(false);
	
		if (!RegUsernameBox.getText().isEmpty() && !RegPasswordBox.getText().isEmpty()
				&& !confirmPasswordBox.getText().isEmpty()) { // check if user/pass boxes have

			String username = RegUsernameBox.getText();
			String password = RegPasswordBox.getText();
			String confirmPassword = confirmPasswordBox.getText();

			if (password.equals(confirmPassword))  { //make sure that the passwords match and username isnt taken
				this.client.registerRequest(username, password);
				System.out.println("Register User Attempted with Username: " + username + " and Password: " + password
						+ " and confirmPassword: " + confirmPassword);
			} else {
				passwordMatchText.setVisible(true);
				//usernameTakenText.setVisible(false);
				System.out.println("Your passwords do not match!");

			}
		}
		else {
			System.out.println("Please make sure the boxes are not empty!");
		}
	}

	@FXML
	// this method takes the user back to the LoginPage when they are on the
	// registerUser page
	protected void backToLogin(ActionEvent event) {
		
			Platform.runLater(new Runnable() { //we do this because javafx needs to run on the javafx thread, else it wont work.
			@Override
			public void run() {
				myGUI.changeToLoginPage();
			}
				
			}	
		);
	}
			
	public void userExistsAlready() {
		existingUserText.setVisible(true);
		passwordMatchText.setVisible(false);
		
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
