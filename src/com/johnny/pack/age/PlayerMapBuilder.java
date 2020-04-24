package com.johnny.pack.age;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerMapBuilder {
    private Map<String, Player> playerMap;
    private Player player;

    public PlayerMapBuilder() {
        this.playerMap = fillPlayerMap();
    }

    private Map<String, Player> fillPlayerMap(){
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


    public void loadPlayer(String userInput) {
        if(playerMap.containsKey(userInput)){
            player = playerMap.get(userInput);
        } else {
            System.out.println("Creating new player");
            player = new Player(userInput);
        }
    }

    public void savePlayerDetails() {
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

    public String getPlayerDetails() {
        return player.toString();
    }

    public Map<String, Player> getPlayerMap() {
        return playerMap;
    }

    public Player getPlayer() {
        return player;
    }
}
