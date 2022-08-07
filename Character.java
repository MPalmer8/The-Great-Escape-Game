import java.util.ArrayList;

/**
 * Write a description of class Character here.
 *
 * @author Matthew Palmer K21005255
 * @version 1.0
 */
public class Character
{
    private String name; // Stores the name of the character
    private boolean moveAround; // Determines whether a character can move around
    private ArrayList<Item> inventory; // Stores the items that the character holds
    private int characterWeight; // Stores the weight of all of the items that the character is holding
    private ArrayList<Room> path; // Stores the path the character will take if it able to move rooms
    /**
     * Constructor for objects of class Character
     */
    public Character(String name, boolean moveAround)
    {
        this.name = name;
        this.moveAround = moveAround;
        inventory = new ArrayList<>();
        path = new ArrayList<>();
    }

    /**
     * Add an item to the character's inventory and add the weight of that item to the Character's weight
     * @param An Item object
     */
    public void additionalItem(Item item)
    {
        inventory.add(item);
        characterWeight += item.getWeight();
    }
    
    /**
     * Remove an item from the character's inventory and subtract the item weight from the Character's weight
     * @param An Item Object
     */
    public void removalOfItem(Item item){
        inventory.remove(item);
        characterWeight -= item.getWeight();
    }
    
    /**
     * Used to get the Character's name
     * @return A String object storing the Character's name
    */
    public String getCharName(){
        return name; 
    }
    
    
    /**
     * Used to store the contents of the Character's inventory in a String
     * @return A String stating the items in the inventory ArrayList
     */
    
    public String viewInventory(){
        String returnString = "Inventory of " + getCharName() +":";
        if(inventory.isEmpty()){
            returnString = "This character isn't currently holding any items";
        }
        else{
            for(Item item: inventory){
                returnString += " " + item.getName();
            }
        }
        return returnString;
    }
    
    
    /**
     * USed to get the Character's weight
     * @return Character's current weight
     */
    public int getCharWeight(){
        return characterWeight; 
    }
    
    /**
     * Gets the item object in the inventory ArrayList that the string name corresponds to
     * @params A string
     * @return Item object that the string corresponds to
     */
    public Item getItemObjectChar(String name){
        Item returnValue = null; 
        for(Item index: inventory){
            String itemName = index.getName();
            if(itemName.equals(name)){
                returnValue = index; 
                break;
            }else{
                returnValue = null; 
            }
        }
        return returnValue;
    }
    
    
    /**
     * Get the moveAround value to see if the character is able to move around.
     * @return A boolean value
     */
    public boolean getMoveValue(){
        return moveAround;
    }
    
    /**
     * Add a room to the character's path
     * @param A room object
     */
    public void addRoom(Room value){
        path.add(value);
    }
    
    /**
     * Remove a room from the character's path
     * @param A room object
     */
    public void removeRoom(Room value){
        path.remove(value);
    }
    
    /**
     * Get the path of the character
     * @return An ArrayList of the Rooms the character has on their path
     */
    public ArrayList getPath(){
        return path; 
    }
    

}
