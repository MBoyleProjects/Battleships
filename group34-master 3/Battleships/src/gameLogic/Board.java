package gameLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Board {
	static int boardWidth;
	static int boardHeight;
	public static int[][] board;
	public static ArrayList<Ship> chosenShips;
	public static final ArrayList<Ship> allShips = new ArrayList<Ship>();
	private int numOfGuesses = 0;

	final static int EMPTY = -1;
	final static int MISS = 0;
	final static int HIT = 1;
	final static int SUNK = 2;

	static Ship destroyer = new Ship("Destroyer", 3, 1);
	static Ship scout = new Ship("Scout", 2, 2);
	static Ship submarine = new Ship("Submarine", 3, 3);
	static Ship launchPad = new Ship("Launch Pad", 4, 4);
	static Ship warship = new Ship("Warship", 2, 5);
	static Ship paddleboat = new Ship("Paddle Boat", 1, 6);

	public static void main(String[] args) {
		allShips.add(destroyer);
		allShips.add(scout);
		allShips.add(submarine);
		allShips.add(launchPad);
		allShips.add(warship);
		allShips.add(paddleboat);

		System.out.println("Welcome to BattleShips!\n");
		createBoard();
		showBoard(board);
		chooseShips();

		for (int i = 0; i < chosenShips.size(); i++) {
			placeShips(chosenShips.get(i));
		}
		// getUserGuess();
	}

	// first we have a method that creates the board and fills it with empty
	// ocean (-1)
	public static void createBoard() { // this method initialises/creates the
										// board game with user input size using
										// scanner
		System.out.println("What size board would you like to play on? Please enter the height:");

		Scanner scanner = new Scanner(System.in);

		try {
			int input = scanner.nextInt();
			if (input <= 26 && input > 0) {
				boardHeight = input;
				System.out.println("Please enter the width:");
			} else {
				System.out.println("Sorry, please enter height values between 0 and 26");
				createBoard();
			}

			int nextInput = scanner.nextInt();
			if (nextInput <= 20 && nextInput > 0) {
				boardWidth = nextInput;
			} else {
				System.out.println("Sorry, please enter width values between 0 and 20");
				createBoard();
			}

			board = new int[boardHeight][boardWidth]; // here, we initialise the
														// board variable from
														// earlier with our
														// scanner input values.
			for (int row = 0; row < boardHeight; row++) {
				for (int column = 0; column < boardWidth; column++) {
					board[row][column] = EMPTY; // this sets the value in each
												// coordinate to be empty.
				}
			}
			scanner.close();
		}

		catch (Exception e) {
			System.out.println(
					"Sorry, please enter an integer for both the height and width, maximum of 20! Try again below:\n");
			createBoard();
		}
	}

	// now we create a method that allows the player to see the board.
	public static void showBoard(int[][] board) {
		System.out.println("\nHere is your board!");

		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		System.out.println();

		for (int i = 0; i < boardWidth; i++) {
			System.out.print("\t" + (i + 1));
		}
		System.out.println();

		for (int row = 0; row < boardHeight; row++) { // for each row print out
														// the number of the
														// row+1
			System.out.print((alphabet[row]) + "");

			for (int column = 0; column < boardWidth; column++) { // for every
																	// column,
																	// print
																	// either ~,
																	// * or X
																	// depending
																	// on the
																	// game
																	// state.
				if (board[row][column] == EMPTY) {
					System.out.print("\t" + "~"); // un-targeted water
				} else if (board[row][column] == MISS) {
					System.out.print("\t" + "*"); // a missed shot taken here
				} else if (board[row][column] == HIT) {
					System.out.print("\t" + "#"); // ship hit but not sunk yet
				} else if (board[row][column] == SUNK) {
					System.out.println("\t" + "X"); // ship sunk
				}
			}
			System.out.println();
		}
	}

	// this method recommends the number of ships that you should place on the
	// board, given its dimensions
	public static int recommendedShipNo(int boardheight, int boardwidth) {
		int boardsize = boardheight * boardwidth;

		if (boardsize <= 15)
			return 1;
		if (boardsize > 15 && boardsize <= 40)
			return 2;
		if (boardsize > 40 && boardsize <= 60)
			return 3;
		if (boardsize > 60 && boardsize <= 80)
			return 4;
		if (boardsize > 80 && boardsize <= 100)
			return 5;
		else
			return 6;
	}

	// this method allows the user to choose from a selection of ships, which
	// ones they want to place on their board
	public static void chooseShips() {
		chosenShips = new ArrayList<Ship>();

		System.out.println("\nTime to place your ships! Here are all the available vessels:\n");
		for (int i = 0; i < allShips.size(); i++) {
			System.out.println(allShips.get(i).toString());
		}

		int numberOfShips = recommendedShipNo(boardHeight, boardWidth);
		System.out.println("\nConsidering your board size, we recommend you to place " + numberOfShips + " ships");
		// System.out.println("Please type in your selected ships by number,
		// separated by commas.");

		if (numberOfShips == 2) {
			chosenShips.add(warship);
			chosenShips.add(scout);
		}
		if (numberOfShips == 3) {
			chosenShips.add(warship);
			chosenShips.add(paddleboat);
			chosenShips.add(submarine);
		}
		if (numberOfShips == 4) {
			chosenShips.add(launchPad);
			chosenShips.add(paddleboat);
			chosenShips.add(scout);
			chosenShips.add(destroyer);
		}
		if (numberOfShips == 5) {
			chosenShips.add(destroyer);
			chosenShips.add(submarine);
			chosenShips.add(scout);
			chosenShips.add(warship);
			chosenShips.add(paddleboat);
		}
		if (numberOfShips == 6) {
			chosenShips.addAll(allShips);
		}

		// ArrayList<Ship> chosenships = new ArrayList<Ship>(); // int shipadds
		// = 0;

		// Scanner scanner = new Scanner(System.in);
		// while (scanner.hasNextInt()) {
		//
		// if (shipadds == numberOfShips) {
		// break;
		// }
		// int shipNo = scanner.nextInt();
		//
		// if (shipNo == 1){
		// chosenShips.add(destroyer);
		// shipadds++;
		// System.out.println("You have added the destroyer!");
		// }
		// else if (shipNo == 2){
		// chosenShips.add(scout);
		// shipadds++;
		// System.out.println("You have added the scout!");
		// }
		// else if (shipNo == 3){
		// chosenShips.add(submarine);
		// shipadds++;
		// System.out.println("You have added the submarine!");
		// }
		// else if (shipNo == 4){
		// chosenShips.add(launchPad);
		// shipadds++;
		// System.out.println("You have added the launchPad!");
		// }
		// else if (shipNo == 5){
		// chosenShips.add(warship);
		// shipadds++;
		// System.out.println("You have added the warship!");
		// }
		// else if (shipNo == 6) {
		// chosenShips.add(paddleboat);
		// shipadds++;
		// System.out.println("You have added the paddleboat!");
		// }
		// else {
		// System.out.println("Please enter a ship number from 1 to 6");
		// chooseShips();
		// }
		// }
		//
		System.out.println("Here are the ships we have chosen for you: ");
		for (int i = 0; i < chosenShips.size(); i++) {
			System.out.println(chosenShips.get(i).toString());
		}
	}

	// this method will place our ships on our 2d grid, ensuring they are placed
	// validly. it takes in the ship length
	public static ArrayList<String> placeShips(Ship ship) {

		String alphabet = "abcdefghijklmnopqrstuvwxyz"; // alphabet for our rows
		int gridSize = boardWidth * boardHeight;
		int[] grid = new int[gridSize];
		int shipCount = 0;
		int shipSize = ship.getLength();
		ArrayList<String> alphaCells = new ArrayList<String>();

		String[] alphacoords = new String[shipSize]; // holds 'f6' type coords,
														// so we have both the
														// row and the column
														// coordinates.
		String temp = null; // temporary String for concatenation
		int[] coords = new int[shipSize]; // current candidate coords, each ship
											// will have the same # of coords as
											// their length
		int attempts = 0; // current attempts counter
		boolean success = false; // flag = found a good location ?
		int location = 0; // current starting location

		shipCount++; // nth ship to place
		int incr = 1; // set horizontal increment
		if ((shipCount % 2) == 1) { // if odd ship (place vertically) this
									// portion deals with verticle/horizontal
									// placement
			incr = boardWidth; // set vertical increment
		}

		while (!success & attempts++ < 200) { // main search loop (32) //just to
												// make sure we dont get stuck
												// in an endless attempt loop
			location = (int) (Math.random() * gridSize); // get random starting
															// point
			// System.out.print(" try " + location);
			int x = 0; // nth position in ship to place (ie if all ships are
						// size 3, x should never go above 3
			success = true; // assume success
			while (success && x < shipSize) { // look for adjacent unused spots
				if (grid[location] == 0) { // if not already used
					coords[x++] = location; // save location
					location += incr; // try 'next' adjacent (remember that incr
										// = 7 if we're going vertically as we
										// have 7 columns so need next row)
					if (location >= gridSize) { // out of bounds - 'bottom'
						success = false; // failure
					}
					if (x > 0 & (location % boardWidth == 0)) { // out of bounds
																// - right edge
						success = false; // failure
					}
				} else { // found already used location
					// System.out.print(" used " + location);
					success = false; // failure
				}
			}
		} // end while

		int x = 0; // turn good location into alpha coords
		int row = 0;
		int column = 0;
		System.out.println("\n");
		while (x < shipSize) {
			grid[coords[x]] = 1; // mark master grid pts. as 'used'
			row = (int) (coords[x] / boardWidth); // get row value
			column = coords[x] % boardWidth; // get numeric column value
			temp = String.valueOf(alphabet.charAt(column)); // convert to alpha

			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;

			System.out.print("  coord " + x + " = " + alphaCells.get(x - 1));

		}

		return alphaCells;
	}

	public static String getUserGuess() { // this is just a basic user input
											// class
		String inputLine = null;

		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();

			if (inputLine.length() == 0) { // if no input return null
				return null;
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine.toLowerCase(); // make sure all input letters are in
										// lower case, eg G5 -> g5 to match the
										// alphacoords
	}

	private void checkUserGuess(String userGuess) {
		numOfGuesses++;
		String result = "miss";

		for (Ship ship : chosenShips) {
			result = ship.checkYourself(userGuess);
			if (result.equals("hit")) {
				break;
			}
			if (result.equals("kill")) {
				chosenShips.remove(ship);
				break;
			}
		}
		System.out.println(result);
	}

	private void finishGame() {
		System.out.println("All Ships are sunk! Congratulations");
		if (numOfGuesses <= 18) {
			System.out.println("It only took you " + numOfGuesses + " guesses");
			System.out.println("You got out before your options sank.");
		} else {
			System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
		}
	}
}
