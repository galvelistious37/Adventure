package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable {
    private String name;
    private int hitpoints;
    private int strength;
    private String weapon;
    private int location;

    public Player(String name) {
        this.name = name;
        this.hitpoints = 100;
        this.strength = 10;
        this.weapon = "Sword";
        this.location = 1;
    }

    public Player(String name, int hitpoints, int strength, String weapon, int location) {
        this.name = name;
        this.hitpoints = hitpoints;
        this.strength = strength;
        this.weapon = weapon;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitpoints=" + hitpoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public List<String> write() {
        List<String> values = new ArrayList<String>();
        values.add(0, this.name);
        values.add(1, String.valueOf(this.hitpoints));
        values.add(2, String.valueOf(this.strength));
        values.add(3, this.weapon);
        values.add(4, String.valueOf(this.location));
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        if(savedValues != null && savedValues.size() > 0){
            this.name = savedValues.get(0);
            this.hitpoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));
            this.weapon = savedValues.get(3);
            this.location = Integer.parseInt(savedValues.get(4));
        }
    }
}
