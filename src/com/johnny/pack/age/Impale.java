package com.johnny.pack.age;

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
    public String goBersek() {
        return this.berserk;
    }
}
