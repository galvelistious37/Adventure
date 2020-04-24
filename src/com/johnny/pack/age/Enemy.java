package com.johnny.pack.age;

public abstract class Enemy {
    IAttackable attackable;
    IBerserkable berserkable;
    int hitpoints;
    int strength;
    String weapon;
    int location;
    boolean isAlive;
    int initiative;

    public Enemy(){

    }

    public abstract String displayEnemy();

    public String performAttack(){
        return attackable.attack();
    }

    public String performBersekable(){

        return berserkable.goBersek();
    }

    public void setAttackable(IAttackable attackable){

        this.attackable =  attackable;
    }

    public void setBerserkable(IBerserkable berserkable){

        this.berserkable = berserkable;
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
}
