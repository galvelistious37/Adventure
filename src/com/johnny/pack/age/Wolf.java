package com.johnny.pack.age;

public class Wolf extends Enemy{

    public Wolf() {
        attackable = new Bite();
        berserkable = new Maul();
        hitpoints = 30;
        strength = 10;
        weapon = "teeth";
        isAlive = true;
    }

    @Override
    public String displayEnemy() {
        return "Wolf";
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

