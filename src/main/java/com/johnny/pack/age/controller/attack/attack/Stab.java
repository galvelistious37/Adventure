package com.johnny.pack.age.controller.attack.attack;

import com.johnny.pack.age.controller.attack.Attackable;

public final class Stab implements Attackable {
    private final String attack;

    private static final Stab INSTANCE = new Stab();

    private Stab(){
        this.attack = "stabbed";
    }

    public static final Stab getInstance(){
        return INSTANCE;
    }

    @Override
    public String attack() {
        return this.attack;
    }
}
