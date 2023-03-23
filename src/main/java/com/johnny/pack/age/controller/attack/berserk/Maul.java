package com.johnny.pack.age.controller.attack.berserk;

import com.johnny.pack.age.controller.attack.Berserkable;

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
    public String goBersek() {
        return this.berserk;
    }
}
