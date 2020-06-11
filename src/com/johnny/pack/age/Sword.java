package com.johnny.pack.age;

public class Sword implements Equipable {
    private String weapon;

    public Sword() {
        this.weapon = "sword";
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }
}
