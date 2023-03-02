package com.johnny.pack.age.model.location;

import com.johnny.pack.age.controller.GameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Location {
    private final int locationId;
    private final Map<String, Integer> exits;

    private Coordinates coords;
    private Terrain terrain;

    public Location(int id) {
        this.locationId = id;
        this.exits = getExits(id);
        this.exits.put("Q", 0);
        coords = new Coordinates(id);
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

    private boolean isExitThere(int id){
        return GameMap.gameMap.contains(id);
    }

    public int getLocationId() {
        return locationId;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }

    public Coordinates getCoords() {
        return coords;
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
