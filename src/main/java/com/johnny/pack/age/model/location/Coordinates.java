package com.johnny.pack.age.model.location;

public class Coordinates {
    private final int TEN = 10;
    private int id;
    private int lat;
    private int lon;

    public Coordinates(int id) {
        this.id = id;
        this.lat = determineLat(id);
        this.lon = determineLon(id);
    }

    private int determineLat(int id) {
        return id % TEN;
    }

    private int determineLon(int id) {
        if(id < TEN){
            return 0;
        } else {
            while(id >= TEN){
                id /= TEN;
            }
        }
        return id;
    }

    public int getLatitude() {
        return lat;
    }

    public int getLongitude() {
        return lon;
    }
}
