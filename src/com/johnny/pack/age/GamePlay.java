package com.johnny.pack.age;

import java.util.*;

class GamePlay {
    // Constants
    private static final String GREETING = "Welcome to the Greatest Adventure Game Ever!!!";
    private static final String WRONG_DIRECTION = "You cannot go in that direction";
    private static final String CHOOSE_PLAYER_NAME = "What is your player's name?";
    private static final String CHOOSE_VALID_OPTION = "Select a valid option!";
    private static final String SHUTTING_DOWN = "Shutting down...";
    private static final String COLON_SEPARATOR = ": ";
    private static final String QUIT_GAME = "Quit";
    private static final String NEW_GAME = "Play";
    private static final int STATUS = 0;
    private static final int PLAY = 1;
    private static final int QUIT = 2;

    private PlayerMapBuilder playerMapBuilder;
    private Move availableExits;

    // Global Variables
    private Scanner scanner;
    private Map<Integer, Location> locationMap;
    private Map<Integer, List<Enemy>> enemyMap;

    /**
     * GamePlay Constructor instantiates global variables
     */
    GamePlay() {
        scanner = new Scanner(System.in);
        playerMapBuilder = new PlayerMapBuilder();
        LocationBuilder locationBuilder = new LocationBuilder();
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        locationMap = locationBuilder.generateLocationMap();
        enemyMap = enemyBuilder.getEnemyMap();
        availableExits = new Move();
    }

    /**
     * Greet and play the game
     */
    void play() {
        displayGreeting();
        displayStartMenu();
        if(!keepPlaying()){
            shutdown();
        }
        setPlayer();
        playTheGame();
    }

    /**
     * Print a greeting to the screen
     */
    private void displayGreeting() {
        System.out.println(GREETING);
    }

    /**
     * Calls a method to generate options. Display options
     */
    private void displayStartMenu() {
        List<String> options = createOptions();
        for(String option : options){
            System.out.println(option);
        }
    }

    /**
     * Create the options for the Start Menu.
     * @return List of String options.
     */
    private List<String> createOptions() {
        List<String> generateOptions = new ArrayList<>();
        generateOptions.add(PLAY + COLON_SEPARATOR + NEW_GAME);
        generateOptions.add(QUIT + COLON_SEPARATOR + QUIT_GAME);
        return generateOptions;
    }

    /**
     * Take user input and determine whether to
     * play or quit.
     * @return a boolean value
     */
    private boolean keepPlaying() {
        while(true){
            if(scanner.hasNextInt()){
                int startMenuInput = scanner.nextInt();
                scanner.nextLine();
                if(startMenuInput == PLAY){
                    return true;
                } else if (startMenuInput == QUIT){
                    return false;
                } else {
                    selectValidOptionText();
                }
            } else {
                selectValidOptionText();
            }
            scanner.nextLine();
        }
    }

    /**
     * Display text to choose a valid option
     */
    private void selectValidOptionText() {
        System.out.println(CHOOSE_VALID_OPTION);
    }

    /**
     * Accept user input for player name
     */
    private void setPlayer() {
        System.out.println(CHOOSE_PLAYER_NAME);
        String userInput = scanner.nextLine();
        playerMapBuilder.loadPlayer(userInput);
    }

    /**
     * Main game play method. This method loops through the logic
     * of each round.
     */
    private void playTheGame(){
        int locationNumber = playerMapBuilder.getPlayer().getLocation();
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
                playerMapBuilder.getPlayer().setLocation(locationNumber);
            } else {
                System.out.println(WRONG_DIRECTION);
            }
        }
    }

    private void dealWithEnemies(int locationNumber) {
        Fight fightinStuff = new Fight();
        fightinStuff.initiative(playerMapBuilder.getPlayer(), getEnemiesFromLocation(locationNumber));
        fightinStuff.doFightinStuff(playerMapBuilder.getPlayer(), getEnemiesFromLocation(locationNumber));
    }

    private void fight(int locationNumber) {
        for(Enemy enemy : enemyMap.get(locationNumber)){
            if(enemy.getInitiative() > playerMapBuilder.getPlayer().getInitiative()){
                System.out.println(enemy.displayEnemy());
                enemy.performBersekable();
                System.out.println("you to death with it's " + enemy.weapon);
            } else {
                System.out.println(playerMapBuilder.getPlayer().getName() +
                        " says: \"Hey Dude\" to the " + enemy.displayEnemy());
            }
        }
    }

    private boolean areEnemiesPresent(int locationNumber) {
        return enemyMap.containsKey(locationNumber);
    }

    private List<Enemy> getEnemiesFromLocation(int locationNumber){
        return enemyMap.get(locationNumber);
    }

    private void displayEnemyDetails(int locatioNumber) {
        String singularOrPlural = "y is";
        if(enemyMap.get(locatioNumber).size() > 1){
            singularOrPlural = "ies are";
        }
        System.out.println(enemyMap.get(locatioNumber).size()
                + " enem" + singularOrPlural + " here.");
    }

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
        System.out.println(playerMapBuilder.getPlayerDetails());
    }

    private void quit() {
        saveGame();
        shutdown();
    }

    private void saveGame(){
        playerMapBuilder.savePlayerDetails();
    }

    private void shutdown(){
        scanner.close();
        System.out.println(SHUTTING_DOWN);
        System.exit(STATUS);
    }
}
