package com.johnny.pack.age;

public class Knife implements Equipable {
    private String weapon;

    public Knife() {
        this.weapon = "knife";
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }
}
