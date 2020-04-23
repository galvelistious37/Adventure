package com.johnny.pack.age;

public class Bandit extends Enemy {

    public Bandit() {
        attackable = new Stab();
        berserkable = new Hack();
        hitpoints = 40;
        strength = 20;
        weapon = "knife";
        isAlive = true;
    }

    @Override
    public String displayEnemy() {
        return "Bandit";
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
