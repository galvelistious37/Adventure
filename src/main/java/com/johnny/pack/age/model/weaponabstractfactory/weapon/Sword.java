package com.johnny.pack.age.model.weaponabstractfactory.weapon;

public final class Sword implements Equipable {
    private final String weapon;
    private final int damage;
    private int location;

    private Sword() {
        this.weapon = "sword";
        this.damage = 14;
        this.location = 3;
    }

    public static final Sword getInstance(){
        return new Sword();
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
