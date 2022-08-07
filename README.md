# The-Great-Escape

## About
### Base Code
>Authors: Michael Kölling and David J. Barnes  
This project is part of the material for chapter 8 of the book
   >+ Objects First with Java - A Practical Introduction using BlueJ  
   >+ Sixth edition  
   >+ David J. Barnes and Michael Kölling  
   >+ Pearson Education, 2016  
   >
>This project is a simple framework for an adventure game. In this version,
it has a few rooms and the ability for a player to walk between these rooms.
That's all.

### Changes / Additions
>Author: **Matthew Palmer**
>
> The user is an inmate in a prison, the aim of the game is to escape. There are various different rooms within this prison, each possibly containing items and/or characters. Characters are NPC's that the user can interactive with, the character might be holding an item that the user needs, which the user will need to trade another item for. Items are found on the ground in a room, the user can only carry a certain weight, meaning that they can only carry a certain amount of items. The user is able to drop items in rooms. The game consists of objectives, once all of the objectives are complete, the user is able to escape the prison. Each objective has a description and an item that is required to complete the objective, once that item is placed into the user's backpack, the objective is completed.
    
## Technologies
### Languages
>This application was made using Java for a University Project
### Hosting
>This can be run on any machine which is capable of running Java applications using the JVM.

## Usage
### Commands
>+ go (direction) - Enter the next room that is located in that direction
>+ quit - Exit the game
>+ help - Display some useful information
>+ back - Go to the last room that you were in
>+ pickup (item) - Pickup an item that is present in that room
>+ drop (item) - Drop an item that is in your inventory in the current room that you are in
>+ info - Get some information including what items you are carrying and the total weight that you are carrying
>+ view (character) - View information about a character, including their items and the weight of those items altogether
>+ take (item) (character) - Take an item from a character
>+ give (item) (character) - Give an item to a character
>+ objective - Display the current objective
