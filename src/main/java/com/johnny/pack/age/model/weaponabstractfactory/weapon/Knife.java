package com.johnny.pack.age.model.weaponabstractfactory.weapon;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;

public final class Knife implements Equipable {
    private final String weapon;
    private final int damage;
    private Scratchable scratchable;
    private Attackable attackable;
    private Berserkable berserkable;
    private int location;

    private Knife() {
        this.weapon = "knife";
        this.damage = 8;
        this.location = 2;
    }

    public static final Knife getInstance(){
        return new Knife();
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }

    @Override
    public int getDamage(){
        return this.damage;
    }

    @Override
    public void attachScratchable(Scratchable scratchable) {
        this.scratchable = scratchable;
    }

    @Override
    public void attachAttackable(Attackable attackable) {
        this.attackable = attackable;
    }

    @Override
    public void attachBerserkable(Berserkable berserkable) {
        this.berserkable = berserkable;
    }

    @Override
    public Scratchable getScratchable() {
        return scratchable;
    }

    @Override
    public Attackable getAttackable() {
        return attackable;
    }

    @Override
    public Berserkable getBerserkable() {
        return berserkable;
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation(int location){
        this.location = location;
    }
}
