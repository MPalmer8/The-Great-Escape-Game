import java.util.HashMap;

/**
 * Write a description of class Item here.
 *
 * @author Matthew Palmer K21005255
 * @version 1.0
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemName; // Stores the name of the item
    private int weight; // Stores the weight of the item
    private boolean pickup; // Determines whether an item can be picked up or not

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemName, int weight, boolean pickup)
    {
        // initialise instance variables
        this.itemName = itemName;
        this.weight = weight;
        this.pickup = pickup;
        
    }

    /**
     * Used to get the name of the item object
     * @return String name of the item
     */
    public String getName()
    {
        return itemName; 
    }
    
    /**
     * Used to get the weight of the item objectd
     * @return int weight
     */
    public int getWeight()
    {
        return weight; 
    }
    
    /**
     * Used to check if the item can be picked up or not
     * @return boolean variable if the item can be picked up or not
     */
    public boolean getPickup(){
        return pickup; 
    }
    
    /**
     * Set if the item can be picked up or not.
     * @params A boolean value to set the pickup variable to
     */
    public void setPickup(boolean value){
        pickup = value;
    }
}
