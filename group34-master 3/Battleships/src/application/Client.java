package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import javafx.application.Platform;

/**
 * A simple client class implementation
 *
 */
public class Client {

	private Socket socket;

	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;

	private Main myGUI;
	private BoardScreenController boardScreenController;
	private endGameController endGameController;
	private LoginPageController loginPageController;
	private RegisterUserController registerUserController;

	/**
	 * Constructor for client
	 * 
	 * @param host IP address of host
	 * @param port port number that server is listening to
	 */
	public Client(String host, int port, Main myGUI) {
		this.myGUI = myGUI;

		try {

			// socket created on client side with BOTH host IP address and port
			socket = new Socket(host, port);

			System.out.println("Connected: " + socket);

			// wrap input/output streams
			fromServer = new ObjectInputStream(socket.getInputStream());
			toServer = new ObjectOutputStream(socket.getOutputStream());
			Thread listeningThread = new Thread(new Listening());
			listeningThread.start();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//method called by the loginPage controller to log the user in
	public void loginRequest(String username, String password) {

		System.out.println("login request called ");

		String[] loginRequest = { "LoginRequest", username, password };
		try {
			toServer.writeObject(loginRequest);
			System.out.println("Request sent to Server ");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// method called by the registerUser controller to allow users to register
	public void registerRequest(String username, String password) {
		System.out.println("register request called");
		String[] reqisterRequest = { "RegisterRequest", username, password };
		try {
			toServer.writeObject(reqisterRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// method called by the boardGrid controller to allow users to attack the
	// opponent.
	public void attackRequest(String locationCoord) {
		String[] attackRequest = { "AttackRequest", locationCoord, };
		try {
			toServer.writeObject(attackRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// method called by the boardGrid controller to store the shipLocations on the
	// server
	public void sendShipLocations(String[] shipLocationCells) {
		System.out.println("game start requested, attempting to send to Server");
		String[] gameStartRequest = shipLocationCells; // already added the identifier in the BoardScreenController
														// class
		// identifier = GameStartRequest
		try {
			toServer.writeObject(gameStartRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void surrenderRequest() {
		System.out.println("Surrender requested on client side");
		String[] surrenderRequest = {"SurrenderRequest", "true"};
		try {
			toServer.writeObject(surrenderRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBoardScreenController(BoardScreenController boardScreenController) {
		this.boardScreenController = boardScreenController;
	}
	public void setEndGameController(endGameController endGameController) {
		this.endGameController = endGameController;
	}
	public void setLoginPageController(LoginPageController loginPageController) {
		this.loginPageController = loginPageController;
	}
	public void setRegisterUserController(RegisterUserController registerUserController) {
		this.registerUserController = registerUserController;
	}

	private class Listening implements Runnable {

//		private Object responseType;

		@Override
		public void run() {

			System.out.println("Client listener thread running");
			while (true) {
				try {

					String[] response = (String[]) fromServer.readObject();
					System.out.println("Recieved response : " + Arrays.toString(response));
					String responseType = response[0];

					if (responseType.equals("LoginResponse")) {
						String loginSuccessful = response[1];
						System.out.println(Arrays.toString(response));
						System.out.println("Login response Received " + loginSuccessful);
						if (loginSuccessful.equals("true")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									myGUI.changeToGameScreen();
								}
							});
						}
						else if (loginSuccessful.equals("false")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									loginPageController.loginInvalid();
								}
							});
						}
					}

					else if (responseType.equals("RegisterResponse")) {
						String registerSuccessful = response[1];
						System.out.println("Register successful: " + registerSuccessful); // print whether register was
																							// successful
						if (registerSuccessful.equals("true")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									myGUI.changeToRegisterSuccessfulScreen();
								}
							});
						} else if (registerSuccessful.equals("false")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									registerUserController.userExistsAlready();
								}
							});
						}
					}

					else if (responseType.equals("GameStartResponse")) {
						String gameStartSuccessful = response[1];
						if (gameStartSuccessful.equals("true")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.startGameResponse(gameStartSuccessful);
								}
							});

							System.out.println("Both clients have successfully initialised their ships");
						}
					}

					// self attack response is to allow the current user to see where they have
					// attacked on the enemy grid.
					else if (responseType.equals("SelfAttackResponse")) {
						String target = response[1];
						String success = response[2];
						
						if (success.equals("hit")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.markOwnAttackHit(target);
								}
							});
						
							// updateGUI method to update user's enemy board button with a red background on
							// where they just hit
							// change EnemyGridPane button with id e.concat(target) to be red

						} else if (success.equals("miss")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.markOwnAttackMiss(target);
								}
							});
							// updateGUI method to update user's enemy board button with a black background
							// on where they just missed
							// change EnemyGridPane button with id e.concat(target) to be black
						}
						String shipSunk = response[3];
						System.out.println("ship sunk: " + shipSunk);
						if (shipSunk.equals("sunk")) {
							System.out.println("Ship Sunk!");// TODO GUI updates
							String shipName = response[4];

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.enemyshipSunk(shipName);
								}
							});
					
						}
					}

					// opponent attack response is to allow the current user to see where the enemy
					// has attacked on their self grid/if they hit.
					else if (responseType.equals("OpponentAttackResponse")) {
						String target = response[1];
						String success = response[2];
						if (success.equals("hit")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.markEnemyAttackHit(target);
								}
							});
							// TODO updateGUI method to update user's self board with red background on
							// where they just hit
							// change PlayerGridPane button with id b.concat(target) to be red
						} else if (success.equals("miss")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.markEnemyAttackMiss(target);
								}
							});

							// TODO updateGUI method to update user's self board with black background on
							// where they just missed
							// change PlayerGridPane button with id b.concat(targt) to be black
						}

						String shipSunk = response[3];
						System.out.println("ship sunk: " + shipSunk);
						if (shipSunk.equals("sunk")) {
							System.out.println("Ship Sunk!");// TODO GUI updates
							String shipName = response[4];

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									boardScreenController.playershipSunk(shipName);
								}
							});
						}
					}
					else if (responseType.equals("gameOver")) {
						String playerSuccess = response[1];
						if (playerSuccess.equals("win")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									myGUI.endGameWin();
								}
							});
						}
						else if (playerSuccess.equals("loss")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									myGUI.endGameLoss();
								}
							});
						}
					}

					
					else if (responseType.equals("NotYourTurn")) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								boardScreenController.notYourTurn();
							}
						});

				} else if (responseType.equals("ClientDisconnected")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							boardScreenController.playerDisconnected();
						}
					});
					
				}
					
					else {
						System.out.println("Invalid response type");
					}

				} catch (ClassNotFoundException | IOException e) {
					System.out.println("server death exception - please restart the server and try again.");
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							myGUI.serverDisconnectError();
//						}
//					});
					System.exit(1); //bad exit = 1, safe exit = 0
					
					
				}
			}
		}

	}


}