package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.controller.attack.Equipable;
import com.johnny.pack.age.controller.attack.*;
import com.johnny.pack.age.controller.attack.attack.Punch;
import com.johnny.pack.age.controller.attack.attack.Stab;
import com.johnny.pack.age.controller.attack.berserk.Hack;
import com.johnny.pack.age.controller.attack.berserk.Pummel;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.weapon.Fist;
import com.johnny.pack.age.controller.attack.weapon.Knife;
import com.johnny.pack.age.controller.attack.weapon.Sword;

public class Player extends Character {
    private AttackFactory attackFactory;
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
    private Scratchable scratchable;

    private static final Player INSTANCE = new Player(
            "You", 100, 5, Fist.getInstance().getDamage(),
            1, true, 0, new FistAttackFactory());

    private Player(String name,
                   int hitPoints,
                   int strength,
                   int damage,
                   int location,
                   boolean isAlive,
                   int initiative,
                   AttackFactory af) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.damage = damage;
        this.location = location;
        this.isAlive = isAlive;
        this.initiative = initiative;
        this.attackFactory = af;
        equipable = attackFactory.getEquipable();
        attackable = attackFactory.getAttack();
        berserkable = attackFactory.getBerserk();
        scratchable = attackFactory.getScratch();
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

    private void setScratchable(Scratchable scratchable) {
        this.scratchable = scratchable;
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

    public void equipNewWeapon(String weapon){
        switch(weapon){
            case "knife" :
                System.out.println("myeah... knife");
                updatePlayerAttack(new KnifeAttackFactory());
                System.out.println("myeah.... getWeapon: " + getEquipable().weaponType());
                break;
            case "sword" :
                updatePlayerAttack(new SwordAttackFactory());
                break;
            case "stinger" :
                updatePlayerAttack(new StingerAttackFactory());
                break;
            case "teeth" :
                updatePlayerAttack(new TeethAttackFactory());
                break;
            default:
                updatePlayerAttack(new FistAttackFactory());
        }
    }

    private void updatePlayerAttack(AttackFactory attackFactory){
        System.out.println("why you no update? " + attackFactory.getEquipable().weaponType());
        setEquipable(attackFactory.getEquipable());
        setAttackable(attackFactory.getAttack());
        setBerserkable(attackFactory.getBerserk());
        setScratchable(attackFactory.getScratch());
        setDamage(attackFactory.getEquipable().getDamage());
    }

    @Override
    public int dealDamage(){
        return this.strength + this.damage;
    }

    public void setAttackFactory(AttackFactory attackFactory){
        this.attackFactory = attackFactory;
    }


}
