package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.model.weaponabstractfactory.WeaponFactoryRunner;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Fist;
import com.johnny.pack.age.model.weaponabstractfactory.weaponfactory.FistFactory;

public class Player extends Character {
    private final String name;
    private int hitPoints;
    private int strength;
    private int damage;
    private int location;
    private boolean isAlive;
    private int initiative;
    private Equipable equipable;

    private static final Player INSTANCE = new Player();

    private Player() {
        this.name = "You";
        setHitPoints(100);
        setStrength(5);
        setDamage(Fist.getInstance().getDamage());
        setLocation(1);
        setIsAlive(true);
        setInitiative(0);
        setEquipable(WeaponFactoryRunner.createEquipable(new FistFactory()));
    }

    public static Player getInstance(){
        return INSTANCE;
    }

    @Override
    public String getName() {
        return name;
    }

    public Equipable getEquipable(){
        return equipable;
    }

    @Override
    public void setEquipable(Equipable equipable) {
        this.equipable = equipable;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage){
        this.damage = damage;
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public boolean getIsAlive() {
        return isAlive;
    }

    @Override
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    @Override
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    @Override
    public String toString() {
        return "Player{" +
                "equipable=" + getEquipable().weaponType() + ", hitPoints=" + getHitPoints() +
                ", strength=" + getStrength() + ", location=" + getLocation() +
                ", isAlive=" + getIsAlive() + ", initiative=" + getInitiative() +
                ", name=" + getName() + ", damage=" + getDamage() +
                ", dealDamage=" + dealDamage() + "}";
    }

    @Override
    public int dealDamage(){
        return this.strength + this.damage;
    }


}
