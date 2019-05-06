package application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class endGameController {

  @FXML
 private Button playAgainButton;

  @FXML
 private Button exitButton;
  
 private Client client;
 private Main myGUI; 




	@FXML
	public void backToGameScreen(ActionEvent event) {
		Platform.runLater(new Runnable() { //we do this because javafx needs to run on the javafx thread, else it wont work.
			@Override
			public void run() {
				myGUI.changeToGameScreen();
			}
				
			}	
		);
	
	}
	
	@FXML
	public void quitGame(ActionEvent event) {
		   Stage stage = (Stage) exitButton.getScene().getWindow();
		    stage.close();
		    System.exit(0);
		
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
