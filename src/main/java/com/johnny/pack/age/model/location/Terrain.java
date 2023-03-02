package com.johnny.pack.age.model.location;

public class Terrain {

    private String bearing;
    private String terrain;

    Terrain(int lat, int lon) {
        this.bearing = determineBearing(lat, lon);
        this.terrain = determineTerrain();
    }

    private String determineBearing(int x, int y) {
        if(x == 3 && y == 2){
            return "Central";
        }
        String longitude = x < 3 ? "West" :
                x > 3 ? "East" : "";
        String lat = y < 2 ? "South" :
                y > 2 ? "North" : "";
        String temp = String.format("%s%s", lat, longitude);
        return temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
    }

    private String determineTerrain() {
        if (bearing.equalsIgnoreCase("North") ||
                bearing.equalsIgnoreCase("Northwest") ||
                bearing.equalsIgnoreCase("West") ||
                bearing.equalsIgnoreCase("Southwest")){
            return "Mountains";
        } else if (bearing.equalsIgnoreCase("South") ||
                bearing.equalsIgnoreCase("Southeast")){
            return "Desert";
        } else {
            return "Forest";
        }
    }

    public String getBearing() {
        return bearing;
    }

    public String getTerrain() {
        return terrain;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getBearing(), getTerrain());
    }
}
