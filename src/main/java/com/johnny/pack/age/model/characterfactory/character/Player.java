package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.baseattack.Punch;
import com.johnny.pack.age.controller.attack.baseattack.Stab;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.heavyattack.Hack;
import com.johnny.pack.age.controller.attack.heavyattack.Pummel;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Fist;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Knife;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Sword;

public class Player extends Character {
    private final String name;
    private int hitPoints;
    private int strength;
    private int damage;
    private int location;
    private boolean isAlive;
    private int initiative;
    private Equipable equipable;
    private Attackable attackable;
    private Berserkable berserkable;
    private final Scratchable scratchable;

    private static final Player INSTANCE = new Player();

    private Player() {
        this.name = "You";
        this.hitPoints = 100;
        this.strength = 5;
        this.damage = Fist.getInstance().getDamage();
        this.location = 1;
        this.isAlive = true;
        this.initiative = 0;
        this.equipable = Fist.getInstance();
        this.attackable = Punch.getInstance();
        this.berserkable = Pummel.getInstance();
        this.scratchable = Scratch.getInstance();
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
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", damage=" + damage +
                ", location=" + location +
                ", isAlive=" + isAlive +
                ", initiative=" + initiative +
                ", equipable=" + equipable +
                ", attackable=" + attackable +
                ", berserkable=" + berserkable +
                '}';
    }

    public Equipable determineEquipable(String weapon){
        switch(weapon){
            case "fist" :
                return Fist.getInstance();
            case "knife" :
                return Knife.getInstance();
            case "sword" :
                return Sword.getInstance();
        }
        return Fist.getInstance();
    }

    public Attackable determineAttackable(Equipable equipable){
        if(equipable.weaponType().equals("fist")){
            return Punch.getInstance();
        }
        return Stab.getInstance();
    }

    public Berserkable determineBerserkable(Equipable equipable){
        if(equipable.weaponType().equals("fist")){
            return Pummel.getInstance();
        }
        return Hack.getInstance();
    }

    @Override
    public int dealDamage(){
        return this.strength + this.damage;
    }


}
