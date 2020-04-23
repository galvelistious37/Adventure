package com.johnny.pack.age;

public class Scorpion extends Enemy {

    public Scorpion() {
        attackable = new Sting();
        berserkable = new Impale();
        hitpoints = 20;
        strength = 15;
        weapon = "stinger";
        isAlive = true;
    }

    @Override
    public String displayEnemy() {
        return "Scorpion";
    }

    @Override
    public String performAttack() {
        return attackable.attack();
    }

    @Override
    public String performBersekable() {
        return berserkable.goBersek();
    }
}
