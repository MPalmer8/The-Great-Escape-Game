import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes, and Matthew Palmer K21005255
 * @version 2016.02.29
 */

public class Room 
{
    private String description; // Stores the description of the room
    private HashMap<String, Room> exits; // Stores exits of this room
    private ArrayList<Item> itemsList; // Stores the items that are in the room
    private ArrayList<Character> characterList; // Stores the characters that are in the room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        itemsList = new ArrayList<>();
        characterList = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return "You are in "+ description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + displayItems() + "\n" + displayCharacters();
        
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Adds an Item object into the itemsList Arraylist
     * @param Item object
     */
    
    public void addItem(Item item){
        itemsList.add(item);
    }
    
    /**
     * Remove an item from the room
     * @param Item object
     */
    public void removeItem(Item item){
        itemsList.remove(item);
    }
    
    /**
     * Return a string describing the items in the room, e.g. "Items: plate ".
     * @return A list of the items in a particular room. 
     */
    
    private String displayItems(){
        String returnString = "Items:"; 
        if(itemsList.isEmpty()){
            returnString = "There are no items in this room";
        }
        else{
            for(Item inRoom: itemsList){
                returnString += " " + inRoom.getName();
            }
        }
        return returnString; 
    }
    
    /**
     * Gets an Item object from the itemsList ArrayList using the name String
     * @param String name
     * @return Item Object
     */
    public Item getItemObject(String name){
        Item returnValue = null; 
        for(Item index: itemsList){
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
     * Adds a Character to the room
     * @param Character Object
     */
    public void addCharacter(Character character){
        characterList.add(character);
    }
    
    /**
     * Removes a Character from the room
     * @param Character Object
     */
    public void removeCharacter(Character character){
        characterList.remove(character);
    }
    
    
    /**
     * Displays the characters that are in the room by using the the characterList ArrayList
     * @return String object containing the Characters present in the room
     */
    private String displayCharacters(){
        String returnString = "Characters:"; 
        if(characterList.isEmpty()){
            returnString = "There are no characters in this room";
        }
        else{
            for(Character character: characterList){
                returnString += " " + character.getCharName();
            }
        }
        return returnString; 
    }
    
    /**
     * Gets a character object by using the name string
     * @param String name
     * @return Character Object
     */
    public Character getCharacterObject(String name){
        Character returnValue = null; 
        for(Character index: characterList){
            String characterName = index.getCharName();
            if(characterName.equals(name)){
                returnValue = index; 
                break;
            }else{
                returnValue = null; 
            }
        }
        return returnValue;
    }
}

