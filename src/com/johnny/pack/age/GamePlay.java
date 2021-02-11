package com.johnny.pack.age;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GamePlay {

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
    void play() {
        System.out.println(displayGreeting());
        playTheGame();
    }

    /**
     * Print a greeting to the screen
     */
    String displayGreeting() {
        return "Welcome to the Greatest Adventure Game Ever!!!";
    }

    /**
     * Main game play method. This method loops through the logic
     * of each round.
     */
    private void playTheGame(){
        while (isStillPlaying) {
            goThroughPlayActions();
        }
        shutDown();
    }

    private void goThroughPlayActions() {
        int locationNumber = playerOne.getLocation();
        Map<String, Integer> exits;
        int nextLocationNumber;
        displayLocation(locationNumber);
        checkForEnemies(locationNumber);
        exits = locationMap.get(locationNumber).getExits();
        displayAvailableExits(exits);
        String direction = UserInput.getUserInstance().getScanner().nextLine().toUpperCase();
        nextLocationNumber = moveInDirection(locationNumber, exits, direction);
        if (locationNumber != nextLocationNumber) {
            resetCharacterInitiative(locationNumber);
            locationNumber = nextLocationNumber;
        }
        checkNewWeapon(locationNumber);
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

    private void checkForEnemies(int locationNumber) {
        if(areEnemiesPresent(locationNumber)){
            List<Character> localEnemies = getEnemiesFromLocation(locationNumber);
            if(!fightObj.countTheDead(localEnemies)){
                dealWithEnemies(localEnemies);
            } else {
                System.out.println("All enemies here are dead");
            }
        }
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

    private boolean areEnemiesPresent(int locationNumber) {
        return enemies.stream()
                .anyMatch(enemy -> enemy.getLocation() == locationNumber);
    }

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

    private void displayLocation(int locationNumber) {
        System.out.println("Location: " + locationMap.get(locationNumber).getDescription());
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
