package com.johnny.pack.age.controller;

import com.johnny.pack.age.model.Location;
import com.johnny.pack.age.UserInput;
import com.johnny.pack.age.model.*;
import com.johnny.pack.age.model.Character;
import com.johnny.pack.age.model.Constant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GamePlay {

    // Global Variables
    private Map<Integer, Location> locationMap;
    private List<Character> enemies;
    private Player playerOne;

    /**
     * GamePlay Constructor instantiates global variables
     * ALso testing this new git requirement to use tokens instead of pw
     */
    private GamePlay() {
        playerOne = Player.getInstance();
        locationMap = LocationBuilder.createLocationBuilder().getLocationMap();
        enemies = EnemyBuilder.totalEnemiesList(Constant.TOTAL_ENEMIES).getEnemyList();
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
        System.out.println(createGreeting());
        playTheGame();
    }

    /**
     * Print a greeting to the screen
     */
    public String createGreeting() {
        return "Welcome to the Greatest Adventure Game Ever!!!";
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
        // Get the player location int value
        int locNumber = playerOne.getLocation();

        // Determine available exits from the current location
        // and store them in a Map.
        Map<String, Integer> exits;
        exits = locationMap.get(locNumber).getExits();

        // Display location details
        displayLocation(locNumber);

        // Determine what to do if enemies are present
        enemyLogicFlow(locNumber);

        // If player not alive, return false.
        if(!playerOne.getIsAlive()){
            return false;
        }

        // Display the available exits based on current location.
        // Move to the newly selected location.
        displayAvailableExits(exits);
        locNumber = moveLocation(locNumber, exits);

        // Check new location for new weapons
        checkNewWeapon(locNumber);

        // Return true to keep playing.
        return true;
    }


    /**
     * Display the details of a given location
     * @param locationNumber - current location int value
     */
    public void displayLocation(int locationNumber) {
        try{
            System.out.println("Location: " + locationMap.get(locationNumber).getDescription());
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
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
                fight.initiative(playerOne, enemies);
                fight.doFightinStuff(playerOne, enemies);
            } else {
                System.out.println("All enemies here are dead");
            }
        }
    }

    /**
     * Display the list of available exits.
     * @param exits - Map of available exits
     */
    private void displayAvailableExits(Map<String, Integer> exits) {
        System.out.println("Available exits are: ");
        System.out.print("\t");
        Stream<String> stream = Stream.of(exits.keySet().toString());
        stream.forEach(System.out::println);
        System.out.println();
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
     * Take user input and determine where to move or to quit.
     * @param locationNumber - current location int
     * @param exits - Map of String, Integer exit locations
     * @return - new location int value
     */
    private int moveLocation(int locationNumber, Map<String, Integer> exits) {
        // Take user input for direction
        String direction = UserInput.getUserInstance().getScanner().nextLine().toUpperCase();
        // Get new location int value from input
        int nextLocationNumber = moveInDirection(locationNumber, exits, direction);
        // reset initiative and set new location
        if (locationNumber != nextLocationNumber) {
            resetCharacterInitiative(locationNumber);
            locationNumber = nextLocationNumber;
        }
        return locationNumber;
    }

    /**
     * Determine if a location can be updated given the value of user input.
     * If user input matches a String value of an available exit, update
     * the location int value to the int value of the new location.
     * @param locationNumber - current location int value
     * @param exits - Map of available exits
     * @param direction - String value user input for chosen direction
     * @return - the updated int value of the selected new location
     */
    private int moveInDirection(int locationNumber, Map<String, Integer> exits, String direction) {
        if(direction.length() > 1){
            direction = getDirectionFromWord(direction);
        }
        if(direction.equals("Q")){
            quit();
        }
        if(exits.containsKey(direction)){
            locationNumber = exits.get(direction);
            playerOne.setLocation(locationNumber);
        } else {
            String wrongDirection = "You cannot go in that direction";
            System.out.println(wrongDirection);
        }
        return locationNumber;
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
     * @param locationNumber - current location int value
     */
    private void resetCharacterInitiative(int locationNumber){
        playerOne.setInitiative(0);
        getEnemiesFromLocation(locationNumber).forEach(enemy -> enemy.setInitiative(0));
    }

    /**
     * WIP progress method: Working on the ability to find and switch
     * player's set weapon and attacks.
     * @param locationNumber - current location int value
     */
    private void checkNewWeapon(int locationNumber) {
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
        System.out.println("You found a " + weaponType);
        playerOne.setEquipable(playerOne.determineEquipable(weaponType));
        playerOne.setAttackable(playerOne.determineAttackable(playerOne.getEquipable()));
        playerOne.setBerserkable(playerOne.determineBerserkable(playerOne.getEquipable()));
        playerOne.setDamage(playerOne.getEquipable().getDamage());
    }

    /**
     * Call to quit the game
     */
    void quit() {
        shutDown();
    }

    /**
     * Close the open Scanner object and shut down the app
     */
    private void shutDown(){
        UserInput.getUserInstance().getScanner().close();
        String shutDown = "Shutting down...";
        int status = 0;
        System.out.println(shutDown);
        System.exit(status);
    }
}
