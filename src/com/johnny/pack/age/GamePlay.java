package com.johnny.pack.age;

import java.io.*;
import java.util.*;

public class GamePlay {
    // Constants
    private static final int PLAY = 1;
    private static final int QUIT = 2;
    private static final String NEW_GAME = "Play";
    private static final String QUIT_GAME = "Quit";

    // Global Variables
    private Scanner scanner;
    private Player player;
    private Map<String, Player> playerMap;
    private Map<String, Monster> monsterMap;
    private Map<Integer, Location> locationMap;

    public GamePlay() {
        scanner = new Scanner(System.in);
        playerMap = populatePlayerMap();
        monsterMap = populateMonsterMap();
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

    private Map<Integer, Location> populateLocationMap() {
        Map<Integer, Location> tempLocationMap = generateLocations();

//        tempLocationMap.put(0, new Location(0, "You are sitting in front of a computer", null));
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 101);
//        tempExit.put("E", 2);
//        tempExit.put("S", -101);
//        tempExit.put("W", 3);
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 102);
//        tempExit.put("E", 4);
//        tempExit.put("S", -102);
//        tempExit.put("W", 1);
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 104);
//        tempExit.put("S", -104);
//        tempExit.put("W", 2);
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 1);
//
//        tempExit = new HashMap<>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);

        return tempLocationMap;
    }

    private Map<Integer, Location> generateLocations() {
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        List<Integer> locationNumbers = getLocationNumbers();


        for(Integer locNumber : locationNumbers){
            tempLocationMap.put(locNumber, new Location(locNumber, String.valueOf(locNumber), getExits(locNumber, locationNumbers))) ;
        }
//
//        tempLocationMap.put(2, new Location(2, "snow-covered forest at the edge of a cliff", null));
//        tempLocationMap.put(3, new Location(3, "You are inside a building, a well house for a small spring", null));
//        tempLocationMap.put(4, new Location(4, "Snow-covered forest", null));
//        tempLocationMap.put(5, new Location(5, "snow-covered forest", null));
//        tempLocationMap.put(11, new Location(11, "snow-covered forest", null));
//        tempLocationMap.put(12, new Location(12, "snow-covered forest", null));
//        tempLocationMap.put(13, new Location(13, "Snow-covered forest", null));
//        tempLocationMap.put(14, new Location(14, "You are inside a building, a well house for a small spring", null));
//        tempLocationMap.put(15, new Location(15, "snow-covered forest at the edge of a cliff", null));
//        tempLocationMap.put(21, new Location(21, "You are in the forest", null));
//        tempLocationMap.put(22, new Location(22, "snow-covered forest at the edge of a cliff", null));
//        tempLocationMap.put(23, new Location(23, "snow-covered forest at the edge of a cliff", null));
//        tempLocationMap.put(24, new Location(24, "You are inside a building, a well house for a small spring", null));
//        tempLocationMap.put(25, new Location(25, "Snow-covered forest", null));
//        tempLocationMap.put(31, new Location(31, "snow-covered forest", null));
//        tempLocationMap.put(32, new Location(32, "snow-covered forest", null));
//        tempLocationMap.put(33, new Location(33, "snow-covered forest", null));
//        tempLocationMap.put(34, new Location(34, "Snow-covered forest", null));
//        tempLocationMap.put(35, new Location(35, "You are inside a building, a well house for a small spring", null));
//        tempLocationMap.put(41, new Location(41, "snow-covered forest at the edge of a cliff", null));
//        tempLocationMap.put(42, new Location(42, "You are in the forest", null));
//        tempLocationMap.put(43, new Location(43, "snow-covered forest at the edge of a cliff", null));
//        tempLocationMap.put(44, new Location(44, "You are inside a building, a well house for a small spring", null));
//        tempLocationMap.put(45, new Location(45, "Snow-covered forest", null));
        return tempLocationMap;
    }

    private Map<String, Integer> getExits(Integer locNumber, List<Integer> locationNumbers) {
        Map<String, Integer> tempExit = new HashMap<>();
            if(locationNumbers.contains(locNumber + 10)){
                tempExit.put("N", locNumber + 10);
            }
            if(locationNumbers.contains(locNumber + 1)){
                tempExit.put("E", locNumber + 1);
            }
            if(locationNumbers.contains(locNumber - 10)){
                tempExit.put("S", locNumber - 10);
            }
            if(locationNumbers.contains(locNumber - 1)){
                tempExit.put("W", locNumber - 1);
            }
        return tempExit;
    }

    private List<Integer> getLocationNumbers() {
        List<Integer> locNums = new ArrayList<>();
        for(int i = 1; i <= 45; i++){
            System.out.println("location numbers: " + i);
            locNums.add(i);
            if(i % 5 == 0){
                i += 5;
            }
        }
        return locNums;
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

    private Map<String, Monster> loadMonsterMap(String enterName) {
        Map<String, Monster> monsterMap = new HashMap<>();
        return monsterMap;
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
