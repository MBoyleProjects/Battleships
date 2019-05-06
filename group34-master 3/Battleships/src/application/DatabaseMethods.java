package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Em23 A class containing methods used to access the database.
 */

public class DatabaseMethods {

	private static Connection connection;

	/**
	 * A methods that connects the game database to the server.
	 */
	public static void setupConnection() {
		try (FileInputStream input = new FileInputStream(new File("db.properties"))) {// reads in from the db file
			Properties props = new Properties();
			props.load(input);

			String dbUrl = props.getProperty("URL");
			String dbUser = props.getProperty("user");
			String dbPassword = props.getProperty("password");

			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Login method.
	 * 
	 * @param username the user name the user uses to login
	 * @param password the password the user uses to login
	 * @return a boolean value, true if the user name and password are correct,
	 *         otherwise false.
	 */

	public static synchronized boolean loginUser(String username, String password) {

		try {
			String userCheck = "";
			String passCheck = "";

			PreparedStatement selectStatement = connection
					.prepareStatement("SELECT username, password FROM users WHERE username=? AND password=?");
			selectStatement.setString(1, username);
			selectStatement.setString(2, password);
			// SQL query that will select the username and password from the databse

			try (ResultSet resultSet = selectStatement.executeQuery()) {// execute the select query
				while (resultSet.next()) {// if we have a result set (it means that we can call the .next method)
					userCheck = resultSet.getString("username");
					passCheck = resultSet.getString("password");
					
					if (userCheck.equals(username) && passCheck.equals(password)) {
						return true;
						
					} else {
					return false;
				}
			}
		} catch (SQLException e) {
				System.out.println("Error here1");
			}
		
		} catch (SQLException e) {
			System.out.println("Error here2");
		}
		return false;
	}
		
			
			/**
	 * A method that will register a user
	 * 
	 * @param username of the user
	 * @param password of the user
	 * @return true if the user has registered
	 */
	public static synchronized boolean registerUser(String username, String password) {
		if (loginUser(username, password) == false) {// if the loginUser method returns false(i.e. values not present in the database
				
			try {
			PreparedStatement insertStatement = connection.
					prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
				// insert the value in the database,
				insertStatement.setString(1, username);
				insertStatement.setString(2, password);
				insertStatement.executeUpdate();
				
				System.out.println("User succssesfully registered");
				return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		System.out.println("The user already exists! Please log in");
		return false;
	}
	
	public static synchronized boolean usernameTaken(String username) {

		try {
			String userCheck = "";
			PreparedStatement selectStatement = connection.prepareStatement("SELECT username FROM users WHERE username=?");
			selectStatement.setString(1, username);
			// SQL query that will select the username and password from the databse

			try (ResultSet resultSet = selectStatement.executeQuery()) {// execute the select query
				while (resultSet.next()) {// if we have a result set (it means that we can call the .next method)
					userCheck = resultSet.getString("username");
					
			if (userCheck.equals(username)) {
						return true;
						
					} else {
					return false;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error here1");
			}
		
		} catch (SQLException e) {
			System.out.println("Error here2");
		}
		return false;
	}
		
	

	public static void main(String[] args) {
		setupConnection();


	}
}

