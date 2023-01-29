package com.johnny.pack.age.controller.attack;

public final class Scratch implements Scratchable {
    private final String attack;

    private static final Scratch INSTANCE = new Scratch();

    private Scratch(){
        this.attack = "scratched";
    }

    public static final Scratch getInstance(){
        return INSTANCE;
    }

    @Override
    public String scratch() {
        return this.attack;
    }
}
