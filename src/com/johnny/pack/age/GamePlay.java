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

    // Global Variables
    private Scanner scanner;
    private Map<Integer, Location> locationMap;
//    private Player player;
    private Map<Integer, List<Enemy>> enemyMap;

    /**
     * GamePlay Constructor instantiates global variables
     */
    GamePlay() {
        scanner = new Scanner(System.in);
        playerMapBuilder = new PlayerMapBuilder();
        enemyMap = populateEnemyMap();
        locationMap = populateLocationMap();
    }

    /**
     * Return a list of locations
     * @return list of locations
     */
    private Map<Integer, Location> populateLocationMap() {
        LocationBuilder locationBuilder = new LocationBuilder();
        return locationBuilder.generateLocationMap();
    }

    private Map<Integer, List<Enemy>> populateEnemyMap() {
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        return enemyBuilder.getIntegerListMap();
    }

    /**
     * Main play method to run the game
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

    private void playTheGame(){
        showPlayerDetails();

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = playerMapBuilder.getPlayer().getLocation();
        while(true){
            System.out.println(locationMap.get(loc).getDescription());
            if(enemyMap.containsKey(loc)){
                System.out.println("Uh-oh... looks like trouble");
                System.out.println(enemyMap.get(loc).size() + " enemies are here.");
                for(Enemy enemy : enemyMap.get(loc)){
                    System.out.println("enemy is ... " + enemy.displayEnemy());
                }
                System.out.println(enemyMap.get(loc).get(0).displayEnemy());
                enemyMap.get(loc).get(0).performBersekable();
                System.out.println("you to death!");
            }
            if(loc == 0){
//                saveObject(player);
                break;
            }

            Map<String, Integer> exits = locationMap.get(loc).getExits();
            System.out.println("Available exits are ");
            for(String exit : exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1){
                String[] words = direction.split(" ");
                for(String word : words){
                    if(vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }


            if(exits.containsKey(direction)){
                if(direction.equals("Q")){
                    quit();
                }
                loc = exits.get(direction);
                playerMapBuilder.getPlayer().setLocation(loc);
            } else {
                System.out.println(WRONG_DIRECTION);
            }
        }
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
