package com.johnny.pack.age;

public class Ogre extends Enemy {

    public Ogre() {
        attackable = new Punch();
        berserkable = new Pummel();
        hitpoints = 50;
        strength = 20;
        weapon = "fists";
        location = 3;
        isAlive = true;
    }

    @Override
    public String displayEnemy() {
        return "Ogre";
    }

    @Override
    public void performAttack() {
        System.out.println(attackable.attack());
    }

    @Override
    public void performBersekable() {
        System.out.println(berserkable.goBersek());
    }
}
