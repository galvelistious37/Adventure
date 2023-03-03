package com.johnny.pack.age.controller.dice;

public final class Dice {

    private static final Dice INSTANCE = new Dice();

    public static Dice getInstance(){
        return INSTANCE;
    }

    public static int rollTheDie(int sides) {
        return (int) Math.ceil(Math.random() * sides);
    }
}
