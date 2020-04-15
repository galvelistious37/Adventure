package com.johnny.pack.age;

public class Scorpion extends Enemy {

    public Scorpion() {
        attackable = new Sting();
        berserkable = new Impale();
        hitpoints = 20;
        strength = 15;
        weapon = "stinger";
//        location = 3;
        isAlive = true;
    }

    @Override
    public String displayEnemy() {
        return "Scorpion";
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
