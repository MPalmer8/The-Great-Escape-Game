import java.util.ArrayList;

/**
 * Write a description of class Backpack here.
 *
 * @author Matthew Palmer K21005255
 * @version 1.0
 */
public class Backpack
{
    private int maxWeight; // The maxmimum weight that the user can carry
    private ArrayList<Item> storedItems; // Stores the items that the user is carrying

    /**
     * Constructor for objects of class Backpack
     */
    public Backpack(int maxWeight)
    {
        this.maxWeight = maxWeight;
        storedItems = new ArrayList<>(); 
    }

    /**
     * Adds an Item object to the storedItems ArrayList
     * @param An Item object
     */
    public void placeItem(Item item)
    {
        storedItems.add(item);
    }
    
    /**
     * Removes an Item object from the storedItems ArrayList
     * @param An Item object
     */
    public void takeItem(Item item){
        storedItems.remove(item);
    }
    
    /**
     * Get a String of the Item objects' names stored in the storedItems ArrayList
     * @param String name
     * @return A String stating the Item objects stored in the ArrayList
     */
    public Item getItemObjectBackpack(String name){
        Item returnValue = null; 
        for(Item index: storedItems){
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
     * Get the value stored in the maximumWeight variable
     * @return maxWeight variable
     */
    public int getMaxWeight(){
        return maxWeight;
    }
    
    /**
     * Gets a String containing the items stored in the storedItems ArrayList
     * @return String containing Item objects stored in storedItems ArrayList
     */
    public String getStoredItems(){
        String returnString = "Items in Backpack:"; 
        if(storedItems.isEmpty()){
            returnString = "There are currently no items in your backpack";
        }
        else{
            for(Item inBackpack: storedItems){
                returnString += " " + inBackpack.getName();
            }
        }
        return returnString;
    }
        
    
}
