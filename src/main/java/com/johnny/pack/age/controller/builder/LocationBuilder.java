package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.model.location.Location;
import com.johnny.pack.age.model.constant.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationBuilder {

    private Map<Integer, Location> locationMap;

    private LocationBuilder(){
        this.locationMap = generateLocationMap();
    }

    public static LocationBuilder createLocationBuilder(){
        return new LocationBuilder();
    }

    public Map<Integer, Location> getLocationMap() {
        return locationMap;
    }

    private Map<Integer, Location> generateLocationMap(){
        LocationNumberBuilder locationNumberBuilder = new LocationNumberBuilder();
        Map<Integer, Location> tempLocationMap = new HashMap<>();
        List<Integer> locationNumbers = locationNumberBuilder.getLocationNumbersList();
        locationNumbers.forEach((n) -> tempLocationMap.put(n, new Location(n, generateLocationDescription(n), getExits(n, locationNumbers))));
//        for(Integer locNumber : locationNumbers){
//            tempLocationMap.put(locNumber, new Location(locNumber, generateLocationDescription(locNumber), getExits(locNumber, locationNumbers))) ;
//        }
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
        return bearing + Constant.SPACE + terrain;
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
        if(x == Constant.THREE && y < Constant.TWO){
            return Constant.SOUTH;
        } else if (x == Constant.THREE && y > Constant.TWO){
            return Constant.NORTH;
        } else if (x > Constant.THREE && y == Constant.TWO){
            return Constant.EAST;
        } else if (x < Constant.THREE && y == Constant.TWO){
            return Constant.WEST;
        } else if (x < Constant.THREE && y < Constant.TWO){
            return Constant.SOUTHWEST;
        } else if (x > Constant.THREE && y < Constant.TWO){
            return Constant.SOUTHEAST;
        } else if (x < Constant.THREE && y > Constant.TWO){
            return Constant.NORTHWEST;
        } else if (x > Constant.THREE && y > Constant.TWO){
            return Constant.NORTHEAST;
        } else {
            return Constant.CENTER;
        }
    }

    private String getTerrain(String bearing) {
        if (bearing.equals("North") || bearing.equals("Northwest") ||
                bearing.equals("West") || bearing.equals("Southwest")){
            return Constant.MOUNTAIN;
        } else if (bearing.equals("South") || bearing.equals("Southeast")){
            return Constant.DESERT;
        } else {
            return Constant.FOREST;
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
