package com.johnny.pack.age.model.weapon;

import com.johnny.pack.age.controller.attack.Equipable;

public final class Sword implements Equipable {
    private final String weapon;
    private final int damage;
    private int location;

    private static final Sword INSTANCE = new Sword();

    private Sword() {
        this.weapon = "sword";
        this.damage = 14;
        this.location = 3;
    }

    public static final Sword getInstance(){
        return INSTANCE;
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation(int location){
        this.location = location;
    }
}
