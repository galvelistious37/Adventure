package com.johnny.pack.age.controller.attack;

public final class Punch implements Attackable {
    private final String attack;

    private static final Punch INSTANCE = new Punch();

    private Punch(){
        this.attack = "punched";
    }

    public static final Punch getInstance(){
        return INSTANCE;
    }

    @Override
    public String attack() {
        return this.attack;
    }
}
