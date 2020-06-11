package com.johnny.pack.age;

public class Fist implements Equipable {
    private String weapon;

    public Fist() {
        this.weapon = "fists";
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }
}
