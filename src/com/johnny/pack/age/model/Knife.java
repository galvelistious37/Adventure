package com.johnny.pack.age.model;

public final class Knife implements Equipable {
    private final String weapon;
    private final int damage;
    private int location;

    private static final Knife INSTANCE = new Knife();

    private Knife() {
        this.weapon = "knife";
        this.damage = 8;
        this.location = 2;
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

    public int getLocation(){
        return this.location;
    }

    public void setLocation(int location){
        this.location = location;
    }
}
