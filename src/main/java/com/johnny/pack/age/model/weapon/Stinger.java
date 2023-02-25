package com.johnny.pack.age.model.weapon;

public final class Stinger implements Equipable {
    private final String weapon;
    private final int damage;

    private static final Stinger INSTANCE = new Stinger();

    private Stinger() {
        this.weapon = "stinger";
        this.damage = 7;
    }

    public static Stinger getInstance(){
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
