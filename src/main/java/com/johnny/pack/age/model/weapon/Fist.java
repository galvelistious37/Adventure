package com.johnny.pack.age.model.weapon;

import com.johnny.pack.age.controller.attack.Equipable;

public final class Fist implements Equipable {
    private final String weapon;
    private final int damage;

    private static final Fist INSTANCE = new Fist();

    private Fist() {
        this.weapon = "fists";
        this.damage = 5;
    }

    public static final Fist getInstance(){
        return INSTANCE;
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }

    @Override
    public int getDamage(){
        return this.damage;
    }
}
