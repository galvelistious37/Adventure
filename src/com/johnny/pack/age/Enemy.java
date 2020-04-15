package com.johnny.pack.age;

public abstract class Enemy {
    IAttackable attackable;
    IBerserkable berserkable;
    int hitpoints;
    int strength;
    String weapon;
    int location;
    boolean isAlive;

    public Enemy(){

    }

    public abstract String displayEnemy();

    public void performAttack(){
        System.out.println(attackable.attack());
    }

    public void performBersekable(){

        System.out.println(berserkable.goBersek());
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
}
