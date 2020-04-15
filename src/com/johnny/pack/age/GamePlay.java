package com.johnny.pack.age;

import java.io.*;
import java.util.*;

class GamePlay {
    // Constants
    private static final int PLAY = 1;
    private static final int QUIT = 2;
    private static final String NEW_GAME = "Play";
    private static final String QUIT_GAME = "Quit";

    // Global Variables
    private Scanner scanner;
    private Map<Integer, Location> locationMap;
    private Player player;
    private Map<String, Player> playerMap;
    private Map<Integer, List<Enemy>> enemyMap;

    /**
     * GamePlay Constructor instantiates global variables
     */
    GamePlay() {
        scanner = new Scanner(System.in);
        playerMap = populatePlayerMap();
        enemyMap = populateEnemyMap();
        locationMap = populateLocationMap();
    }

    /**
     * Main play method to run the game
     */
    void play() {
        displayGreeting();
        displayStartMenu();
        if(!getStartMenuInput()){
            shutdown();
        }
        player = setPlayer();
        playTheGame();
    }

    /**
     * Accept user input for player name
     * @return a player object with the name from user input
     */
    private Player setPlayer() {
        System.out.println("What is your player's name?");
        String userInput = scanner.nextLine();
        if(playerMap.containsKey(userInput)){
            return playerMap.get(userInput);
        } else {
            System.out.println("Creating new player");
            return new Player(userInput);
        }
    }

    /**
     * Print a greeting to the screen
     */
    private void displayGreeting() {
        String message = "Hello! " +
                "Select an option from below:";
        System.out.println(message);
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
        generateOptions.add(PLAY + ": " + NEW_GAME);
        generateOptions.add(QUIT + ": " + QUIT_GAME);
        return generateOptions;
    }

    /**
     * Take user input and determine whether to
     * play or quit.
     * @return a boolean value
     */
    private boolean getStartMenuInput() {
        while(true){
            if(scanner.hasNextInt()){
                int startMenuInput = scanner.nextInt();
                scanner.nextLine();
                if(startMenuInput == PLAY){
                    return true;
                } else if (startMenuInput == QUIT){
                    return false;
                } else {
                    System.out.println("Select a valid option");
                }
            } else {
                System.out.println("Select a valid option");
            }
            scanner.nextLine();
        }
    }

    /**
     * Return a list of locations
     * @return list of locations
     */
    private Map<Integer, Location> populateLocationMap() {
        LocationBuilder locationBuilder = new LocationBuilder();
        return locationBuilder.generateLocationMap();
    }

    private Map<String, Player> populatePlayerMap() {
        PlayerBuilder playerBuilder = new PlayerBuilder();
        return playerBuilder.fillPlayerMap();
    }

    private Map<Integer, List<Enemy>> populateEnemyMap() {
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        return enemyBuilder.getIntegerListMap();
    }

    private void playTheGame(){
        System.out.println("Your player deetz");
        System.out.println("Name: " + player.getName());
        System.out.println("Hit Points: " + player.getHitpoints());
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Weapon: " + player.getWeapon());
        System.out.println("Location: " + player.getLocation());

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = player.getLocation();
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
                player.setLocation(loc);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
//        saveGame(player);
    }

    private void saveGame(){
        System.out.println("Saving your game...");
        FilePath path = new FilePath();

        if(!playerMap.containsKey(player.getName())){
            playerMap.put(player.getName(), player);
        }

        try(FileWriter fw = new FileWriter(path.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            for(String key : playerMap.keySet()){
                String saveString;
                String separator = ",";
                saveString = playerMap.get(key).getName() + separator +
                        playerMap.get(key).getHitpoints() + separator +
                        playerMap.get(key).getStrength() + separator +
                        playerMap.get(key).getWeapon() + separator +
                        playerMap.get(key).getLocation();

                out.println(saveString);
            }
        } catch (IOException e) {
            System.out.println("Exception???");
            //exception handling left as an exercise for the reader
        }
    }

    private void quit() {
        saveGame();
        shutdown();
    }

    private void shutdown(){
        scanner.close();
        System.out.println("Shutting down...");
        System.exit(0);
    }
}
