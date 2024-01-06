package com.johnny.pack.age.controller.attack.heavyattack;

public final class Impale implements Berserkable {
    private final String berserk;

    private static final Impale INSTANCE = new Impale();

    private Impale(){
        this.berserk = "impaled";
    }

    public static final Impale getInstance(){
        return INSTANCE;
    }

    @Override
    public String goBerserk() {
        return this.berserk;
    }
}
