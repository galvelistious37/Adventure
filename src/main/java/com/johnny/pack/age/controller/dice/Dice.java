package com.johnny.pack.age.controller.dice;

public class Dice {

    private Dice(){}

    private static class DiceRegistry {
        static Dice INSTANCE = new Dice();
    }

    public static Dice getInstance(){
        return DiceRegistry.INSTANCE;
    }

    public int rollTheDie(int sides) {
        return (int) Math.ceil(Math.random() * sides);
    }
}
