package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.controller.builder.LocationBuilder;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.model.weaponabstractfactory.WeaponFactoryRunner;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weaponfactory.StingerFactory;
import com.johnny.pack.age.model.weaponabstractfactory.weaponfactory.TeethFactory;

/**
 * Wolf POJO class
 */
public class Wolf extends Character {
    private Equipable equipable;
    private int hitPoints;
    private int strength;
    private int damage;
    private int location;
    private boolean isAlive;
    private int initiative;
    private String name;

    private Wolf() {
        setEquipable(WeaponFactoryRunner.createEquipable(new TeethFactory()));
        setDamage(equipable.getDamage());
        setHitPoints(8);
        setStrength(6);
        setLocation(0);
        setIsAlive(true);
        setInitiative(0);
        this.name = Constant.WOLF;
    }

    public static Wolf getWolf(){
        return new Wolf();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Equipable getEquipable() {
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
    public void setDamage(int damage) {
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
    public int dealDamage() {
        return damage + strength;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "equipable=" + getEquipable().weaponType() + ", hitPoints=" + getHitPoints() +
                ", strength=" + getStrength() + ", location=" + getLocation() +
                ", isAlive=" + getIsAlive() + ", initiative=" + getInitiative() +
                ", name=" + getName() + ", damage=" + getDamage() +
                ", dealDamage=" + dealDamage() + "}";
    }
}

