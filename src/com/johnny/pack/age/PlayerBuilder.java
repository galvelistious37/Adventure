package com.johnny.pack.age;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerBuilder {

    protected Map<String, Player> fillPlayerMap(){
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
}
