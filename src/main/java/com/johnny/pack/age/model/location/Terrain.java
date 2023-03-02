package com.johnny.pack.age.model.location;

public class Terrain {
    private final int TWO = 2;
    private final int THREE = 3;
    private final String NORTH = "North";
    private final String EAST = "East";
    private final String SOUTH = "South";
    private final String WEST = "West";
    private final String CENTER = "Central";
    private final String DESERT = "Desert";
    private final String MOUNTAIN = "Mountains";
    private final String FOREST = "Forest";
    private final String NOTHING = "";

    private int lat;
    private int lon;
    private String bearing;
    private String terrain;

    public Terrain(int lat, int lon) {
        this.lat = lat;
        this.lon = lon;
        this.bearing = determineBearing(lat, lon);
        this.terrain = determineTerrain();
    }


    private String determineBearing(int x, int y) {
        if(x == THREE && y == TWO){
            return CENTER;
        }
        String longitude = x < THREE ? WEST :
                x > THREE ? EAST : NOTHING;
        String lat = y < TWO ? SOUTH :
                y > TWO ? NORTH : NOTHING;
        String temp = String.format("%s%s", lat, longitude);
        return temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
    }

    private String determineTerrain() {
        if (bearing.equalsIgnoreCase("North") ||
                bearing.equalsIgnoreCase("Northwest") ||
                bearing.equalsIgnoreCase("West") ||
                bearing.equalsIgnoreCase("Southwest")){
            return MOUNTAIN;
        } else if (bearing.equalsIgnoreCase("South") ||
                bearing.equalsIgnoreCase("Southeast")){
            return DESERT;
        } else {
            return FOREST;
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
