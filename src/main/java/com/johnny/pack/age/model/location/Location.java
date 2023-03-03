package com.johnny.pack.age.model.location;

import com.johnny.pack.age.controller.GameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Location {
    private final Map<String, Integer> exits;

    private int id;
    private Terrain terrain;

    public Location(int id) {
        this.id = id;
        this.exits = getExits(this.id);
        this.exits.put("Q", 0);
        Coordinates coords = new Coordinates(this.id);
        terrain = new Terrain(coords.getLatitude(), coords.getLongitude());
    }

    /**
     * Return a map of available exits for a given location
     * @param locationId - current map location
     * @return - map of available exits
     */
    private Map<String, Integer> getExits(int locationId) {
        // Populate a temp map with all directions
        Map<String, Integer> mapExits = new HashMap<>();
        mapExits.put("N", locationId + 10);
        mapExits.put("E", locationId + 1);
        mapExits.put("S", locationId - 10);
        mapExits.put("W", locationId - 1);

        // Filter out exits which are not available
        return mapExits.entrySet().stream()
                .filter(id -> isExitThere(id.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Check if id is present in the gameMap
     * @param id - location id
     * @return - boolean value if id is in the gameMap
     */
    private boolean isExitThere(int id){
        return GameMap.gameMap.contains(id);
    }


    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getId(){
        return id;
    }
}
