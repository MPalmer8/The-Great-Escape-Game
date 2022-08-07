
/**
 * Write a description of class objective here.
 *
 * @author Matthew Palmer K21005255
 * @version 1.0
 */
public class Objective
{
    private Item item; // Stores the item needed to complete the objective
    private String description; // Stores the instruction of how to complete the objective
    private boolean value = false; // Determines whether the objective has been completed or not

    /**
     * Constructor for objects of class objective
     */
    public Objective(String description, Item item)
    {
        this.description = description;
        this.item = item; 
    }

    /**
     * An example of a method - replace this comment with your own
     * @return the description of the objective
     */
    public String getDesc()
    {
        return description;
    }
    
    
    /**
     * Get the needed item to complete the objective
     * @return An item object of the objective instance
     */
    public Item getNeededItem()
    {
        return item;
    }
    
    /**
     * Set the value variable to true
     */
    public void setTrue()
    {
        value = true; 
    }
    
    
    /**
     * Get the value variable
     * @return value variable
     */
    public boolean getValue()
    {
        return value; 
    }
    
    
    /**
     * Used to get an item's name field
     * @param An item object
     * @return The name of that item
     */
    public String getItemName(Item item){
        String itemName = item.getName();
        return itemName;
    }
    
}
