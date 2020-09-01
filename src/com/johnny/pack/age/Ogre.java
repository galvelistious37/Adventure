package com.johnny.pack.age;

public class Ogre extends Character {
    private Equipable equipable;
    private Attackable attackable;
    private Berserkable berserkable;
    private int hitpoints;
    private int strength;
    private int location;
    private boolean isAlive;
    private int initiative;
    private String name;

    private Ogre(int hitpoints, int strength, String name) {
        this.equipable = new Fist();
        this.attackable = new Punch();
        this.berserkable = new Pummel();
        this.hitpoints = hitpoints;
        this.strength = strength;
        this.location = 0;
        this.isAlive = true;
        this.initiative = 0;
        this.name = name;
    }

    public static Ogre normalOgre(){
        return new Ogre(25, 12, "Normal Ogre");
    }

    public static Ogre superOgre(){
        return new Ogre(35, 17, "Super Crazy Ogre");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String weaponType() {
        return equipable.weaponType();
    }

    @Override
    public void setEquipable(Equipable equipable) {
        this.equipable = equipable;
    }

    @Override
    public String performAttack() {
        return attackable.attack();
    }

    @Override
    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }

    @Override
    public String performBersek() {
        return berserkable.goBersek();
    }

    @Override
    public void setBerserkable(Berserkable berserkable) {
        this.berserkable = berserkable;
    }

    @Override
    public int getHitpoints() {
        return hitpoints;
    }

    @Override
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
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
        return "Ogre{" +
                "equipable=" + equipable +
                ", attackable=" + attackable +
                ", berserkable=" + berserkable +
                ", hitpoints=" + hitpoints +
                ", strength=" + strength +
                ", location=" + location +
                ", isAlive=" + isAlive +
                ", initiative=" + initiative +
                ", name='" + name + '\'' +
                '}';
    }
}
