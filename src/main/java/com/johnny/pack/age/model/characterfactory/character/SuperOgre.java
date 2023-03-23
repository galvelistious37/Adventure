package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.controller.attack.*;
import com.johnny.pack.age.controller.attack.Equipable;
import com.johnny.pack.age.controller.attack.attack.Punch;
import com.johnny.pack.age.controller.attack.berserk.Pummel;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.weapon.Fist;

/**
 * SuperOgre POJO class
 */
public class SuperOgre extends Character {
    private AttackFactory attackFactory = new FistAttackFactory();
    private Equipable equipable;
    private Attackable attackable;
    private Berserkable berserkable;
    private Scratchable scratchable;
    private int hitPoints;
    private int strength;
    private int damage;
    private int location;
    private boolean isAlive;
    private int initiative;
    private String name;

    private SuperOgre(int hitPoints, int strength, String name) {
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.name = name;
        equipable = attackFactory.getEquipable();
        attackable = attackFactory.getAttack();
        berserkable = attackFactory.getBerserk();
        scratchable = attackFactory.getScratch();
        damage = equipable.getDamage();
        location = 0;
        isAlive = true;
        initiative = 0;
    }

    public static SuperOgre getSuperOgre(){
        return new SuperOgre(35, 10, "Super Crazy Ogre");
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
    public Attackable getAttackable() {
        return attackable;
    }

    @Override
    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }

    @Override
    public Berserkable getBerserkable() {
        return berserkable;
    }

    @Override
    public void setBerserkable(Berserkable berserkable) {
        this.berserkable = berserkable;
    }

    @Override
    public Scratchable getScratchable(){
        return scratchable;
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
        return "SuperOgre{" +
                "equipable=" + equipable +
                ", attackable=" + attackable +
                ", berserkable=" + berserkable +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", location=" + location +
                ", isAlive=" + isAlive +
                ", initiative=" + initiative +
                ", name='" + name + '\'' +
                '}';
    }
}
