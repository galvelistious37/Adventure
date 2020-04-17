package com.johnny.pack.age;

public class Player {
    private String name;
    private int hitpoints;
    private int strength;
    private String weapon;
    private int location;
    private int initiative;

    public Player(String name) {
        this.name = name;
        this.hitpoints = 100;
        this.strength = 10;
        this.weapon = "Sword";
        this.location = 1;
        this.initiative = 0;
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
        return "Name: " + name +
                ", Hitpoints: " + hitpoints +
                ", Strength: " + strength +
                ", Weapon: " + weapon +
                ", Location: " + location;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
}
