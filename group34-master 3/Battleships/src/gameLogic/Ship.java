package gameLogic;
import java.util.ArrayList;

public class Ship {
	
	private String name;
	private int length;
	private int number;
    private ArrayList<String> locationCells;
	 
	 public Ship(String name, int length, int number) {
		 this.name = name;
		 this.length = length;
		 this.number = number;
	 }
	 
	 public void setLocationCells(ArrayList<String> loc) {  //locationcells are the coordinates at which the ships are located.
	     this.locationCells = loc; 							// it is of type string to account for letters, eg f6, g9 etc
	    }
	    
	 public ArrayList<String> getLocationCells() {
	     return this.locationCells;
	    }
	 
	 public String getName() {
		 return this.name;
	 }
	 
	 public int getLength() {
		 return this.length;
	 }
	 
	 public int getNumber() {
		 return this.number;
	 }
	 
	 public String toString() {
		 return number + ": " +  name + "\t Length: " + length;
	 }
	  
	    public String checkYourself(String userInput){ //this method checks the user guess against the location cells for all the ships placed.
	        
	        String result = "miss"; //else clause specifies a miss
	        
	        int index = locationCells.indexOf(userInput);  //find the index in the location cell array of the user guess
	        if (index >= 0) {
	            locationCells.remove(index);          //if the user guess was in the ship's location cells, then remove that cell
	            if (locationCells.isEmpty()) {   //if the location cells are now empty, that means the ship is dead
	                result = "kill";            //thus return killed
	            }
	            else
	            {
	                result = "hit"; //if there are still some location cells left for that ship, 
	            }
	        }
	        return result;
	    }

		public void setName(String name) {
			this.name = name;
			
		}
		
		public void setNumber(int number) {
			this.number = number;
		}
		
		public void setLength(int length) {
			this.length = length;
		}

}
