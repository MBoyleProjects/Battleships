
import java.util.ArrayList;

public class Ships {
	private String name;
    private ArrayList<String> locationCells;
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setLocationCells(ArrayList<String> loc) {  //locationcells are the coordinates at which the ships are located.
        this.locationCells = loc;
    }
    
    public ArrayList<String> getLocationCells() {
    	return this.locationCells;
    }
    
    
    
    public String checkYourself(String userInput){ //this method checks the user guess against the locationcells for all the ships placed.
    
        String result = "miss"; //else clause specifies a miss
        
        int index = locationCells.indexOf(userInput);  //find the index in the locationcell array of the user guess
        if (index >= 0) {
            locationCells.remove(index);          //if the user guess was in the ship's locationcells, then remove that cell
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

   
    
  
}
