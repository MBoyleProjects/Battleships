import java.util.*;

public class BattleshipsMain {
    private GameHelper helper = new GameHelper();
    private ArrayList<Ships> shipList = new ArrayList<Ships>();
    private int numOfGuesses = 0;
    
    private void setUpGame() {
    	
        Ships warship = new Ships();
        	warship.setName("Warship");
        Ships sub = new Ships();
        	sub.setName("Submarine");
        Ships pboat = new Ships();
        	pboat.setName("Paddleboat");       //create three new ships, all they are is a name atm
        
        shipList.add(warship);
        shipList.add(sub);
        shipList.add(pboat);       //add each ship to an array called shiplist
        
        System.out.println("Your goal is to sink three ships.");
        System.out.println("The Warship, the Submarine and the Paddleboat. Each have a length of 3");
        System.out.println("Try to sink them all in the fewest number of guesses");           //basic instructions to the user
        
        for (Ships ship : shipList) {
            ArrayList<String> newLocation = GameHelper.placeShips(3); //now we have to place the ships on the board, and each has a size of 3 (alphacells are set to be newLocation)
            ship.setLocationCells(newLocation);  //each ship now has location cells set to it
        }
    }
    
    private void startPlaying() {
        while (!shipList.isEmpty()) {  //while we still have ships on the board, prompt user input
            String userGuess = helper.getUserInput("Enter a guess"); 
            checkUserGuess(userGuess); 
        }
        finishGame(); //print endgame content after all ships are sunk
    }
    
    private void checkUserGuess(String userGuess)
    {
        numOfGuesses++;
        String result = "miss";
        
        for (Ships ship : shipList)
        {
            result = ship.checkYourself(userGuess);
            if (result.equals("hit"))
            {
                break;
            }
            if (result.equals("kill"))
            {
            	shipList.remove(ship);
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
        }
        else
        {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
        }
    }
    
    public static void main(String[] args) {
        BattleshipsMain game = new BattleshipsMain();
        game.setUpGame();
        game.startPlaying();
    }
}
