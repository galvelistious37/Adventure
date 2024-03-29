package com.johnny.pack.age.controller.attack.heavyattack;

public final class Maul implements Berserkable {
    private final String berserk;

    private static final Maul INSTANCE = new Maul();

    private Maul(){
        this.berserk = "mauled";
    }

    public static final Maul getInstance(){
        return INSTANCE;
    }

    @Override
    public String goBerserk() {
        return this.berserk;
    }
}
