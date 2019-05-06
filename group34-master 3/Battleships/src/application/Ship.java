package application;
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

}
