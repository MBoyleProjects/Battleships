package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gameLogic.Ship;

public class Server {

	private List<ObjectOutputStream> outputStreams;
	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private Ship[][] bothPlayersShips = new Ship[2][6]; // 2 = no. of players, 6 = number of ships each player gets.
	// bothPlayersShips[0] would give you all the ships belonging to player with
	// userID 0 and bothPlayersShips[1] would give you all ships belonging to ID1
	private int currentPlayersTurn = 0;

	/**
	 * Constructor
	 * 
	 * @param port port number
	 */
	public Server(int port) {
		DatabaseMethods.setupConnection();
		outputStreams = new ArrayList<>();

		try {
			// initialise a fixed size thread pool that can allow up to 10 concurrent
			// threads
			threadPool = Executors.newFixedThreadPool(2);
			// initialise ServerScocket obejct that will be used to accept new clients
			serverSocket = new ServerSocket(port);
			System.out.println("Creating server on port " + port);

			// an infinite loop to accept clients indefinitely
			while (true) {
				System.out.println("Waiting for client");
				// call .accept to wait for a new client to connect
				// a new socket object is returned by .accept when the
				// new client connects successuly
				Socket newClient = serverSocket.accept();
				// pass the socket created for the new client to a separate
				// ClientHandlerThread object and execute it on the threadpool
				threadPool.execute(new ClientHandlerThread(newClient));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//

	/**
	 * Inner class to represent a dedicated task that handles a particular client
	 */
	private class ClientHandlerThread implements Runnable {

		private Socket clientSocket;

		private ObjectInputStream fromClient;
		private ObjectOutputStream toClient;

		int playerNumber;

		/**
		 * Simple constructor that takes the public socket created by the
		 * ServerSocket.accpet method
		 * 
		 * @param clientSocket
		 */
		public ClientHandlerThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			System.out.println("Connected to client");
//			response = new String[2];

			// wrap input/output streams
			synchronized (outputStreams) {// we want to make sure that we dont get two clients connecting at the same
											// time, otherwise theyb would both get playernumber = 0
				this.playerNumber = outputStreams.size(); // we need to keep track of which streams belong to which
															// players.
			}
			try {
				toClient = new ObjectOutputStream(clientSocket.getOutputStream());
				outputStreams.add(toClient);
				fromClient = new ObjectInputStream(clientSocket.getInputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			while (true) {

				try {
					String[] request = (String[]) fromClient.readObject();// while(true)
					System.out.println("received " + Arrays.toString(request));

					String requestType = request[0];

					if (requestType.equals("LoginRequest")) { // request[1] = username, request[2] = password
						if (DatabaseMethods.loginUser(request[1], request[2]) == true) {
							String[] response = { "LoginResponse", "true" };
							System.out.println("Login Succeeded" + Arrays.toString(response));
							toClient.writeObject(response);
						} else {
							String[] response = { "LoginResponse", "false" };
							System.out.println("Login failed" + Arrays.toString(response));
							toClient.writeObject(response);
						}
					}

					else if (requestType.equals("RegisterRequest")) {
						if (DatabaseMethods.registerUser(request[1], request[2]) == true) { // &&
																							// DatabaseMethods.usernameTaken(request[1])
																							// == false
							String[] response = { "RegisterResponse", "true" };
							System.out.println("Registering succeeded" + Arrays.toString(response));
							toClient.writeObject(response);
						} else {
							String[] response = { "RegisterResponse", "false" };
							System.out.println("Registering failed" + Arrays.toString(response));
							toClient.writeObject(response);
						}
					}

					else if (requestType.equals("GameStartRequest")) {
						Ship destroyer = new Ship("Destroyer", 3, 1); // size 3
						Ship scout = new Ship("Scout", 2, 2); // size 2
						Ship submarine = new Ship("Submarine", 3, 3); // size 3
						Ship launchPad = new Ship("Launch Pad", 4, 4);// size 4
						Ship warship = new Ship("Warship", 2, 5); // size 2
						Ship paddleboat = new Ship("Paddle Boat", 1, 6);// size 1

						// 0 = gamestartrequest
						// 1,2,3 = destroyer coords
						// 4,5 = scout coords
						// 6,7,8 = submarine coords
						// 9,10,11,12 = launchPad coords
						// 13,14 = warship coords
						// 15 = paddleboat coords.

						ArrayList<String> dcoords = new ArrayList<String>();
						dcoords.add(request[1]);
						dcoords.add(request[2]);
						dcoords.add(request[3]);
						ArrayList<String> scoutcoords = new ArrayList<String>();
						scoutcoords.add(request[4]);
						scoutcoords.add(request[5]);
						ArrayList<String> subcoords = new ArrayList<String>();
						subcoords.add(request[6]);
						subcoords.add(request[7]);
						subcoords.add(request[8]);
						ArrayList<String> lcoords = new ArrayList<String>();
						lcoords.add(request[9]);
						lcoords.add(request[10]);
						lcoords.add(request[11]);
						lcoords.add(request[12]);
						ArrayList<String> wcoords = new ArrayList<String>();
						wcoords.add(request[13]);
						wcoords.add(request[14]);
						ArrayList<String> pcoords = new ArrayList<String>();
						pcoords.add(request[15]);

						destroyer.setLocationCells(dcoords);
						scout.setLocationCells(scoutcoords);
						submarine.setLocationCells(subcoords);
						launchPad.setLocationCells(lcoords);
						warship.setLocationCells(wcoords);
						paddleboat.setLocationCells(pcoords);

						Ship[] ship = { destroyer, scout, submarine, launchPad, warship, paddleboat };
						bothPlayersShips[playerNumber] = ship;
						System.out.println("These ships have been initialised for player: " + playerNumber);
						for (Ship shipx : bothPlayersShips[playerNumber]) {
							System.out.println(shipx + shipx.getLocationCells().toString());
						}

						if (bothPlayersShips[1 - playerNumber][0] != null) { // check to ensure both players have
																				// uploaded their ship coordinates
							String gameStartSuccessful = "true";
							String[] response = { "GameStartResponse", gameStartSuccessful };
							for (int i = 0; i < outputStreams.size(); i++) {
								outputStreams.get(i).writeObject(response); // if so, send successful response to both
																			// players
							}
						}

					}

					else if (requestType.equals("AttackRequest")) {
						String[] response1 = null; // back to self client
						String[] response2 = null; // to opponent
						
						
						if (playerNumber == currentPlayersTurn) {

							String locationCoord = request[1];
							String hitOrMiss = "miss";
							String sunkOrNot = "notSunk";
							//String gameOver = "notGameOver";
							String shipName = null;
							// where has just been attacked
							
							//this part checks for hits
							for (Ship ship : bothPlayersShips[1 - playerNumber]) { // go through the enemy's ships
								for (String coord : ship.getLocationCells()) { // for each cell's locationCells
									//System.out.println("checking request" + locationCoord + " against " + ship.getName() + coord);
									if (locationCoord.equals(coord)) { // if one matches the attackRequest
										 //hitShip = ship;
										// hitCoord = locationCoord.indexOf(coord);
										ship.getLocationCells().remove(coord); // remove it
										System.out.println("we have removed " + coord + " from " + ship.getName() + ship.getLocationCells());
										hitOrMiss = "hit";
										shipName = ship.getName();		
										break;
									}
								}
								for (Ship shipsunk : bothPlayersShips[1-playerNumber]) {
									if (shipsunk.getName().equals(shipName) && shipsunk.getLocationCells().isEmpty()) {
										sunkOrNot = "sunk";
									}
									else if (shipName == null){
										System.out.println("no ship hit this turn");
									}
									
								}
							}
							if (isGameOver() == true) {
								response1 = new String[] { "gameOver", "win"};
								response2 = new String[] { "gameOver", "loss"};
								
							} else if (isGameOver() == false) {

							response1 = new String[] { "SelfAttackResponse", locationCoord, hitOrMiss, sunkOrNot,
										shipName}; // what you've done
							response2 = new String[] { "OpponentAttackResponse", locationCoord, hitOrMiss, sunkOrNot,
									    shipName }; // what the enemy's done
							}
							
							System.out.println("Sending response 1" + Arrays.toString(response1));
							
							outputStreams.get(playerNumber).writeObject(response1); // return selfAttackResponse to
																					// client who sent it, should update
																					// gui for current client in client
																					// method
							outputStreams.get(1 - playerNumber).writeObject(response2); // send OpponentAttackRequest to
																						// opponent client, should
																						// update their gui board in
																						// client method.

							currentPlayersTurn = 1 - currentPlayersTurn; // change currentPlayers turn to be the other
																			// players. this should be the last line of
																				// code.
							
						} else {
							// TODO fire back response to tell player that it isn't their turn
							System.out.println("it is not your turn yet! Please wait until the other player has made a move.");
							String[] response3 = {"NotYourTurn", "true"};
								outputStreams.get(playerNumber).writeObject(response3);
						}
					}
						
						else if (requestType.equals("SurrenderRequest")) {
							if (request[1].equals("true")) {
							String[] response1 = new String[] { "gameOver", "win"};
							String[] response2 = new String[] { "gameOver", "loss"};
							
							outputStreams.get(1-playerNumber).writeObject(response1);
							outputStreams.get(playerNumber).writeObject(response2);
							}
						}
						
						
					 else {
						System.out.println("invalid request type");
					}

					toClient.flush();

					//catch if one client disconnects mid game
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("client disconnect Exception");
					String[] clientDisconnected = {"ClientDisconnected", "true"};
					
					try {
						outputStreams.get(1-playerNumber).writeObject(clientDisconnected);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					outputStreams.remove(playerNumber);
					return;
				}
			}
				
	}
	
			public boolean isGameOver() {
				for (Ship ship : bothPlayersShips[1-playerNumber]) {
					if (ship.getLocationCells().isEmpty() == false) {
						return false;
					}
				}
				return true;
			}
	}
	
	

	public static void main(String[] args) {
		int port = 8888;
		new Server(port);
	}
}

