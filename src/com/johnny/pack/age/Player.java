package com.johnny.pack.age;

public class Player extends Character{
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

    private static final Player INSTANCE = new Player(
            "You", 100, 5, Fist.getInstance().getDamage(),
            1, true, 0, Fist.getInstance(),
            Punch.getInstance(), Pummel.getInstance(), Scratch.getInstance());

    private Player(String name,
                   int hitPoints,
                   int strength,
                   int damage,
                   int location,
                   boolean isAlive,
                   int initiative,
                   Equipable equipable,
                   Attackable attackable,
                   Berserkable berserkable,
                   Scratchable scratchable) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.damage = damage;
        this.location = location;
        this.isAlive = isAlive;
        this.initiative = initiative;
        this.equipable = equipable;
        this.attackable = attackable;
        this.berserkable = berserkable;
        this.scratchable = scratchable;
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
