package com.johnny.pack.age;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationBuilder {

    protected Map<Integer, Location> generateLocationMap(){
        LocationNumberBuilder locationNumberBuilder = new LocationNumberBuilder();
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        List<Integer> locationNumbers = locationNumberBuilder.getLocationNumbersList();
        for(Integer locNumber : locationNumbers){
            tempLocationMap.put(locNumber, new Location(locNumber, String.valueOf(locNumber), getExits(locNumber, locationNumbers))) ;
        }
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
}
