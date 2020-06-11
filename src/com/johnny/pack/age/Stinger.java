package com.johnny.pack.age;

public class Stinger implements Equipable {
    private String weapon;

    public Stinger() {
        this.weapon = "stinger";
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }
}
