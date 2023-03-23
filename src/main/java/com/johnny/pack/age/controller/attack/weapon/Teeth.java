package com.johnny.pack.age.controller.attack.weapon;

import com.johnny.pack.age.controller.attack.Equipable;

public final class Teeth implements Equipable {
    private final String weapon;
    private final int damage;

    private static final Teeth INSTANCE = new Teeth();

    private Teeth() {
        this.weapon = "teeth";
        this.damage = 9;
    }

    public static Teeth getInstance(){
        return INSTANCE;
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
