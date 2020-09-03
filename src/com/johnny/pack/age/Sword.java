package com.johnny.pack.age;

public final class Sword implements Equipable {
    private final String weapon;
    private final int damage;

    private static final Sword INSTANCE = new Sword();

    private Sword() {
        this.weapon = "sword";
        this.damage = 12;
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
}
