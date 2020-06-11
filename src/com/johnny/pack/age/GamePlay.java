package com.johnny.pack.age;

import java.util.*;

class GamePlay {
    // Constants
    private static final String GREETING = "Welcome to the Greatest Adventure Game Ever!!!";
    private static final String WRONG_DIRECTION = "You cannot go in that direction";
    private static final String CHOOSE_VALID_OPTION = "Select a valid option!";
    private static final String SHUTTING_DOWN = "Shutting down...";
    private static final int STATUS = 0;

    private Move availableExits;

    // Global Variables
    private Scanner scanner;
    private Map<Integer, Location> locationMap;
    private List<Character> enemies;
    private Character playerOne;

    /**
     * GamePlay Constructor instantiates global variables
     */
    GamePlay() {
        scanner = new Scanner(System.in);
        playerOne = new Player();
        LocationBuilder locationBuilder = new LocationBuilder();
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        locationMap = locationBuilder.generateLocationMap();
        enemies = enemyBuilder.getEnemyList();
        availableExits = new Move();
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
        System.out.println(GREETING);
    }

    /**
     * Display text to choose a valid option
     */
    private void selectValidOptionText() {
        System.out.println(CHOOSE_VALID_OPTION);
    }


    /**
     * Main game play method. This method loops through the logic
     * of each round.
     */
    private void playTheGame(){
        int locationNumber = playerOne.getLocation();
        while(true){
            System.out.println(locationMap.get(locationNumber).getDescription());
            showPlayerDetails();
            if(areEnemiesPresent(locationNumber)){
                dealWithEnemies(locationNumber);
//                displayEnemyDetails(locationNumber);
//                fight(locationNumber);
            }
            Map<String, Integer> locationExits = locationMap.get(locationNumber).getExits();
            displayAvailableExits(locationExits);
            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1){
                direction = getDirectionFromWord(direction);
            }
            if(direction.equals("Q")){
                quit();
            }
            if(locationExits.containsKey(direction)){
                locationNumber = locationExits.get(direction);
                playerOne.setLocation(locationNumber);
            } else {
                System.out.println(WRONG_DIRECTION);
            }
        }
    }

    private void dealWithEnemies(int locationNumber) {
        Fight fightinStuff = new Fight();
        List<Character> localEnemies = getEnemiesFromLocation(locationNumber);
        fightinStuff.initiative(playerOne, localEnemies);
        fightinStuff.doFightinStuff(playerOne, localEnemies);
    }

    private void fight(int locationNumber) {
        for(Character enemy : enemies){
            if(enemy.getInitiative() > playerOne.getInitiative()){
                System.out.println(enemy.displayCharacter());
                enemy.performBersek();
                System.out.println("you to death with it's " + enemy.weaponType());
            } else {
                System.out.println(playerOne.displayCharacter() +
                        " says: \"Hey Dude\" to the " + enemy.displayCharacter());
            }
        }
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
                System.out.println(enemy.toString());
                tempList.add(enemy);
            }
        }
        System.out.println("temp list size: = " + tempList.size());
        return tempList;
    }

//    private void displayEnemyDetails(int locatioNumber) {
//        String singularOrPlural = "y is";
//        if(enemies.get(locatioNumber).size() > 1){
//            singularOrPlural = "ies are";
//        }
//        System.out.println(enemies.get(locatioNumber).size()
//                + " enem" + singularOrPlural + " here.");
//    }

    private void displayAvailableExits(Map<String, Integer> locationExits) {
            System.out.println("Available exits are ");
            int stringCounter = 1;
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

    private void showPlayerDetails() {
        System.out.println(playerOne.toString());
    }

    private void quit() {
        scanner.close();
        System.out.println(SHUTTING_DOWN);
        System.exit(STATUS);
    }
}
