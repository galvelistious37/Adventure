package com.johnny.pack.age;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GamePlay {
    Player player;
    Map<String, Player> playerMap;
    Map<String, Monster> monsterMap;
    private static Map<Integer, Location> locations;

    public GamePlay() {
        playerMap = new HashMap<>();
        monsterMap = new HashMap<>();
        locations = generateLocations();
    }

//    public GamePlay (Player player){
//        this.player = player;
//    }

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
            System.out.println(locations.get(loc).getDescription() + " first call");
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
                if(direction.equals("Q")){
                    saveGame();
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
        System.out.println(playerMap.size());
//        System.out.println(playerMap.get(player.getName()).getName());
        if(playerMap.containsKey(player.getName())){
            playerMap.replace(player.getName(), player);
        } else {
            playerMap.put(player.getName(), player);
        }

        try(FileWriter fw = new FileWriter(path.getAbsolutePath(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            String saveString = "";
            for(String key : playerMap.keySet()){
                for(int i = 0; i < playerMap.get(key).write().size(); i++){
                    saveString = saveString + (playerMap.get(key).write().get(i)) + ",";
                 }
                out.println(saveString);
            }

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }

    public void playNewGame() {
        Scanner scanner = new Scanner(System.in);
        String enterName = scanner.nextLine();
        player = new Player(enterName, 100, 10);
        playTheGame();
    }

    public void loadGame() {
        System.out.println("What is your player's name?");
        Scanner scanner = new Scanner(System.in);
        String enterName = scanner.nextLine();
        Map<String, Player> playerMap = loadPlayerMap();
        player = playerMap.get(enterName);
        Map<String, Monster> monsterMap = loadMonsterMap(enterName);

        playTheGame();
    }

    private Map<String, Player> loadPlayerMap() {
        Map<String, Player> tempMap = new HashMap<>();
        FilePath path = new FilePath();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(path.getAbsolutePath()));
            String line = reader.readLine();
            while(line != null){
                String[] splitLine = line.split(",", -1);
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

    private Map<String, Monster> loadMonsterMap(String enterName) {
        Map<String, Monster> monsterMap = new HashMap<>();
        return monsterMap;
    }

    public void quit() {
        saveGame();
        System.out.println("Shutting down...");
        System.exit(0);
    }

    private Map<Integer, Location> generateLocations() {
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        Map<String, Integer> tempExit;
        tempLocationMap.put(0, new Location(0, "You are sitting in front of a computer", null));

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

}
