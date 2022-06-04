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
    private Fight fightObj;
    private boolean isStillPlaying;

    /**
     * GamePlay Constructor instantiates global variables
     */
    private GamePlay() {
        fightObj = new Fight();
        playerOne = Player.getInstance();
        locationMap = LocationBuilder.createLocationBuilder().getLocationMap();
        enemies = EnemyBuilder.totalEnemiesList(Constant.TOTAL_ENEMIES).getEnemyList();
        isStillPlaying = true;
    }

    private static final GamePlay INSTANCE = new GamePlay();

    public static GamePlay getInstance(){
        return INSTANCE;
    }

    /**
     * Greet and play the game
     */
    public void play() {
        System.out.println(displayGreeting());
        playTheGame();
    }

    /**
     * Print a greeting to the screen
     */
    public String displayGreeting() {
        return "Welcome to the Greatest Adventure Game Ever!!!";
    }

    /**
     * Main game play method. This method loops through the logic
     * of each round.
     */
    private void playTheGame(){
        while (isStillPlaying) {
            isStillPlaying = goThroughPlayActions();
        }
        shutDown();
    }

    private boolean goThroughPlayActions() {
        // Get the player location
        int locationNumber = playerOne.getLocation();

        // Determine available exits from the current location
        // and store them in a Map.
        Map<String, Integer> exits;
        exits = locationMap.get(locationNumber).getExits();

        // Display location details
        displayLocation(locationNumber);

        // If enemies are present in current location, populate
        // the list of enemies.
        List<Character> enemies = new ArrayList<>();
        if(areEnemiesPresent(locationNumber)){
            enemies = getEnemiesFromLocation(locationNumber);

            // If enemies are still alive, deal with them.
            if(fightObj.areEnemiesAlive(enemies)) {
                dealWithEnemies(enemies);
            } else {
                System.out.println("All enemies here are dead");
            }
        }

        // If player died dealing with enemies, return false and quit.
        if(!playerOne.getIsAlive()){
            return false;
        }


        displayAvailableExits(exits);
        locationNumber = determineMovement(locationNumber, exits);
        checkNewWeapon(locationNumber);
        return true;
    }

    private int determineMovement(int locationNumber, Map<String, Integer> exits) {
        String direction = UserInput.getUserInstance().getScanner().nextLine().toUpperCase();
        int nextLocationNumber = moveInDirection(locationNumber, exits, direction);
        if (locationNumber != nextLocationNumber) {
            resetCharacterInitiative(locationNumber);
            locationNumber = nextLocationNumber;
        }
        return locationNumber;
    }

    private void checkNewWeapon(int locationNumber) {
        if(locationNumber == Knife.getInstance().getLocation()){
            setWeaponDetails(Knife.getInstance().weaponType());
        }
        if(locationNumber == Sword.getInstance().getLocation()){

            setWeaponDetails(Sword.getInstance().weaponType());
        }
    }

    private void setWeaponDetails(String weaponType){
        System.out.println("You found a " + weaponType);
        playerOne.setEquipable(playerOne.determineEquipable(weaponType));
        playerOne.setAttackable(playerOne.determineAttackable(playerOne.getEquipable()));
        playerOne.setBerserkable(playerOne.determineBerserkable(playerOne.getEquipable()));
        playerOne.setDamage(playerOne.getEquipable().getDamage());
    }

    private void resetCharacterInitiative(int locationNumber){
        playerOne.setInitiative(0);
        getEnemiesFromLocation(locationNumber).forEach(enemy -> enemy.setInitiative(0));
    }

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


    private void dealWithEnemies(List<Character> localEnemies){
        fightObj.initiative(playerOne, localEnemies);
        fightObj.doFightinStuff(playerOne, localEnemies);
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


    private void displayAvailableExits(Map<String, Integer> locationExits) {
        System.out.println("Available exits are: ");
        System.out.print("\t");
        Stream<String> stream = Stream.of(locationExits.keySet().toString());
        stream.forEach(System.out::println);
        System.out.println();
    }

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

    public boolean displayLocation(int locationNumber) {
        try{
            System.out.println("Location: " + locationMap.get(locationNumber).getDescription());
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return true;
    }


    void quit() {
        isStillPlaying = false;
    }

    private void shutDown(){
        UserInput.getUserInstance().getScanner().close();
        String shutDown = "Shutting down...";
        int status = 0;
        System.out.println(shutDown);
        System.exit(status);
    }
}
