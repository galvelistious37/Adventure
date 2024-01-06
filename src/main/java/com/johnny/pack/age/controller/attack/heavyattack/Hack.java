package com.johnny.pack.age.controller.attack.heavyattack;

public final class Hack implements Berserkable {
    private final String berserk;

    private static final Hack INSTANCE = new Hack();

    private Hack(){
        this.berserk = "hacked";
    }

    public static final Hack getInstance(){
        return INSTANCE;
    }

    @Override
    public String goBerserk() {
        return this.berserk;
    }
}
