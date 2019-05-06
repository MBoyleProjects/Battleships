package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import gameLogic.Ship;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BoardScreenController {

    @FXML
    private GridPane EnemyGridPane;
    @FXML    private Button e00;    @FXML    private Button e10;    @FXML    private Button e19;    @FXML    private Button e52;
    @FXML    private Button e20;    @FXML    private Button e30;    @FXML    private Button e40;    @FXML    private Button e50;
    @FXML    private Button e60;    @FXML    private Button e70;    @FXML    private Button e80;    @FXML    private Button e90;
    @FXML    private Button e02;    @FXML    private Button e03;    @FXML    private Button e05;    @FXML    private Button e09;
    @FXML    private Button e21;    @FXML    private Button e11;    @FXML    private Button e72;    @FXML    private Button e62;
    @FXML    private Button e42;    @FXML    private Button e32;    @FXML    private Button e22;    @FXML    private Button e12;
    @FXML    private Button e91;    @FXML    private Button e81;    @FXML    private Button e71;    @FXML    private Button e61;
    @FXML    private Button e51;    @FXML    private Button e41;    @FXML    private Button e31;    @FXML    private Button e93;
    @FXML    private Button e83;    @FXML    private Button e73;    @FXML    private Button e63;    @FXML    private Button e43;
    @FXML    private Button e33;    @FXML    private Button e23;    @FXML    private Button e13;    @FXML    private Button e92;
    @FXML    private Button e82;    @FXML    private Button e98;    @FXML    private Button e88;    @FXML    private Button e78;
    @FXML    private Button e68;    @FXML    private Button e48;    @FXML    private Button e38;    @FXML    private Button e28;
    @FXML    private Button e18;    @FXML    private Button e08;    @FXML    private Button e97;    @FXML    private Button e87;
    @FXML    private Button e77;    @FXML    private Button e67;    @FXML    private Button e47;    @FXML    private Button e37;
    @FXML    private Button e27;    @FXML    private Button e17;    @FXML    private Button e07;    @FXML    private Button e96;
    @FXML    private Button e86;    @FXML    private Button e76;    @FXML    private Button e66;    @FXML    private Button e45;
    @FXML    private Button e35;    @FXML    private Button e25;    @FXML    private Button e15;    @FXML    private Button e94;
    @FXML    private Button e84;    @FXML    private Button e64;    @FXML    private Button e74;    @FXML    private Button e44;
    @FXML    private Button e34;    @FXML    private Button e24;    @FXML    private Button e14;    @FXML    private Button e04;
    @FXML    private Button e46;    @FXML    private Button e36;    @FXML    private Button e26;    @FXML    private Button e99;
    @FXML    private Button e16;    @FXML    private Button e06;    @FXML    private Button e95;    @FXML    private Button e85;
    @FXML    private Button e75;    @FXML    private Button e65;    @FXML    private Button e89;    @FXML    private Button e79;
    @FXML    private Button e69;    @FXML    private Button e49;    @FXML    private Button e39;    @FXML    private Button e29;
    @FXML	 private Button e58;    @FXML 	 private Button e57;    @FXML    private Button e56;    @FXML 	 private Button e55;
    @FXML	 private Button e54;	@FXML 	 private Button e53;	@FXML 	 private Button e59;	@FXML	 private Button e01;
    
    @FXML
    private GridPane PlayerGridPane;
    @FXML    private Button b00;    @FXML    private Button b10;    @FXML    private Button b19;    @FXML    private Button b52;
    @FXML    private Button b20;    @FXML    private Button b30;    @FXML    private Button b40;    @FXML    private Button b50;
    @FXML    private Button b60;    @FXML    private Button b70;    @FXML    private Button b80;    @FXML    private Button b90;
    @FXML    private Button b02;    @FXML    private Button b03;    @FXML    private Button b05;    @FXML    private Button b09;
    @FXML    private Button b21;    @FXML    private Button b11;    @FXML    private Button b72;    @FXML    private Button b62;
    @FXML    private Button b42;    @FXML    private Button b32;    @FXML    private Button b22;    @FXML    private Button b12;
    @FXML    private Button b91;    @FXML    private Button b81;    @FXML    private Button b71;    @FXML    private Button b61;
    @FXML    private Button b51;    @FXML    private Button b41;    @FXML    private Button b31;    @FXML    private Button b93;
    @FXML    private Button b83;    @FXML    private Button b73;    @FXML    private Button b63;    @FXML    private Button b43;
    @FXML    private Button b33;    @FXML    private Button b23;    @FXML    private Button b13;    @FXML    private Button b92;
    @FXML    private Button b82;    @FXML    private Button b98;    @FXML    private Button b88;    @FXML    private Button b78;
    @FXML    private Button b68;    @FXML    private Button b48;    @FXML    private Button b38;    @FXML    private Button b28;
    @FXML    private Button b18;    @FXML    private Button b08;    @FXML    private Button b97;    @FXML    private Button b87;
    @FXML    private Button b77;    @FXML    private Button b67;    @FXML    private Button b47;    @FXML    private Button b37;
    @FXML    private Button b27;    @FXML    private Button b17;    @FXML    private Button b07;    @FXML    private Button b96;
    @FXML    private Button b86;    @FXML    private Button b76;    @FXML    private Button b66;    @FXML    private Button b45;
    @FXML    private Button b35;    @FXML    private Button b25;    @FXML    private Button b15;    @FXML    private Button b94;
    @FXML    private Button b84;    @FXML    private Button b64;    @FXML    private Button b74;    @FXML    private Button b44;
    @FXML    private Button b34;    @FXML    private Button b24;    @FXML    private Button b14;    @FXML    private Button b04;
    @FXML    private Button b46;    @FXML    private Button b36;    @FXML    private Button b26;    @FXML    private Button b99;
    @FXML    private Button b16;    @FXML    private Button b06;    @FXML    private Button b95;    @FXML    private Button b85;
    @FXML    private Button b75;    @FXML    private Button b65;    @FXML    private Button b89;    @FXML    private Button b79;
    @FXML    private Button b69;    @FXML    private Button b49;    @FXML    private Button b39;    @FXML    private Button b29;
    @FXML	 private Button b58;    @FXML 	 private Button b57;    @FXML    private Button b56;    @FXML 	 private Button b55;
    @FXML	 private Button b54;    @FXML	 private Button b53;	@FXML 	 private Button b59;	@FXML 	 private Button b01;
   
    @FXML    private Button PlaceShipsButton;
    @FXML    private Button startGameButton;
    @FXML    private Button surrenderButton;
    
    @FXML	 private TextArea gameMoveText;
        
	private Client client;
	private Main myGUI;
	
	Ship destroyer;
	Ship scout;
	Ship submarine;
	Ship launchPad;
	Ship warship;
	Ship paddleboat;
	
	private static int boardWidth = 10;
	private static int gridSize = 100;
	private  int[] grid = new int[gridSize];
	
	 Color black = Color.BLACK;
	 Color red = Color.RED;


    @FXML
    //this method will eventually also have the code to check whether the attack was a hit/miss/sink, and change colour of button if so.
    public void getAttackCoordinates(ActionEvent event) {
		Button button = (Button) event.getSource();
		String locationcoord = button.getText();
		String xcoord = button.getText().substring(0, 1);
		String ycoord = button.getText().substring(button.getText().length() - 1);
		

		System.out.println("You have attacked the coordinates " + xcoord + "," + ycoord);
		System.out.println("Sending locationCoordinate " + locationcoord + " to the client");
		this.client.attackRequest(locationcoord);
		

    }
    
    @FXML
    public void startGame(ActionEvent event) {
    	surrenderButton.setDisable(false);
    	gameMoveText.setStyle("-fx-text-inner-color: black;");
    	System.out.println("This startgame button works at least");
    	gameMoveText.appendText("\n You have requested a game start! Please wait for another player to connect!");
    	if (destroyer.getLocationCells() != null) {
    		PlaceShipsButton.setDisable(true);
    		
    		ArrayList<String> dcoords = destroyer.getLocationCells();
    		ArrayList<String> scoutcoords = scout.getLocationCells();
    		ArrayList<String> subcoords = submarine.getLocationCells();
    		ArrayList<String> lcoords = launchPad.getLocationCells();
    		ArrayList<String> wcoords = warship.getLocationCells();
    		ArrayList<String> pcoords = paddleboat.getLocationCells();
    		
    		String[] allCoords = {"GameStartRequest",
    							   dcoords.get(0), dcoords.get(1), dcoords.get(2), //add the destroyer coordinates
    							   scoutcoords.get(0), scoutcoords.get(1), //add the scout coordinates
    							   subcoords.get(0), subcoords.get(1), subcoords.get(2), //add the submarine coordinates
    							   lcoords.get(0), lcoords.get(1), lcoords.get(2), lcoords.get(3), //add the launchPad coordinates
    							   wcoords.get(0), wcoords.get(1), //add the warship coordinates
    							   pcoords.get(0) //add the paddleboat coordinates.
    							   };
    		System.out.println(allCoords.toString());

    		this.client.sendShipLocations(allCoords);
    		System.out.println("Your ships have been sent to the server to be stored!");
    		gameMoveText.appendText("Thanks for placing your ships! Waiting for opponent to place...\n");
    		startGameButton.setDisable(true);
    		
    	}
    	else {
    		System.out.println("The ships have not yet been given locationCoords");
    		gameMoveText.setText("Please place your ships before starting the game!\n");
    	}
    }
    
    @FXML
    public void surrender(ActionEvent event) {
    	gameMoveText.setStyle("-fx-text-inner-color: red;");	
    	gameMoveText.appendText("\n You have surrendered! The enemy wins!");
    	this.client.surrenderRequest();
    		
    	}
    
    
    @FXML
    public void randomiseShipPlacement(ActionEvent event) {
    	gameMoveText.setStyle("-fx-text-inner-color: black;");
    	gameMoveText.appendText("\n You have randomised your ships! Press 'start game' when you're ready to play");
		Arrays.fill(grid, 0); // reset the grid to allow ships to be placed
								// anywhere again. also means that the opponent
								// can place ships at same place.
		ObservableList<Node> nodes = PlayerGridPane.getChildren();
		for (Node colouredButtons : nodes) {
			colouredButtons.setStyle(null); //clear all current markings on the grid.
		}
		
		String bordercolour = "#7acc61"; //set an initial border colour

		System.out.println("The randomiseShipPlacement button works");

		destroyer = new Ship("Destroyer", 3, 1); //size 3
		scout = new Ship("Scout", 2, 2);         //size 2
		submarine = new Ship("Submarine", 3, 3); //size 3
		launchPad = new Ship("Launch Pad", 4, 4);//size 4
		warship = new Ship("Warship", 2, 5);     //size 2
		paddleboat = new Ship("Paddle Boat", 1, 6);//size 1

		Ship[] ships = { destroyer, scout, submarine, launchPad, warship, paddleboat }; //put all the ships into an array 
		placeShip(ships); //place all the ships, thus setting their locationCells.
		
		for (Ship ship : ships) {
			System.out.println(ship.toString() + ship.getLocationCells()); //basic print out of where all your ships are.
		}
		
		for (Ship ship : ships) {
			for (int i = 0; i < ship.getLength(); i++) {
		
		String shipcoord = ship.getLocationCells().get(i);
		
		String concat = "b".concat(shipcoord); //concatenate the locationCell with the letter 'b' to match the button Id
		System.out.println("we're searching the buttons for " + concat); 
		
			if (ship.equals(destroyer)) {
				bordercolour = "#7acc61"; //green
			} else if (ship.equals(launchPad)) {
				bordercolour = "#f44292"; //pink
			} else if (ship.equals(paddleboat)){
				bordercolour = "#41bbf4"; //blue
			} else if (ship.equals(scout)) {
				bordercolour = "#f4d341"; //yellow
			} else if (ship.equals(submarine)) {
				bordercolour = "#f46741"; //orange 
			} else if (ship.equals(warship)) {
				bordercolour = "#f44141";// red        //here we make the outlines of the user ship placements different colours
			}
				
		ObservableList<Node> buttons = PlayerGridPane.getChildren(); //get all the PlayerGridPane buttons and put them in an array
		for (int j = 0; j < buttons.size(); j++) {    //for each of buttons, get the id, and check it against the locationCell we're looking for
		
			String id = buttons.get(j).getId();
			if (id == null) {
				System.out.println("THIS BUTTON ISNT INITIALISED");
				System.out.println(buttons.get(j));   //this is just a check to see whether any buttons haven't been id'd, used during testing.
			}
				if (id.equals(concat)) {
					System.out.println("Found the matching coordinate!");
					buttons.get(j).setStyle("-fx-border-color: " + bordercolour + "; -fx-border-width: 4px;"); //give the button a border
					break;
			} else {
				//System.out.println("Not this button, try next one");
		}
		//System.out.println(buttons.get(j).getId()); //for testing purposes, print out all the tried buttons during the search for the locationCell.
		}
		}
	}
    }
    
    public void startGameResponse(String success) {
    	gameMoveText.setStyle("-fx-text-inner-color: black;");
    	if(success.equals("false")) {
    		System.out.println("Please wait for the other player to join!");
    	} else {
    		System.out.println("Game started!");
    		gameMoveText.setText("Both Players are connected! Let's play Battleships! \n");
    	}
    }
	//mark attacks that you have made on the enemy grid as hits
    
	  public void markOwnAttackHit(String attackcoord) {
		  gameMoveText.setStyle("-fx-text-inner-color: black;");
		  System.out.println("Marking your attack as a hit on the enemy board: " + attackcoord);
		  String text = "You successfully hit one of your enemy's ships at the coordinates: " + attackcoord.substring(0, 1) + "," + attackcoord.substring(1);
		  gameMoveText.appendText(text + "\n");
		  
		  ObservableList<Node> nodes =  EnemyGridPane.getChildren(); //get all gridpane children
		  ArrayList<Button> buttons = new ArrayList<Button>();      //create arrayList for button children
		  
		  for (Node node : nodes) { //for each node in gridpane children
			  if (node instanceof Button) { //if the node is a button, add it to the button arrayList.
				  buttons.add((Button) node);
		  	}
		  }
		  String buttonCoord = "e".concat(attackcoord); //create the name of the button using the input attackcoord
		  
		  for (Button button : buttons) { //for each of the buttons in the grid, 
			  if (button.getId().equals(buttonCoord)) { //if the button id matches, then you've found the correct button.
				 Border border = button.getBorder();
				  button.setStyle("-fx-base: #ee2211;"); //set button disabled and colour to be red
				  button.setDisable(true);
				  button.setBorder(border);
			  }
		  }
	  }
	  //mark attacks that you have made on the enemy grid as miss
    
	  public void markOwnAttackMiss(String attackcoord) {
		  gameMoveText.setStyle("-fx-text-inner-color: black;");
		  System.out.println("Marking your attack as a miss on the enemy board: " + attackcoord);
		  String text = "You missed the enemy's ships at the coordinates: " + attackcoord.substring(0,1) + "," + attackcoord.substring(1);
		  gameMoveText.appendText(text + "\n");
		  ObservableList<Node> nodes = EnemyGridPane.getChildren();
		  ArrayList<Button> buttons = new ArrayList<Button>();
		  	for (Node node : nodes) {
		  		if (node instanceof Button) {
		  			buttons.add((Button) node);
		  		}
		  	}
		  String buttonCoord = "e".concat(attackcoord);
		  for (Button button : buttons) {
			  if(button.getId().equals(buttonCoord)) {
				  button.setDisable(true);
				  button.setStyle("-fx-background-colour: black;"); //set button disabled and colour to be black
				  button.setStyle("fx-border-colour: black;");
			  }
		  }
	  }
	  //mark attacks that the enemy has made as hits on your grid
	  
	  public void markEnemyAttackHit(String attackcoord) {
		  gameMoveText.setStyle("-fx-text-inner-color: black;");
		  System.out.println("Marking enemy attack as a hit on your self board: " + attackcoord);
		  String text = "The enemy successfully hit one of your ships at the coordinates: " + attackcoord.substring(0,1) + "," + attackcoord.substring(1);
		  gameMoveText.appendText(text + "\n");
		  ObservableList<Node> nodes = PlayerGridPane.getChildren();
		  ArrayList<Button> buttons = new ArrayList<Button>();
		  		for (Node node: nodes) {
		  			if (node instanceof Button) {
		  				buttons.add((Button) node);
		  			}
		  		}
		  String buttonCoord = "b".concat(attackcoord);
		  
		  for(Button button : buttons) {
			  if(button.getId().equals(buttonCoord)) {
				  Border border = button.getBorder();
				  button.setDisable(true);
				  button.setStyle("-fx-base: #ee2211;"); //set internal colour to red and button disabled
				  button.setBorder(border);
				  
			  }
		  }
	  }
	  //mark attacks that the enemy has made as misses on your grids
	  
	  public void markEnemyAttackMiss(String attackcoord) {
		  gameMoveText.setStyle("-fx-text-inner-color: black;");
		  System.out.println("Marking enemy attack as a miss on your self board: " + attackcoord);
		  String text = "The enemy missed your ships at the coordinates: " + attackcoord.substring(0,1) + "," + attackcoord.substring(1);
		  gameMoveText.appendText(text + "\n");
		  ObservableList<Node> nodes = PlayerGridPane.getChildren();
		  ArrayList<Button> buttons = new ArrayList<Button>();
		  for (Node node : nodes) {
			  if (node instanceof Button) {
				  buttons.add((Button) node);
			  }
		  }
		  String buttonCoord = "b".concat(attackcoord);
		  
		  for(Button button : buttons) {
			  if(button.getId().equals(buttonCoord)) {
				  button.setStyle("-fx-background-colour: black;"); //set internal colour to red and button disabled
				  button.setDisable(true);
				  button.setStyle("fx-border-colour: black;");
				
			  }
		  }
		  
	  }
    
	  
	  public void notYourTurn() {
		  gameMoveText.appendText("\n It is not your turn! Please wait for your opponent's move first!\n");
		  gameMoveText.setStyle("-fx-text-inner-color: red;");
	  }
	  
	  public void enemyshipSunk(String shipName) {
		  gameMoveText.appendText("You have sunk the enemy's " + shipName);
	  }
	  
	  public void playershipSunk(String shipName) {
		  gameMoveText.appendText("The enemy has sunk your " + shipName);
	  }
	  
	  public void playerDisconnected() {
		  myGUI.endGameWin();
	  }
    //this is the method to place the ships so that they do not overlap, and that they fit on the board without overhang. it also sets the locationCells for each ship.
	  public  void placeShip(Ship[] ships) {	
		  for (Ship ship:ships) {
				ArrayList<String> shipPositions = new ArrayList<String>(); //arrayList of all the string coordinates. will be initialised to locationCells
			int shipSize = ship.getLength();
			
			String temp = null; //temporary string for concatenation.
			int[] coords = new int[shipSize]; //current candidate coords.
			
			int attempts = 0; //current attempts counter
			boolean success = false; // flag = found a good location?
			int location = 0; //a starting location
			
			int incr = 1;
			Random rnd = new Random();
			int randomNum = rnd.nextInt((10 - 1) + 1) + 1;
			if (randomNum % 2 == 1) {
				incr = boardWidth; //use a random number generator to decide whether to place ships vertically or horizontally. if odd, vertical.
			}
			
			while(success == false && attempts++ < 200) {
				location = (int) (Math.random() * gridSize); //get a random location on the grid
				int x = 0; //nth position of the ship to place
				success = true; //assume success
				
			while(success && x < shipSize) { //while success = true and we still have ship length positions to place
				if(grid[location] == 0) { //if we havent used this location already (all values initialised to zero)
					coords[x++] = location;
					location += incr; //try next adjacent location (remember that incr = 10 if we're going vertically)
					
				if (location >= gridSize) {
					success = false; //we cant have a location thats not on the grid. adjacent values that hang off the grid not considered.
				}
				if (x > 0 && (location % boardWidth == 0)) { //out of bounds right edge. run while loop again.
					success = false;
				}
			} else { //location found is already in use, thus false. run while loop again.
				success = false;
			}
		}
		}
			int x = 0;  //now we turn the good locations into actual coodinates. remember x is which position of the ships's length we have coords for.
			int row = 0;
			int column = 0;
			
			while (x < shipSize) {
				grid[coords[x]] = 1; //mark these positions on the master grid as being used, ie they arent 0 any more thus get caught on if(grid[location] == 0)
				row = (int) (coords[x] / boardWidth); //get row value
				column = (int) (coords[x] % boardWidth); //get column value
				temp = Integer.toString(column).concat(Integer.toString(row)); //make the row and column into a suitable locationCell coordinate
				
				shipPositions.add(temp); //the current ship's locationCells stored in here
				x++; //x refers to the current position in the ship's length we're at
				}
			ship.setLocationCells(shipPositions); //set the location cells for the ship.
		  }
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

