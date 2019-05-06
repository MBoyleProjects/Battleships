package pastProgress;

import application.DatabaseMethods;

public class Test {

	
	public static void main(String[] args) {
		DatabaseMethods.setupConnection();
		 
		if (DatabaseMethods.loginUser("first", "pass123") == true) {
			System.out.println("This worked");
		}
		else {
			System.out.println("it didnt.");
		}
	//	DatabaseMethods.loginUser("em", "parola");
	//	DatabaseMethods.loginUser("izzie", "abcd");
	//	DatabaseMethods.loginUser("first", "pass123");
//		DatabaseMethods.loginUser("first", "pass123");
//		DatabaseMethods.loginUser("first", "pass123");
	}
}
