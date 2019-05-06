

import java.io.*;
import java.util.*;

public class GameHelper {

  private static final String alphabet = "abcdefg"; //7 letters for our 7 rows
  private static int gridLength = 7; //7 columns
  private static int gridSize = 49;
  private static int [] grid = new int[gridSize];
  private static int shipCount = 0;


  public String getUserInput(String prompt) { //this is just a basic user input class
     String inputLine = null;
     
     System.out.print(prompt + "  ");
     try {
       BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
       inputLine = is.readLine();
       
       if (inputLine.length() == 0 ){ //if no input return null
    	   return null; 
       }
     } catch (IOException e) {
       System.out.println("IOException: " + e);
     }
     return inputLine.toLowerCase(); //make sure all input letters are in lower case, eg G5 -> g5 to match the alphacoords
  }

  
 //this method will place our ships on our 2d grid, ensuring they are placed validly. it takes in the ship length 
 public static ArrayList<String> placeShips(int shipSize) {                
    ArrayList<String> alphaCells = new ArrayList<String>();
    
    String [] alphacoords = new String [shipSize];      // holds 'f6' type coords, so we have both the row and the column coordinates.
    String temp = null;                                // temporary String for concatenation
    int [] coords = new int[shipSize];                  // current candidate coords, each ship will have the same # of coords as their length
    int attempts = 0;                                  // current attempts counter
    boolean success = false;                           // flag = found a good location ?
    int location = 0;                                  // current starting location
    
    shipCount++;                                        // nth ship to place
    int incr = 1;                                      // set horizontal increment
    if ((shipCount % 2) == 1) {                         // if odd ship  (place vertically)		this portion deals with verticle/horizontal placement
      incr = gridLength;                               // set vertical increment
    }

    while ( !success & attempts++ < 200 ) {             // main search loop  (32)  //just to make sure we dont get stuck in an endless attempt loop
	location = (int) (Math.random() * gridSize);      // get random starting point
       // System.out.print(" try " + location);
	int x = 0;                                        // nth position in ship to place (ie if all ships are size 3, x should never go above 3
        success = true;                                 // assume success
        while (success && x < shipSize) {                // look for adjacent unused spots
          if (grid[location] == 0) {                    // if not already used
             coords[x++] = location;                    // save location
             location += incr;                          // try 'next' adjacent (remember that incr = 7 if we're going vertically as we have 7 columns so need next row)
             if (location >= gridSize){                 // out of bounds - 'bottom'
               success = false;                         // failure
             }
             if (x>0 & (location % gridLength == 0)) {  // out of bounds - right edge        //here, if
               success = false;                         // failure
             }
          } else {                                      // found already used location
             //  System.out.print(" used " + location);  
              success = false;                          // failure
          }
        }
    }                                                   // end while

    int x = 0;                                          // turn good location into alpha coords
    int row = 0;
    int column = 0;
    // System.out.println("\n");
    while (x < shipSize) {
      grid[coords[x]] = 1;                              // mark master grid pts. as 'used'
      row = (int) (coords[x] / gridLength);             // get row value
      column = coords[x] % gridLength;                  // get numeric column value
      temp = String.valueOf(alphabet.charAt(column));   // convert to alpha
      
      alphaCells.add(temp.concat(Integer.toString(row)));
      x++;

       System.out.print("  coord "+x+" = " + alphaCells.get(x-1));
      
    }
    System.out.println("\n");
    return alphaCells;
   }
 
 public static void main(String[] args) {
	 System.out.println(placeShips(3));
 }
}