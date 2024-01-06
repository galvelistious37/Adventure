package com.johnny.pack.age.controller.attack.heavyattack;

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
    public String goBerserk() {
        return this.berserk;
    }
}
