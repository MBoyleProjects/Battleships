//package pastProgress;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//public class BasicLoginBox extends Application{
//	
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		
//		GridPane grid = new GridPane();
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(25, 25, 25, 25));
//	
//		
//		TextField usernamebox = new TextField();
//		usernamebox.setPromptText("Username");
//		usernamebox.setAlignment(Pos.TOP_LEFT);
//		
//		TextField passwordbox = new TextField();
//		passwordbox.setPromptText("Password");
//		passwordbox.setAlignment(Pos.CENTER_LEFT);
//		
//		
//		Text scenetitle = new Text("Welcome to BattleShips!");
//		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//		grid.add(scenetitle, 0, 0, 2, 1); //add to column 0, row 0, but span from columns 0-2 and span rows 0-1
//		
//		Label instructions = new Label("Please enter your credentials");
//		grid.add(instructions, 0, 1, 2, 1);
//		
//		Label userName = new Label("User Name:");
//		grid.add(userName, 0, 2); //add to column 0, row 1
//
//		TextField userTextField = new TextField();
//		userTextField.setPromptText("Username"); //add that translucent text prompt in the textbox
//		userTextField.setFocusTraversable(false); //allows prompt text to be seen until box is clicked on
//		grid.add(userTextField, 1, 2);
//
//		Label pw = new Label("Password:");
//		grid.add(pw, 0, 3);
//
//		PasswordField pwBox = new PasswordField();
//		pwBox.setPromptText("Password");
//		pwBox.setFocusTraversable(false); //allows prompt text to be seen until box is clicked on
//		grid.add(pwBox, 1, 3);
//		
//		Button signIn = new Button("Sign in");
//		Button register = new Button("Register (new Players)");
//		HBox hbBtn = new HBox(10);                    //hBox adds its children in a nice horizontal line
//		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//		hbBtn.getChildren().add(signIn);
//		hbBtn.getChildren().add(register);
//		grid.add(hbBtn, 1,4);
//		
//		
//		grid.setGridLinesVisible(false);
//		 
//		
//		Scene scene = new Scene(grid, 400, 200);
//		stage.setTitle("Login Screen");
//		stage.setScene(scene);
//		stage.show();
//	}
//	public static void main(String[] args) {
//		launch(args);
//		
//		
//		
//	}
//
//}
