package com.johnny.pack.age;

public class Teeth implements Equipable {
    private String weapon;

    public Teeth() {
        this.weapon = "teeth";
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }
}
