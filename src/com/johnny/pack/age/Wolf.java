package com.johnny.pack.age;

public class Wolf extends Enemy{

    public Wolf() {
        attackable = new Bite();
        berserkable = new Maul();
        hitpoints = 30;
        strength = 10;
        weapon = "teeth";
//        location = 3;
        isAlive = true;
    }

    @Override
    public String displayEnemy() {
        return "Wolf";
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

