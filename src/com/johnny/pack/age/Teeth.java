package com.johnny.pack.age;

public final class Teeth implements Equipable {
    private final String weapon;

    private static final Teeth INSTANCE = new Teeth();

    private Teeth() {
        this.weapon = "teeth";
    }

    public static Teeth getInstance(){
        return INSTANCE;
    }

    @Override
    public String weaponType() {
        return this.weapon;
    }
}
