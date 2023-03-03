package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.controller.GameMap;
import com.johnny.pack.age.model.location.Location;

import java.util.*;

public class LocationBuilder {

    public static final Map<Integer, Location> mapLocations = generateMapLocation();

    /**
     * Generate a map of key = id, value = Location based on gameMap ids
     * @return - Map<id, Location>
     */
    private static Map<Integer, Location> generateMapLocation(){
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        GameMap.gameMap.forEach((n) -> tempLocationMap.put(n, new Location(n)));
        return tempLocationMap;
    }

    /**
     * Return a random location id from the list of ids.
     * @return - int location id
     */
    public static int getRandomLocation(){
        Random random = new Random();
        return GameMap.gameMap.get(random.nextInt(GameMap.gameMap.size()));
    }
}
