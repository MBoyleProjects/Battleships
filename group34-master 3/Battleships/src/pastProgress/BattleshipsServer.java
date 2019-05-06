//package pastProgress;
////package com.battleships;
//
//
//import javax.xml.crypto.Data;
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//
//public class BattleshipsServer extends DatabaseMethods {
//        /**
//         * A simple (multi-threaded) server implementation
//         * @author Miles
//         */
//
//        private ServerSocket serverSocket;
//        private Scanner in;
//        private ExecutorService runGame;
//        private String [] Players;
//        private Lock gameLock;
//        private Condition otherPlayerConnected;
//        private Condition otherPlayerTurn;
//        private Player;
//
//        /**
//         * Constructor
//         * @param port
//         * port number
//         */
//
//        public BattleshipsServer (int port) {
//
//            try {
//
//                // initialise a fixed size thread pool that can allow up to 2 concurrent threads (2 players)
//
//                runGame = Executors.newFixedThreadPool(2); // allows up to 2 players to join using executer service
//
//                gameLock = new ReentrantLock(); // locks the game so players have to take turns, stops one thread and starts the other
//
//                otherPlayerConnected = gameLock.newCondition(); // condition variable to confirm that new player has successfully connected to the game
//
//                otherPlayerTurn = gameLock.newCondition(); // locks the game again using a conditional variable to switch player turns
//
//                Players = new String[2]; // array list to contain two players
//
//                // initialise ServerSocket object that will be used to accept new clients
//
//                serverSocket = new ServerSocket(port);
//
//                System.out.println("Creating server on port " + port);
//
//                // an infinite loop to accept clients indefinitely
//                while (true) {
//
//                    System.out.println("Waiting for client");
//                    // call .accept to wait for a new client to connect
//                    // a new socket object is returned by .accept when the
//                    // new client connects successfully
//                    Socket newClient = serverSocket.accept();
//
//                    // pass the socket created for the new client to a separate
//                    // Player object and execute it on the threadpool
//
//                    runGame.execute(new Player(newClient));
//                }
//            }
//
//            catch (IOException e) {
//
//                e.printStackTrace();
//            }
//
//        }
//
//        /**
//         * Inner class to represent a dedicated task that handles a particular client
//         * @author Miles
//         */
//
//        private abstract class Player extends com.battleships.Player implements Runnable {
//
//            private Socket clientSocket; // connection to client
//            private Scanner in; // receive input from client
//            private ObjectInputStream fromClient;
//            private ObjectOutputStream toClient;
//            private int playerNumber; // stores player number as int
//
//            /**
//             * Simple constructor that takes the socket created by the ServerSocket.accept
//             * method
//             * @param clientSocket;
//             */
//
//            public Player(Socket  clientSocket) {
//
//                this.clientSocket = clientSocket;
//
//
//
//            }
//
//
//            public void userLogIn ()  throws IOException {
//
//                BufferedReader usernameInput = new BufferedReader(new InputStreamReader(System.in)); // stores username input
//                String usernameInputObj = usernameInput.readLine(); // stores as readable object to be verified by the server
//                BufferedReader passwordInput = new BufferedReader(new InputStreamReader(System.in)); // stores password input
//                String passwordInputObj = passwordInput.readLine(); // stores as readable object to be verified by the server
//
//                if (!(usernameInputObj == null && passwordInputObj == null)) { // once the user has typed in username & password
//
//                    do {
//                        // check to see if user profile is in DB
//
//                        boolean matchSuccess = false; // boolean 'found' value set to false
//
//                        if
//
//                        userLogIn(DatabaseMethods.loginUser(usernameInputObj.matches(, passwordInputObj));
//
//
//                    }
//                }
//
//
//            }
//            @Override
//
//
//            public void play() {
//
//                this.in = new Scanner(System.in);
//                System.out.println("Connected to client");
//
//                try {
//
//
//                    // verification methods to compare against database entries
//                     fromClient = new ObjectInputStream(clientSocket.getInputStream());
//
//                     toClient = new ObjectOutputStream(clientSocket.getOutputStream());
//
//                    String[] toServer = (String[]) fromClient.readObject(); // instantiate arrayList to hold client/ user selection
//
//                    if (!(toServer == null)) {
//
//                        String requestID = toServer[0]; // Identifier for the server to recognise what type of
//                        // request has been sent from the client
//
//                        String xCoord = toServer[1]; //creating serverside object of user input
//                        String yCoord = toServer[2]; //creating serverside object of user input Y co-ord
//
//                        if (requestID.equals("attackrequest".toLowerCase())) {
//
//
//                        }
//                    }
//
//                    System.out.println("Type your reply to the client");
//                    String reply = in.nextLine();
//                    toClient.writeObject(reply); // send object to client
//
//
//                } catch (IOException | ClassNotFoundException e) {
//                    System.out.println("Exception");
//                    e.printStackTrace();
//
//                } finally // this will need to be changed after the game logic is fully built
//
//                {
//                    // ensure client socket closes regardless of how code in
//                    // try block terminates
//
//                    try {
//
//                        clientSocket.close();
//
//                    } catch (IOException e) {
//                    }
//                }
//
//            }
//
//        }
//
//
//            public static void main(String[] args) {
//                int port = 8834;
//                new BattleshipsServer(port);
//            }
//
//        }
//
//
//
//
//
//
//
