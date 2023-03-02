package com.johnny.pack.age.model.location;

class Coordinates {
    private final int TEN = 10;
    private int lat;
    private int lon;

    Coordinates(int id) {
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

    int getLatitude() {
        return lat;
    }

    int getLongitude() {
        return lon;
    }
}
