package com.johnny.pack.age.controller;

import com.johnny.pack.age.controller.Move.Move;
import com.johnny.pack.age.controller.builder.EnemyBuilder;
import com.johnny.pack.age.controller.builder.LocationBuilder;
import com.johnny.pack.age.controller.runner.FightRunner;
import com.johnny.pack.age.model.characterfactory.PlayerCreator;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.model.location.Location;
import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.weaponabstractfactory.WeaponFactoryRunner;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Knife;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Sword;
import com.johnny.pack.age.view.Display;

import java.util.*;
import java.util.stream.Collectors;

public class GamePlay {

    // Global Variables
    private final Character player = new PlayerCreator().createCharacter();
    private final List<Character> enemies = EnemyBuilder.getInstance().getAllEnemies();

    /**
     *  Create only one instance of GamePlay
     */
    private static final GamePlay INSTANCE = new GamePlay();

    /**
     * Get the one instance of GamePlay Object.
     * @return - The static GamePlay object instance.
     */
    public static GamePlay getInstance(){
        return INSTANCE;
    }

    /**
     * While true, continue playing the game
     */
    public void playTheGame(){
        Display.displayText(Constant.WELCOME_TEXT);
        while (true) {
            goThroughPlayActions();
        }
    }

    private void goThroughPlayActions() {
        // Get the Location
        Location location = getLocation(player.getLocation());
        Display.displayText(Constant.LOCATION_LABEL + location.getTerrain());

        enemyCheck(location.getId());
        Display.showExits(location.getExits());
        int locNumber = moveLocation(location.getId());
        checkNewWeapon(locNumber);
    }

    private void enemyCheck(int locationId) {
        List<Character> enemyList = getEnemiesInLocation(locationId);
        if(enemyList.size() > 0){
            FightRunner.getFightRunner(enemyList).runFightTask();
        }
    }

    /**
     * Get the location from a map of locations
     * @return The map value for key player location id
     */
    private Location getLocation(int locationId) {
        return LocationBuilder
                .mapLocations
                .get(locationId);
    }

    /**
     * Filter the list of enemies by locationId.
     * @param locationId - int value of location
     * @return - A list of enemies in the given location
     */
    private List<Character> getEnemiesInLocation(int locationId){
        return enemies.stream()
                .filter(enemy -> enemy.getLocation() == locationId)
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

        // Is user selection valid?
        if(!isValidSelection(id, direction)){
            Display.displayText(Constant.YOU_SHALL_NOT_PASS);
            // Return current location id
            return id;
        }

        // Return new location id
        return moveInDirection(id, direction);
    }

    /**
     * Validate user input contains acceptable input and that
     * the exit contains the user input value.
     * @param id - location id
     * @param direction - User input direction to move in
     * @return - boolean value is acceptable input and valid movement
     */
    private boolean isValidSelection(int id, String direction) {
        return LocationBuilder.mapLocations
                .get(id)
                .getExits()
                .containsKey(direction);
    }

    /**
     * Take user input and determine if valid direction or if user
     * opted to quit
     * @return - A single character string for user selection
     */
    private String getUserSelection() {
        // Store user input as String variable
        String direction = UserInput.getUserInstance()
                .getScanner()
                .nextLine()
                .toUpperCase();

        // Is user entered more than one character, check
        // for acceptable word
        if(direction.length() > 1){
            direction = getDirectionFromWord(direction);
        }

        // If user entered "Q" then quit
        if(direction.equals(Constant.Q)){
            quit();
        }

        // Return single character String for direction
        return direction;
    }

    /**
     * Break down the String value of words from user input to
     * determine if they have selected an exit.
     * @param direction - String value user input
     * @return - A single character String value used for exit selection
     */
    private String getDirectionFromWord(String direction) {
        String[] words = direction.split(Constant.SPACE);
        for(String word : words){
            if(Move.moveOptionsMap.containsKey(word)){
                return Move.moveOptionsMap.get(word);
            }
        }
        return Constant.X;
    }

    /**
     * Determine if a location is valid and update
     * the location int value to the int value of the new location.
     * @param id - current location int value
     * @param direction - String value user input for chosen direction
     * @return - the updated int value of the selected new location
     */
    private int moveInDirection(int id, String direction) {
        id = LocationBuilder
                .mapLocations
                .get(id)
                .getExits()
                .get(direction);

        player.setLocation(id);
        return id;
    }

    /**
     * WIP progress method: Working on the ability to find and switch
     * player's set weapon and attacks.
     * @param id - current location int value
     */
    private void checkNewWeapon(int id) {
        // TODO: Make a weaponLocation map builder type thing
        Map<Integer, Equipable> weaponLocation = new HashMap<>();
        weaponLocation.put(Knife.getInstance().getLocation(), Knife.getInstance());
        weaponLocation.put(Sword.getInstance().getLocation(), Sword.getInstance());
        if(weaponLocation.containsKey(id)){
            setWeaponDetails(weaponLocation.get(id).weaponType());
        }
    }

    /**
     * Set player's attacks and damage values based on weapon type
     * @param weaponType - String value of weapon type
     */
    private void setWeaponDetails(String weaponType){
        Display.displayText(Constant.YOU_FOUND + weaponType);
        player.setEquipable(WeaponFactoryRunner.determineEquipable(weaponType));
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
        Display.displayText(Constant.SHUTTING_DOWN);
        UserInput.getUserInstance().getScanner().close();
        int status = 0;
        System.exit(status);
    }
}
