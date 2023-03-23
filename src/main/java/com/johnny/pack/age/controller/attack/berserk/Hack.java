package com.johnny.pack.age.controller.attack.berserk;

import com.johnny.pack.age.controller.attack.Berserkable;

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
    public String goBersek() {
        return this.berserk;
    }
}
