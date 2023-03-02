package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.controller.GameMap;
import com.johnny.pack.age.model.location.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationBuilder {

    public static final Map<Integer, Location> mapLocations = generateMapLocation();

    private static Map<Integer, Location> generateMapLocation(){
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        List<Integer> locationNumbers = GameMap.gameMap;
        locationNumbers.forEach((n) -> tempLocationMap.put(n, new Location(n)));
        return tempLocationMap;
    }
}
