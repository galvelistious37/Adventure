package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.model.weaponabstractfactory.WeaponFactoryRunner;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weaponfactory.KnifeFactory;

/**
 * Bandit POJO class
 */
public class Bandit extends Character {
    private Equipable equipable;
    private int hitPoints;
    private int strength;
    private int damage;
    private int location;
    private boolean isAlive;
    private int initiative;
    private final String name;

    private Bandit() {
        setEquipable(WeaponFactoryRunner.createEquipable(new KnifeFactory()));
        setDamage(equipable.getDamage());
        setHitPoints(15);
        setStrength(6);
        setLocation(0);
        setIsAlive(true);
        setInitiative(0);
        this.name = Constant.BANDIT;
    }

    public static Bandit getBandit(){
        return new Bandit();
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
        return "Bandit{" +
                "equipable=" + getEquipable().weaponType() + ", hitPoints=" + getHitPoints() +
                ", strength=" + getStrength() + ", location=" + getLocation() +
                ", isAlive=" + getIsAlive() + ", initiative=" + getInitiative() +
                ", name=" + getName() + ", damage=" + getDamage() +
                ", dealDamage=" + dealDamage() + "}";
    }
}
