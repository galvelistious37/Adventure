package com.johnny.pack.age.controller;

public final class Bite implements Attackable {
    private final String attack;

    private static final Bite INSTANCE = new Bite();

    private Bite(){
        this.attack = "bit";
    }

    public static final Bite getInstance(){
        return INSTANCE;
    }

    @Override
    public String attack() {
        return this.attack;
    }
}
