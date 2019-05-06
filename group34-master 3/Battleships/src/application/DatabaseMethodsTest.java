package application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.DatabaseMethods;

/**
 * Class that will test the Database Methods class
 * 
 * @author DoraPpo
 *
 */

public class DatabaseMethodsTest {
	/**
	 * Test for loginUser method Tests for loginUser method when both the username
	 * and password are correct
	 */
	@Test
	public void loginUserTest1() {
		DatabaseMethods.setupConnection();

		boolean expected = true;
		boolean actual = DatabaseMethods.loginUser("oscar", "kitty01");
		assertEquals(expected, actual);

	}

	/**
	 * Test for loginUser method Tests for loginUser method when username is wrong
	 */
	@Test
	public void loginUserTest2() {
		DatabaseMethods.setupConnection();

		boolean expected = false;
		boolean actual = DatabaseMethods.loginUser("wrong", "kitty01");
		assertEquals(expected, actual);

	}

	/**
	 * Test for loginUser method Tests for loginUser method when password is wrong
	 */
	@Test
	public void loginUserTEst3() {
		DatabaseMethods.setupConnection();

		boolean expected = false;
		boolean actual = DatabaseMethods.loginUser("oscar", "wrong");
		assertEquals(expected, actual);

	}

	/**
	 * Test for registerUser method
	 */
	@Test
	public void registerUserTest1() {
		DatabaseMethods.setupConnection();

		boolean expected = true;
		boolean actual = DatabaseMethods.registerUser("testU02", "testP");
		assertEquals(expected, actual);

	}
	// sanity checks on client side for matching passwords, reducing number of checks

	/**
	 * Test for registerUser method Tests for registerUser method when username
	 * already taken
	 */
	@Test
	public void registerUserTest2() {
		DatabaseMethods.setupConnection();

		boolean expected = false;
		boolean actual = DatabaseMethods.loginUser("oscar", "test ");
		assertEquals(expected, actual);

	}

	/**
	 * Test for registerUser method Tests for registerUser method when user was
	 * registered while testing the registerUser method Test for registerUser method
	 * when username is testU
	 */
	@Test
	public void registerUserTest3() {
		DatabaseMethods.setupConnection();

		boolean expected = false;
		boolean actual = DatabaseMethods.loginUser("testU", "123456 ");
		assertEquals(expected, actual);

	}

	/**
	 * Test for usernameTaken method While registering, usernameTaken method checks
	 * to see if username already in database Test for usernameTaken method when
	 * username already taken
	 */
	@Test
	public void usernameTakenTest1() {
		DatabaseMethods.setupConnection();

		boolean expected = true;
		boolean actual = DatabaseMethods.usernameTaken("moomoo");
		assertEquals(expected, actual);

	}

	/**
	 * Test for usernameTaken method Test for usernameTaken method when username not
	 * taken
	 */
	@Test
	public void usernameTakenTest2() {
		DatabaseMethods.setupConnection();

		boolean expected = false;
		boolean actual = DatabaseMethods.usernameTaken("testUT");
		assertEquals(expected, actual);

	}

}
