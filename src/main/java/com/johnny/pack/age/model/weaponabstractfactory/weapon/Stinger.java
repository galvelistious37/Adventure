package com.johnny.pack.age.model.weaponabstractfactory.weapon;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;

public final class Stinger implements Equipable {
    private final String weapon;
    private final int damage;
    private Scratchable scratchable;
    private Attackable attackable;
    private Berserkable berserkable;

    private static final Stinger INSTANCE = new Stinger();

    private Stinger() {
        this.weapon = "stinger";
        this.damage = 7;
    }

    public static Stinger getInstance(){
        return INSTANCE;
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
}
