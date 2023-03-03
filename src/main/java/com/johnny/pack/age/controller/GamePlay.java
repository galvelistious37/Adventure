package com.johnny.pack.age.controller;

import com.johnny.pack.age.controller.Move.Move;
import com.johnny.pack.age.controller.attack.Fight;
import com.johnny.pack.age.controller.builder.EnemyBuilder;
import com.johnny.pack.age.controller.builder.LocationBuilder;
import com.johnny.pack.age.model.location.Location;
import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.weapon.Knife;
import com.johnny.pack.age.model.weapon.Sword;
import com.johnny.pack.age.view.Display;

import java.util.*;
import java.util.stream.Collectors;

public class GamePlay {

    // Global Variables
    private List<Character> enemies;
    private Player player;

    /**
     * GamePlay Constructor instantiates global variables
     */
    private GamePlay() {
        player = Player.getInstance();
        enemies = EnemyBuilder.getInstance().getEnemyList();
    }

    // Create one and only one instance of GamePlay
    private static final GamePlay INSTANCE = new GamePlay();

    /**
     * Getter for the one and only instance of the GamePlay
     * Object.
     * @return - The GamePlay object instance.
     */
    public static GamePlay getInstance(){
        return INSTANCE;
    }

    /**
     * Greet and initiate the game
     */
    public void initiate() {
        Display.getDisplayInstance.displayText("Welcome to the Greatest Adventure Game Ever!!!");
        playTheGame();
    }

    /**
     * Use a boolean value to determine whether to continue
     * playing the game
     */
    private void playTheGame(){
        boolean isStillPlaying = true;
        while (isStillPlaying) {
            isStillPlaying = goThroughPlayActions();
        }
        shutDown();
    }

    /**
     * This method loops through the logic of game initiate.
     * @return - A boolean value of whether to keep playing
     */
    private boolean goThroughPlayActions() {
        // Get the Location
        Location location = LocationBuilder.mapLocations
                .get(player.getLocation());

        // Display location details
        Display.getDisplayInstance.displayText(
                "Location: " + location.getTerrain());

        // Determine what to do if enemies are present
        enemyLogicFlow(location.getId());

        // If player not alive, return false.
        if(!player.getIsAlive()){
            return false;
        }

        // Display the available exits based on current location.
        Display.getDisplayInstance.displayAvailableExits(location.getExits());

        // Move to the newly selected location.
        int locNumber = moveLocation(location.getId());

        // Check new location for new weapons
        checkNewWeapon(locNumber);

        // Return true to keep playing.
        return true;
    }

    /**
     * This method progresses through the flow of working
     * with enemies if they are present.
     * @param locNumber - current location int value
     */
    private void enemyLogicFlow(int locNumber) {
        // If enemies are present in current location, populate
        // the list of enemies.
        List<Character> enemies;
        if(areEnemiesPresent(locNumber)){
            enemies = getEnemiesFromLocation(locNumber);

            // If enemies are still alive, deal with them.
            Fight fight = new Fight();
            if(fight.areEnemiesAlive(enemies)) {
                fight.initiative(player, enemies);
                fight.doFightinStuff(player, enemies);
            } else {
                Display.getDisplayInstance.displayText("All enemies here are dead");
            }
        }
    }

    /**
     * Find any enemy with the same location as the given parameter
     * @param locationNumber - int location
     * @return - a boolean if enemies have the same location
     */
    private boolean areEnemiesPresent(int locationNumber) {
        return enemies.stream()
                .anyMatch(enemy -> enemy.getLocation() == locationNumber);
    }

    /**
     * Create a list of enemies by filtering on enemies in that location.
     * @param locationNumber - int value of location
     * @return - A list of enemies in the given location
     */
    private List<Character> getEnemiesFromLocation(int locationNumber){
        return enemies.stream()
                .filter(enemy -> enemy.getLocation() == locationNumber)
                .collect(Collectors.toList());
    }

    /**
     * Take user input and determine where to go or to quit.
     * @param id - current location id
     * @return - new location int value
     */
    private int moveLocation(int id) {
        // Take user input for direction
        String direction = getUserSelection();
        // Is a valid selection
        if(isValidSelection(id, direction)){
            // Get new location int value from input
            int nextLocationNumber = moveInDirection(id, direction);
            // moved to new location
            if (id != nextLocationNumber) {
                // reset initiative and set new location
                resetCharacterInitiative(id);
                id = nextLocationNumber;
            }
        }
        return id;
    }

    private boolean isValidSelection(int id, String direction) {
        return LocationBuilder.mapLocations
                .get(id).getExits().containsKey(direction);

    }

    private String getUserSelection() {
        String direction = UserInput.getUserInstance()
                .getScanner().nextLine().toUpperCase();

        if(direction.length() > 1){
            direction = getDirectionFromWord(direction);
        }

        if(direction.equals("Q")){
            quit();
        }

        return direction;
    }

    /**
     * Determine if a location is valid and update
     * the location int value to the int value of the new location.
     * @param id - current location int value
     * @param direction - String value user input for chosen direction
     * @return - the updated int value of the selected new location
     */
    private int moveInDirection(int id, String direction) {
        Map<String, Integer> exits = LocationBuilder.mapLocations
                .get(id).getExits();

        if(exits.containsKey(direction)){
            id = exits.get(direction);
            player.setLocation(id);
        } else {
            Display.getDisplayInstance.displayText("You cannot go in that direction");
        }
        return id;
    }

    /**
     * Break down the String value of words from user input to
     * determine if they have selected an exit.
     * @param direction - String value user input
     * @return - A single character String value used for exit selection
     */
    private String getDirectionFromWord(String direction) {
        Move moveDirections = new Move();
        String[] words = direction.split(" ");
        for(String word : words){
            if(moveDirections.getMoveOptionsMap().containsKey(word)){
                return moveDirections.getMoveOptionsMap().get(word);
            }
        }
        return "X";
    }

    /**
     * Set the initiative for all characters in a given location
     * to zero.
     * @param id - current location id
     */
    private void resetCharacterInitiative(int id){
        player.setInitiative(0);
        getEnemiesFromLocation(id).forEach(enemy -> enemy.setInitiative(0));
    }

    /**
     * WIP progress method: Working on the ability to find and switch
     * player's set weapon and attacks.
     * @param locationNumber - current location int value
     */
    private void checkNewWeapon(int locationNumber) {
        // TODO: This should probably be a map<locationId, Weapon>???
        if(locationNumber == Knife.getInstance().getLocation()){
            setWeaponDetails(Knife.getInstance().weaponType());
        }
        if(locationNumber == Sword.getInstance().getLocation()){

            setWeaponDetails(Sword.getInstance().weaponType());
        }
    }

    /**
     * Set player's attacks and damage values based on weapon type
     * @param weaponType - String value of weapon type
     */
    private void setWeaponDetails(String weaponType){
        Display.getDisplayInstance.displayText("You found a " + weaponType);
        player.setEquipable(player.determineEquipable(weaponType));
        player.setAttackable(player.determineAttackable(player.getEquipable()));
        player.setBerserkable(player.determineBerserkable(player.getEquipable()));
        player.setDamage(player.getEquipable().getDamage());
    }

    /**
     * Call to quit the game
     */
    public void quit() {
        shutDown();
    }

    /**
     * Close the open Scanner object and shut down the app
     */
    private void shutDown(){
        UserInput.getUserInstance().getScanner().close();
        int status = 0;
        Display.getDisplayInstance.displayText("Shutting down...");
        System.exit(status);
    }
}
