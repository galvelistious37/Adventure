package com.johnny.pack.age;

public class Player extends Character{
    private Equipable equipable;
    private Attackable attackable;
    private Berserkable berserkable;
    private int hitpoints;
    private int strength;
    private int damage;
    private int location;
    private boolean isAlive;
    private int initiative;
    private String name;

    private static final Player INSTANCE = new Player();

    private Player() {
        this.equipable = Fist.getInstance();
        this.attackable = Punch.getInstance();
        this.berserkable = Pummel.getInstance();
        this.hitpoints = 100;
        this.strength = 5;
        this.damage = Fist.getInstance().getDamage();
        this.location = 1;
        this.isAlive = true;
        this.initiative = 0;
        this.name = "You";
    }

    public static Player getInstance(){
        return INSTANCE;
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
        return name + " have " + hitpoints + " hitpoints \n" +
                "Weapon: " + equipable.weaponType();
    }

    private Equipable determineEquipable(String weapon){
        switch(weapon){
            case "knife" :
                return Knife.getInstance();
            case "sword" :
                return Sword.getInstance();
        }
        return Fist.getInstance();
    }

    private Attackable determineAttackable(Equipable equipable){
        if(equipable.weaponType().equals("fist")){
            return Punch.getInstance();
        }
        return Stab.getInstance();
    }

    private Berserkable determineBerserkable(Equipable equipable){
        if(equipable.weaponType().equals("fist")){
            return Pummel.getInstance();
        }
        return Hack.getInstance();
    }

    public int dealDamage(){
        return this.strength + this.damage;
    }


}
