package com.johnny.pack.age;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class GamePlay {
    // Constants
    private static final int PLAY = 1;
    private static final int QUIT = 2;
    private static final String NEW_GAME = "Play";
    private static final String QUIT_GAME = "Quit";

    // Global Variables
    Scanner scanner;
    Player player;
    Map<String, Player> playerMap;
    Map<String, Monster> monsterMap;
    private static Map<Integer, Location> locationMap;

    public GamePlay() {
        scanner = new Scanner(System.in);
        playerMap = populatePlayerMap();
        monsterMap = populateMonsterMap();
        locationMap = generateLocations();
    }

    /**
     * Main play method to run the game
     */
    public void play() {
        displayGreeting();
        displayStartMenu();
        if(!getStartMenuInput()){
            shutdown();
        }
        player = setPlayer();
        playTheGame();
    }

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
     * Call a method to generate options.
     * Display those options on the screen
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
     * Procees, Sir.
     */
    private void proceed() {
        for(String key : playerMap.keySet()){
            System.out.println(playerMap.get(key).toString());
        }
    }

    private void populateLocationMap() {

    }

    private Map<String, Player> populatePlayerMap() {
        Map<String, Player> tempMap = new HashMap<>();
        FilePath path = new FilePath();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(path.getAbsolutePath()));
            String line = reader.readLine();
            while(line != null){
                String[] splitLine = line.split(",");
                String name = splitLine[0];
                int hitpoints = Integer.parseInt(splitLine[1]);
                int strength = Integer.parseInt(splitLine[2]);
                String weapon = splitLine[3];
                int location = Integer.parseInt(splitLine[4]);

                Player tempPlayer = new Player(name, hitpoints, strength, weapon, location);
                tempMap.put(name, tempPlayer);
                line = reader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return tempMap;
    }





//    private Map<String, Player> populatePlayerMap() {
//
//        return null;
//    }

    private Map<String, Monster> populateMonsterMap() {

        return null;
    }



    public void playTheGame(){
        System.out.println("Your player deetz");
        System.out.println("Name: " + player.getName());
        System.out.println("Hit Points: " + player.getHitpoints());
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Weapon: " + player.getWeapon());
        System.out.println("Location: " + player.getLocation());


        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = player.getLocation();
        while(true){
            System.out.println(locationMap.get(loc).getDescription());
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

    public void saveGame(){
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

    public void playNewGame() {
        Scanner scanner = new Scanner(System.in);
        String enterName = scanner.nextLine();
        player = new Player(enterName);
        playTheGame();
    }
//
//    public void loadGame() {
//        System.out.println("What is your player's name?");
//        Scanner scanner = new Scanner(System.in);
//        String enterName = scanner.nextLine();
//        Map<String, Player> playerMap = loadPlayerMap();
//        player = playerMap.get(enterName);
//        Map<String, Monster> monsterMap = loadMonsterMap(enterName);
//
//        playTheGame();
//    }


    private Map<String, Monster> loadMonsterMap(String enterName) {
        Map<String, Monster> monsterMap = new HashMap<>();
        return monsterMap;
    }

    private Map<Integer, Location> generateLocations() {
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        Map<String, Integer> tempExit;tempLocationMap.put(0, new Location(0, "You are sitting in front of a computer", null));

        tempExit = new HashMap<>();
        tempExit.put("N", 101);
        tempExit.put("E", 2);
        tempExit.put("S", -101);
        tempExit.put("W", 3);
        tempLocationMap.put(1, new Location(1, "You have woken up in the middle of a cold, snow-covered forest", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);


        tempExit = new HashMap<>();
        tempExit.put("N", 5);
        tempLocationMap.put(2, new Location(2, "You are at top of a hill", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 1);
        tempLocationMap.put(3, new Location(3, "You are inside a building, a well house for a small spring", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        tempLocationMap.put(4, new Location(4, "You are in a valley beside a stream", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        tempLocationMap.put(5, new Location(5, "You are in the forest", tempExit));

        return tempLocationMap;
    }

    public void quit() {
        saveGame();
        shutdown();
    }

    private void shutdown(){
        scanner.close();
        System.out.println("Shutting down...");
        System.exit(0);
    }
}
