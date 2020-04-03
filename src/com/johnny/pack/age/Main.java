package com.johnny.pack.age;

import java.io.*;
import java.util.*;

public class Main {
    private static final int NEW = 1;
    private static final int LOAD = 2;
    private static final int QUIT = 3;
    private static final int LOW_CHECK = 0;
    private static final int HIGH_CHECK = 3;
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {
	    play();
        List<String> details;
        Scanner scanner = new Scanner(System.in);
        generateLocations();
//        Player player = getPlayer();
//        loadObject(player);
//        details = scenario(player);
//        System.out.println(details.get(0));
//        System.out.println(details.get(1));
//        System.out.println(details.get(2));

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = 1;
        while(true){
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0){
//                saveObject(player);
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
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
                loc = exits.get(direction);
//                player.setLocation(loc);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }

//        String[] road = "You are standing at the end of a road before a small brick building".split(" ");
//        for(String i : road){
//            System.out.println(i);
//        }
//
//        System.out.println("===============================================================");
//
//        String[] building = "You are inside a building, a well house for a spring".split(", ");
//        for(String i : building){
//            System.out.println(i);
//        }
    }

    private static void play() {
        System.out.println(greet());
        System.out.println(playOptions());
        Player player = fateChosen(continuePlay());
        GamePlay gamePlay = new GamePlay(player);
        gamePlay.playTheGame();
        gamePlay.saveGame(player);
        loadObject();
//        List<String> details = scenario(player);
//        System.out.println(details.get(0));
//        System.out.println(details.get(1));
//        System.out.println(details.get(2));

    }

    /**
     * Deteremine play option from value param
     * @param value
     */
    private static Player fateChosen(int value) {
        Player player;
        if(value == NEW){
            return getPlayer();
        } else {
            System.out.println("Check");
            return  loadObject();
        }
    }

    private static Player getPlayerLoad() {
        return new Player("Jekk Baerr", 50, 10);
    }

    /**
     * Print a greeting
     * @return a String greeting
     */
    private static String greet() {
        return "Make your selection:";
    }

    /**
     * Display the opening menu options
     * @return a String of options
     */
    private static String playOptions() {
        return "1 - New Game \n2 - Load Saved Game \n3 - Quit";
    }

    /**
     * Take player input selection for opening menu options
     * @return an int value of the selection option
     */
    private static int continuePlay() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(scanner.hasNextInt()){
                int value = scanner.nextInt();
                if(value > LOW_CHECK && value < HIGH_CHECK){
                    return value;
                } else if (value == QUIT){
                    quit();
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
     * Quit the game
     */
    private static void quit() {
        System.out.println("Shutting down...");
        System.exit(0);
    }


//    private static void saveObject(ISaveable objectToSave) {
//        for(int i = 0; i < objectToSave.write().size(); i++){
//            System.out.println("Saving " + objectToSave.write().get(i));
//        }
//    }

    private static Player loadObject(){
        String directory = ("./Files/");
        String fileName = ("save_file.txt");
        String absolutePath = directory + fileName;
        Scanner scanner = new Scanner(System.in);
        String enterName = scanner.nextLine();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = reader.readLine();
            String[] splitLine = line.split(",");
            while(line != null){
                if(enterName.trim().equals(splitLine[0].trim())){
                    String name = splitLine[0];
                    int hitpoints = Integer.parseInt(splitLine[1]);
                    int strength = Integer.parseInt(splitLine[2]);
                    String weapon = splitLine[3];
                    int location = Integer.parseInt(splitLine[4]);
                    return new Player(name, hitpoints, strength, weapon, location);
                }
                line = reader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;


// read the content from file

//        System.out.println("Loading...");
////        ArrayList<String> values = readValues();
//        ArrayList<String> values = new ArrayList<>();
//        values.add(((Player) objectToLoad).getName());
//        values.add(String.valueOf(((Player) objectToLoad).getHitpoints()));
//        values.add(String.valueOf(((Player) objectToLoad).getStrength()));
//        values.add(((Player) objectToLoad).getWeapon());
//        values.add(String.valueOf(((Player) objectToLoad).getLocation()));
//        objectToLoad.read(values);
//        return (Player) objectToLoad;
    }

    private static void generateLocations() {
        Map<String, Integer> tempExit = new HashMap<>();
        locations.put(0, new Location(0, "You are sitting in front of a computer", null));

        tempExit = new HashMap<>();
        tempExit.put("N", 100);
        tempExit.put("E", 2);
        tempExit.put("S", -100);
        tempExit.put("W", 3);
        locations.put(1, new Location(1, "You have woken up in the middle of a cold, snow-covered forest", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);


        tempExit = new HashMap<>();
        tempExit.put("N", 5);
        locations.put(2, new Location(2, "You are at top of a hill", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 1);
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        locations.put(4, new Location(4, "You are in a valley beside a stream", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        locations.put(5, new Location(5, "You are in the forest", tempExit));
    }

    /**
     * Print scenario details
     * @param player object
     * @return a list of strings detailing the scenario
     */
    private static List<String> scenario(Player player) {
        List<String> playerDetails = new ArrayList<String>();
        playerDetails.add("Your name is " + player.getName());
        playerDetails.add("You have " + player.getHitpoints() + " points of health");
        playerDetails.add("You can deal up to " + player.getStrength() + " points of " +
                "damage with your " + player.getWeapon().toLowerCase());
        return playerDetails;
    }

    /**
     * Generate a new player
     * @return a new player object
     */
    private static Player getPlayer() {
        String name = getNewPlayerName();
        return new Player(name, 100, 10);
    }

    /**
     * Take user input for a new player's name and return
     * a string value.
     * @return user input as a string.
     */
    private static String getNewPlayerName() {
        System.out.println("Enter your player's name:");
        Scanner scanner = new Scanner(System.in);
        String name = "";
        boolean quit = false;
        while(!quit){
            name = scanner.nextLine();
            if(name.equals("")){
                System.out.println("Enter a name:");
            } else {
                quit = true;
            }
        }
        return name;
    }
}
