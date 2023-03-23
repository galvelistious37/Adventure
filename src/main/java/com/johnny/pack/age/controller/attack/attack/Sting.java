package com.johnny.pack.age.controller.attack.attack;

import com.johnny.pack.age.controller.attack.Attackable;

public final class Sting implements Attackable {
    private final String attack;

    private static final Sting INSTANCE = new Sting();

    private Sting(){
        this.attack = "stung";
    }

    public static final Sting getInstance(){
        return INSTANCE;
    }

    @Override
    public String attack() {
        return this.attack;
    }
}
