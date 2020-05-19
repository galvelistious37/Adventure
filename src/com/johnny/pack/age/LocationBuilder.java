package com.johnny.pack.age;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationBuilder {
    private static final String WEST = "West";
    private static final String SOUTH = "South";
    private static final String SOUTHWEST = "Southwest";
    private static final String SOUTHEAST = "Southeast";
    private static final String EAST = "East";
    private static final String NORTH = "North";
    private static final String NORTHWEST = "Northwest";
    private static final String NORTHEAST = "Northeast";
    private static final String CENTER = "Central";
    private static final String DESERT = "Desert";
    private static final String MOUNTAIN = "Mountains";
    private static final String FOREST = "Forest";
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final String SPACE = " ";

    protected Map<Integer, Location> generateLocationMap(){
        LocationNumberBuilder locationNumberBuilder = new LocationNumberBuilder();
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        List<Integer> locationNumbers = locationNumberBuilder.getLocationNumbersList();
        for(Integer locNumber : locationNumbers){
            tempLocationMap.put(locNumber, new Location(locNumber, generateLocationDescription(locNumber), getExits(locNumber, locationNumbers))) ;
        }
        return tempLocationMap;
    }

    private String generateLocationDescription(Integer locNumber) {
        return getCoordinates(locNumber);
    }

    private String getCoordinates(int locNumber){
        int x = getX(locNumber);
        int y = getY(locNumber);
        String bearing = getBearing(x,y);
        String terrain = getTerrain(bearing);
        return bearing + SPACE + terrain;
    }

    private int getX(Integer locNumber) {
        return locNumber % 10;
    }

    private int getY(Integer locNumber) {
        if(locNumber < 10){
            return 0;
        } else {
            while(locNumber >= 10){
                locNumber /= 10;
            }
        }
        return locNumber;
    }


    private String getBearing(int x, int y) {
        if(x == THREE && y < TWO){
            return SOUTH;
        } else if (x == THREE && y > TWO){
            return NORTH;
        } else if (x > THREE && y == TWO){
            return EAST;
        } else if (x < THREE && y == TWO){
            return WEST;
        } else if (x < THREE && y < TWO){
            return SOUTHWEST;
        } else if (x > THREE && y < TWO){
            return SOUTHEAST;
        } else if (x < THREE && y > TWO){
            return NORTHWEST;
        } else if (x > THREE && y > TWO){
            return NORTHEAST;
        } else {
            return CENTER;
        }
    }

    private String getTerrain(String bearing) {
        if (bearing.equals("North") || bearing.equals("Northwest") ||
                bearing.equals("West") || bearing.equals("Southwest")){
            return MOUNTAIN;
        } else if (bearing.equals("South") || bearing.equals("Southeast")){
            return DESERT;
        } else {
            return FOREST;
        }
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
