package com.johnny.pack.age.controller;

public final class Pummel implements Berserkable {
    private final String berserk;

    private static final Pummel INSTANCE = new Pummel();

    private Pummel(){
        this.berserk = "pummeled";
    }

    public static final Pummel getInstance(){
        return INSTANCE;
    }

    @Override
    public String goBersek() {
        return this.berserk;
    }
}
