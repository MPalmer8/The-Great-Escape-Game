import java.util.Random;
import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes, and Matthew Palmer K21005255
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom; // Holds the current room that the user is in
    private Room previousRoom; // Holds the previous room that the user was in
    private Room firstRoom; // Holds the room before the previous room that the user was in
    
    private boolean backCheck = false; // Used to see if the back command has been executed more than once
    private ArrayList<Room> roomList; // Stores the rooms in the game 
    private Room teleporter;  // Used to indentify when the user is in the teleporter room
    private Backpack backpack; // Holds the user's items
    private int carriedWeight; // Stores the user's total weight
    private ArrayList<Objective> objectiveList; // Stores the objectives that have to be completed
    private Objective currentObjective; // Stores the current objective to be completed by the user
    private boolean objectivesCompleted = false; // Turns true when all of the objectives in the objective list have been completed
    private int placeholder;  // Used to store the current objective's index in the objectives array list
    private Room beforeWinRoom; // Stores the room that comes before the winning room
    private Room winningRoom; // Stores the room that the user will win in when entering the room 
    private Item winningItem; // Stores the item needed to enter the winningRoom
    private int roomCounter = 0; // Used as an index value on the path for a certain character that is able to move around, index will get their next room.
    private Character mobileGuard; // A mobileguard Character
    private Objective finalObjective; // The final objective of the game
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        roomList = new ArrayList<>();
        objectiveList = new ArrayList<>();
        createElements();
        parser = new Parser(); 
        backpack = new Backpack(5000);
        carriedWeight = 0; 
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all of Items and place them in a room.
     * Create all of the characters and place them in a room, and give them items.
     */
    private void createElements()
    {
        Room cell, corridor, guard, breakguard, gate, cafeteria, medical, kitchen, yard, common, games, gym, toilet, shower, outside; 
        
        // create the rooms
        cell = new Room("in your cell");
        corridor = new Room("in the prison corridor");
        guard = new Room("in the guard room");
        breakguard = new Room("in the guard's break room"); 
        gate = new Room("in the gate room which hold the gates to exit the prison");
        cafeteria = new Room("in the cafeteria where food is served");
        medical = new Room("in the medical room");
        kitchen = new Room("in the kitchen where food is prepared");
        yard = new Room("in the outside yard area");
        common = new Room("in the inmate common room");
        games = new Room("in the inmate games room");
        gym = new Room("in the prison gym");
        toilet = new Room("in the prison toilets");
        shower = new Room("in the prison shower room");
        teleporter = new Room("in the magic teleporter room");
        outside = new Room("outside of the prison");
        
        // initialise room exists 
        cell.setExit("north", corridor);
        cell.setExit("east", teleporter);
        
        corridor.setExit("north", guard);
        corridor.setExit("east", cafeteria);
        corridor.setExit("south", cell);
        corridor.setExit("west", common);
        
        guard.setExit("north", breakguard);
        guard.setExit("south", corridor);
        
        breakguard.setExit("north", gate);
        breakguard.setExit("south", guard);
        
        gate.setExit("south", breakguard);
        
        cafeteria.setExit("north", medical);
        cafeteria.setExit("east", kitchen);
        cafeteria.setExit("south", yard);
        cafeteria.setExit("west", corridor);
        
        medical.setExit("south", cafeteria);
        
        kitchen.setExit("west", cafeteria);
        
        yard.setExit("north", cafeteria);
        
        common.setExit("north", games);
        common.setExit("east", corridor);
        common.setExit("south", toilet);
        common.setExit("west", gym);
        
        games.setExit("south", common);
        
        gym.setExit("east", common);
        
        toilet.setExit("north", common);
        toilet.setExit("south", shower);
        
        shower.setExit("north", toilet);
        

        currentRoom = cell; // start game outside
        previousRoom = cell; // set the previous room
        firstRoom = cell; // set the room before the previous room
        beforeWinRoom = gate; // set the room before the winning room
        winningRoom = outside; // set the room that the user will win in
        
        // Add the rooms to the roomList array list
        roomList.add(cell);
        roomList.add(corridor);
        roomList.add(guard);
        roomList.add(breakguard);
        roomList.add(gate);
        roomList.add(cafeteria);
        roomList.add(medical);
        roomList.add(kitchen);
        roomList.add(yard);
        roomList.add(common);
        roomList.add(games);
        roomList.add(gym);
        roomList.add(toilet);
        roomList.add(shower);
        
        
        
        // Create the items that are in the game
        Item weights, spoon, note, contraband, weapon, money, gatecard, food, uniform, chess, sponge, stethoscope, monitor, bed, plate, looroll; 
        weights = new Item("weights", 1000, true); 
        spoon = new Item("spoon", 15, true);
        note = new Item("note", 5, true);
        contraband = new Item("contraband", 50, false);
        weapon = new Item("weapon", 500, false);
        money = new Item("money", 100, false);
        gatecard = new Item("gatecard", 150, false);
        food = new Item("food", 500, true);
        uniform = new Item("uniform", 2000, true);
        chess = new Item("chess", 500, true);
        sponge = new Item("sponge", 100, true);
        stethoscope = new Item("stethoscope", 1000, true);
        monitor = new Item("monitor", 3500, false);
        bed = new Item("bed", 4900, false);
        plate = new Item("plate", 300, true);
        looroll = new Item("looroll", 200, true);
        
        winningItem = gatecard; // Set the item the user needs to enter the winning room
        // Add items to the rooms
        gym.addItem(weights);
        cell.addItem(note); 
        kitchen.addItem(food);
        guard.addItem(uniform);
        games.addItem(chess);
        shower.addItem(sponge);
        medical.addItem(stethoscope);
        medical.addItem(monitor);
        cell.addItem(bed);
        kitchen.addItem(plate);
        toilet.addItem(looroll);
        
        // Create the characters that are in the game
        Character chef, contrabandChar, weaponChar, inmate, gymguard; 
        chef = new Character("chef", false); 
        contrabandChar = new Character("dealer", false);
        weaponChar = new Character("smuggler", false);
        inmate = new Character("inmate", false);
        gymguard = new Character("gym-guard", false);
        mobileGuard = new Character("guard", true);
        
        // Add characters to the rooms
        kitchen.addCharacter(chef);
        yard.addCharacter(contrabandChar);
        shower.addCharacter(weaponChar);
        common.addCharacter(inmate);
        gym.addCharacter(gymguard);
        
        // Give characters items
        chef.additionalItem(spoon);
        contrabandChar.additionalItem(contraband);
        weaponChar.additionalItem(weapon);
        inmate.additionalItem(money);
        gymguard.additionalItem(gatecard);
        
        // Set the objectives of the game
        Objective objectiveOne, objectiveTwo, objectiveThree, objectiveFour, objectiveFive, objectiveSix;
        objectiveOne = new Objective("Pickup the note located in your cell", note);
        objectiveTwo = new Objective("Trade your note for contraband from the dealer character located in the yard", contraband);
        objectiveThree = new Objective("Trade your contraband for a weapon from the smuggler located in the shower room", weapon);
        objectiveFour = new Objective("Trade your weapon for money from the inmate character located in the common room", money);
        objectiveFive = new Objective("Trade your money for a gatecard from the gym guard character located in the gym room", gatecard);
        finalObjective = new Objective("Go north from the gate room", null);
        // Add the objectives to the objectiveList ArrayList
        objectiveList.add(objectiveOne);
        objectiveList.add(objectiveTwo);
        objectiveList.add(objectiveThree);
        objectiveList.add(objectiveFour);
        objectiveList.add(objectiveFive);
        getCurrentObjective();
        
        
        
        // Set a path for a character
        
        mobileGuard.addRoom(cafeteria);
        mobileGuard.addRoom(corridor);
        mobileGuard.addRoom(common);
        mobileGuard.addRoom(gym);
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to The Great Escape!");
        System.out.println("The Great Escape is an adventure game");
        System.out.println("You are an inmate in a prison");
        System.out.println("Follow the objectives until you have escaped prison!");
        System.out.println("Type 'help' for a list of useful commands");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
            commandDesc();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")){
            backCheck = !backCheck; 
            if(backCheck){
                firstRoom = currentRoom; 
                currentRoom = previousRoom;
                System.out.println(previousRoom.getLongDescription());
            }
            else{
                currentRoom = firstRoom;
                System.out.println(firstRoom.getLongDescription());
            }
        }
        else if (commandWord.equals("pickup")){
            pickUp(command);
        }
        else if (commandWord.equals("drop")){
            drop(command);
        }
        else if (commandWord.equals("info")){
            getInfo();
        }
        else if (commandWord.equals("view")){
            view(command);
        }
        else if (commandWord.equals("take")){
            take(command);
        }
        else if (commandWord.equals("give")){
            give(command);
        }
        else if (commandWord.equals("objective")){
            getCurrentObjective();
            System.out.println(currentObjective.getDesc());
        }
            
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * List all of the command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the prison.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    
    /**
     * Detailed description of how to use each command and what each command does.
     */
    
    private void commandDesc(){
        System.out.println(); 
        System.out.println("Descriptions of every command:");
        System.out.println("go (direction) - Enter the next room that is located in that direction");
        System.out.println("quit - Exit the game");
        System.out.println("help - Display some useful information");
        System.out.println("back - Go to the last room that you were in");
        System.out.println("pickup (item) - Pickup an item that is present in that room");
        System.out.println("drop (item) - Drop an item that is in your inventory in the current room that you are in");
        System.out.println("info - Get some information including what items you are carrying and the total weight that you are carrying");
        System.out.println("view (character) - View information about a character, including their items and the weight of those items altogether");
        System.out.println("take (item) (character) - Take an item from a character");
        System.out.println("give (item) (character) - Give an item to a character");
        System.out.println("objective - Display the current objective");
        
    }
    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        backCheck = false; 
        characterMove(mobileGuard); // Move a certain character
        roomCounter++; 
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(nextRoom == winningRoom){
            Item tempItem = backpack.getItemObjectBackpack("gatecard");
            if (tempItem == null){
                System.out.println("You don't currently have the gatecard so you can't exit the prison");
            }
            else{
                previousRoom = currentRoom; 
                currentRoom = nextRoom;
                getCurrentObjective();
            }
        }
        else {
            previousRoom = currentRoom; 
            currentRoom = nextRoom;
            getCurrentObjective();
        }
        
        if (currentRoom == teleporter){ 
            System.out.println(currentRoom.getShortDescription());
            System.out.println("");
            randomRoom();
        }
        else if(currentRoom == winningRoom){
            System.out.println(currentRoom.getShortDescription());
            gameWon();
        }
        else{
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Generates a random room to be teleported to when using the magic teleporter room
     */
    
    private void randomRoom(){
        Random rand = new Random(); 
        int num = rand.nextInt(13); 
        Room generatedRoom = roomList.get(num); 
        currentRoom = generatedRoom;
        System.out.println(currentRoom.getLongDescription());
    }
    

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    
    
    /** 
     * Try to pickup an item that is present in that room. If there isn't the item that the user has selected, state it to the user.
     * Otherwise, add that item to the backpack and remove it from the room.
     */
    private void pickUp(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pickup
            System.out.println("Pickup what?");
            return;
        }
        String pickUpItem = command.getSecondWord();
        Item value = currentRoom.getItemObject(pickUpItem); 
        if (value == null) {
            System.out.println("There is no item with that name present in this room");
        }else{
            int tempWeight = carriedWeight + value.getWeight();
            if (!value.getPickup()){
                System.out.println("You aren't allowed to pickup that item");
            }
            else if (tempWeight > backpack.getMaxWeight()){
                System.out.println("You cannot pickup that item as it would exceed the maximum weight that you can carry");
            }
            else {
                backpack.placeItem(value);
                currentRoom.removeItem(value);
                carriedWeight += value.getWeight(); 
                System.out.println("You have picked up the " + value.getName() + " item");
                
                if(value == currentObjective.getNeededItem()){
                    currentObjective.setTrue();
                    getCurrentObjective();
                }

            }  
        }
        
    }
    
    /** 
     * Try to pickup an item that is present in that room. If there isn't the item that the user has selected, state it to the user.
     * Otherwise, add that item to the backpack and remove it from the room.
     */
    private void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to Drop
            System.out.println("Drop what?");
            return;
        }

        String dropItem = command.getSecondWord();
        Item value = backpack.getItemObjectBackpack(dropItem);
        if (value == null) {
            System.out.println("There is no item with that name present in your backpack");
        }
        else {
            backpack.takeItem(value);
            currentRoom.addItem(value);
            carriedWeight -= value.getWeight();
            System.out.println("You have dropped the " + value.getName() + " item");

        }
    }
    
    /**
     * Prints out some information to the user: the items that they are currently carrying and the weight of those items altogether
     */
    
    private void getInfo(){
        System.out.println(backpack.getStoredItems() + "\n" + "Weight of current carried items: " + carriedWeight + "g");
    }
  
    /**
     * View what items a certain character has in their inventory and the total weight that the character is holding. 
     * @param A command that the user has entered.
     */
    
    private void view(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to view
            System.out.println("View what?");
            return;
        }
        
        String viewChar = command.getSecondWord();
        Character value = currentRoom.getCharacterObject(viewChar);
        if (value == null){
            System.out.println("That character is not present in this room");
        }
        else{
            System.out.println(value.viewInventory() + "\nWeight of Character's carried items: " + value.getCharWeight() + "g");
        }
    }

    /**
     * Take an item from a character's inventory and place it into the user's backpack
     * Add the item's weight into the user's carried weight and take it away from the character's carried weight.
     * @param A command that the user has entered
     */
        
    private void take(Command command){
        if(!command.hasThirdWord()){
            System.out.println("take the item from who?");
            return;
        }else if(!command.hasSecondWord()){
            System.out.println("take what item?");
            return; 
        }
        
        String commandItem = command.getSecondWord();
        String commandChar = command.getThirdWord();
        Character charValue = currentRoom.getCharacterObject(commandChar);
        if (charValue == null){
            System.out.println("That character is not present in this room so you cannot take items from them here");
        }
        else{
            Item itemValue = charValue.getItemObjectChar(commandItem);
            if (itemValue == null){
                System.out.println("That item is not present in that character's inventory");
            }
            else if (!itemValue.getPickup()){
                System.out.println("You can't take that item from that character");
            }
            else{
                int tempWeight = carriedWeight + itemValue.getWeight();
                if (tempWeight > backpack.getMaxWeight()){
                    System.out.println("You cannot take that item from the " + commandChar + " character as it would exceed the maximum weight that you can carry");
                }
                else{
                    charValue.removalOfItem(itemValue);
                    backpack.placeItem(itemValue);
                    System.out.println("You have taken the " + commandItem + " item from the " + commandChar + " character");
                    carriedWeight += itemValue.getWeight();
                    
                    if(itemValue == currentObjective.getNeededItem()){
                        currentObjective.setTrue();
                        getCurrentObjective();
                    }
                
                }
            }
        }   
    }
    
    
    /**
     * Give a certain character an item from the user's backpack that will be placed in the characters inventory.
     * Subtract the item's weight from the user's carried weight and add this weight to the character's carried weight.
     * @param A command that the user has entered 
     */
    private void give(Command command){
        if(!command.hasThirdWord()){
            System.out.println("give the item to who?");
            return;
        }else if(!command.hasSecondWord()){
            System.out.println("give what item?");
            return; 
        }
        
        String commandItem = command.getSecondWord();
        String commandChar = command.getThirdWord();
        Character charValue = currentRoom.getCharacterObject(commandChar);
        
        if (charValue == null){
            System.out.println("That character is not present in this room so you cannot give items to them here");
        }
        else{
            Item itemValue = backpack.getItemObjectBackpack(commandItem);
            if (itemValue == null){
                System.out.println("That item is not present in your backpack");
            }
            else{
                charValue.additionalItem(itemValue);
                backpack.takeItem(itemValue);
                System.out.println("You have given the " + commandItem + " item to the " + commandChar + " character");
                carriedWeight -= itemValue.getWeight();
                
                Objective tempObjective = objectiveList.get(placeholder);
                if(tempObjective.getNeededItem() == itemValue){
                    Item tempItem = currentObjective.getNeededItem();
                    tempItem.setPickup(true);
                }
            }
            
        }
        
        
    }
    
    
    /**
     * This will get the current objective and store it in the currentObjective variable if there is an objective in the objectiveList that has their completed field as false.
     * Otherwise, a new room will be created.
     */
    private void getCurrentObjective(){
        placeholder = -2;
        for(Objective index: objectiveList){
            placeholder++;
            if(!index.getValue()){
                currentObjective = index; 
                break;
            }
            else{
                objectivesCompleted = true;
                beforeWinRoom.setExit("north", winningRoom);
                currentObjective = finalObjective;
                
            }
        }
    }
        
    /**
     * Prints out Strings that let the user know that they have won the game.
     */
    private void gameWon(){
        System.out.println("You have escaped prison");
        System.out.println("You have won the game!");
        System.out.println("Type quit to exit the game");
    }
    
    /**
     * Moves a character along their set path until they have been in all of the rooms specified on their path ArrayList. 
     * @param A character object 
     */
    private void characterMove(Character character){
        if(character.getMoveValue()){
            ArrayList<Room> path = character.getPath();
            if(roomCounter < path.size()){
                Room room = path.get(roomCounter);
                if(roomCounter > 0){
                    int pastRoomValue = roomCounter - 1;
                    Room pastRoom = path.get(pastRoomValue);
                    room.addCharacter(character);
                    pastRoom.removeCharacter(character);
                }else{
                    room.addCharacter(character);
                }
            }
        }
    }
}
