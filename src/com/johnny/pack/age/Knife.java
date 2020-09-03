package com.johnny.pack.age;

public final class Knife implements Equipable {
    private final String weapon;
    private final int damage;

    private static final Knife INSTANCE = new Knife();

    private Knife() {
        this.weapon = "knife";
        this.damage = 8;
    }

    public static final Knife getInstance(){
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
