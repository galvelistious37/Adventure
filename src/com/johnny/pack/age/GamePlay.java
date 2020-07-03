package com.johnny.pack.age;

import java.util.*;

class GamePlay {
    // Constants
    private static final String GREETING = "Welcome to the Greatest Adventure Game Ever!!!";
    private static final String WRONG_DIRECTION = "You cannot go in that direction";
    private static final String CHOOSE_VALID_OPTION = "Select a valid option!";
    private static final String SHUTTING_DOWN = "Shutting down...";
    private static final int STATUS = 0;

    // Global Variables
    private Scanner scanner;
    private Map<Integer, Location> locationMap;
    private List<Character> enemies;
    private Character playerOne;
    private Fight fightObj;
    private LocationBuilder locationBuilder;
    private EnemyBuilder enemyBuilder;

    /**
     * GamePlay Constructor instantiates global variables
     */
    GamePlay() {
        fightObj = new Fight();
        scanner = new Scanner(System.in);
        playerOne = new Player();
        locationBuilder = new LocationBuilder();
        enemyBuilder = new EnemyBuilder();
        locationMap = locationBuilder.generateLocationMap();
        enemies = enemyBuilder.getEnemyList();
    }

    /**
     * Greet and play the game
     */
    void play() {
        displayGreeting();
        playTheGame();
    }

    /**
     * Print a greeting to the screen
     */
    private void displayGreeting() {
        System.out.println("");
        System.out.println(GREETING);
    }

    /**
     * Main game play method. This method loops through the logic
     * of each round.
     */
    private void playTheGame(){
        int locationNumber = playerOne.getLocation();
        int nextLocationNumber = 0;
        Map<String, Integer> exits;
        while(true){
            displayLocation(locationNumber);
            checkForEnemies(locationNumber);
            exits = locationMap.get(locationNumber).getExits();
            displayAvailableExits(exits);
            String direction = scanner.nextLine().toUpperCase();
            nextLocationNumber = moveInDirection(locationNumber, exits, direction);
            if(locationNumber != nextLocationNumber){
                resetCharacterInitiative(locationNumber);
                locationNumber = nextLocationNumber;
            }
        }
    }

    private void resetCharacterInitiative(int locationNumber){
        List<Character> localEnemies = getEnemiesFromLocation(locationNumber);
        playerOne.setInitiative(0);
        for(Character enemy : localEnemies){
            enemy.setInitiative(0);
        }
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
            System.out.println("");
        } else {
            System.out.println(WRONG_DIRECTION);
        }
        return locationNumber;
    }


    private void dealWithEnemies(List<Character> localEnemies){
        fightObj.initiative(playerOne, localEnemies);
        fightObj.doFightinStuff(playerOne, localEnemies);
    }

    private boolean areEnemiesPresent(int locationNumber) {
        for(Character enemy : enemies){
            if(enemy.getLocation() == locationNumber){
                return true;
            }
        }
        return false;
    }

    private List<Character> getEnemiesFromLocation(int locationNumber){
        List<Character> tempList = new ArrayList<>();
        for(Character enemy : enemies){
            if(enemy.getLocation() == locationNumber){
                tempList.add(enemy);
            }
        }
        return tempList;
    }

    protected void displayEnemies(List<Character> localEnemies){
        System.out.println("\tEnemies in these lands:");
        for(Character enemy : localEnemies){
            System.out.println("\t" + "[" + localEnemies.indexOf(enemy) + "] " +
                    enemy.getName() + ": " + enemy.getHitpoints() + " HP");
        }
    }

    private void displayAvailableExits(Map<String, Integer> locationExits) {
        System.out.println("Available exits are: ");
        int stringCounter = 1;
        System.out.print("\t");
        for(String exit : locationExits.keySet()){
            if(stringCounter < locationExits.size()){
                System.out.print(exit + ", ");
            } else {
                System.out.print(exit);
            }
            stringCounter++;
        }
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
        System.out.println("");
        System.out.println("Location: " + locationMap.get(locationNumber).getDescription());
    }

    protected void showCharacterStatus(Character character) {
        System.out.println(" ");
        System.out.println(character.toString());
        System.out.println(" ");
    }

    protected void quit() {
        scanner.close();
        System.out.println(SHUTTING_DOWN);
        System.exit(STATUS);
    }
}
