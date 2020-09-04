package com.johnny.pack.age;

import java.util.*;

class GamePlay {

    // Global Variables
    private final int TOTAL_ENEMIES = 30;
    private Scanner scanner;
    private Map<Integer, Location> locationMap;
    private List<Character> enemies;
    private Player playerOne;
    private Fight fightObj;

    /**
     * GamePlay Constructor instantiates global variables
     */
    private GamePlay() {
        fightObj = new Fight();
        scanner = new Scanner(System.in);
        playerOne = Player.getInstance();
        locationMap = LocationBuilder.createLocationBuilder().getLocationMap();
        enemies = EnemyBuilder.totalEnemiesList(TOTAL_ENEMIES).getEnemyList();
    }

    private static final GamePlay INSTANCE = new GamePlay();

    public static final GamePlay getInstance(){
        return INSTANCE;
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
        System.out.println("Welcome to the Greatest Adventure Game Ever!!!");
    }

    /**
     * Main game play method. This method loops through the logic
     * of each round.
     */
    private void playTheGame(){
        int locationNumber = playerOne.getLocation();
        int nextLocationNumber;
        Map<String, Integer> exits;
        while (true) {
            displayLocation(locationNumber);
            checkForEnemies(locationNumber);
            exits = locationMap.get(locationNumber).getExits();
            displayAvailableExits(exits);
            String direction = scanner.nextLine().toUpperCase();
            nextLocationNumber = moveInDirection(locationNumber, exits, direction);
            if (locationNumber != nextLocationNumber) {
                resetCharacterInitiative(locationNumber);
                locationNumber = nextLocationNumber;
            }
            checkNewWeapon(locationNumber);
        }
    }

    private void checkNewWeapon(int locationNumber) {
        if(locationNumber == 2){
            playerOne.setEquipable(playerOne.determineEquipable("knife"));
            playerOne.setAttackable(playerOne.determineAttackable(playerOne.getEquipable()));
            playerOne.setBerserkable(playerOne.determineBerserkable(playerOne.getEquipable()));
            playerOne.setDamage(Knife.getInstance().getDamage());
            System.out.println("You now have a " + playerOne.getEquipable().weaponType());
            System.out.println("Attack mode " + playerOne.getAttackable().attack());
            System.out.println("Berserk mode " + playerOne.getBerserkable().goBersek());
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
        System.out.println("Location: " + locationMap.get(locationNumber).getDescription());
    }


    void quit() {
        scanner.close();
        String shutDown = "Shutting down...";
        int status = 0;
        System.out.println(shutDown);
        System.exit(status);
    }
}
