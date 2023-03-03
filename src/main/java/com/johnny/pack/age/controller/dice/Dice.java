package com.johnny.pack.age.controller.dice;

public final class Dice {

    public static int rollTheDie(int sides) {
        return (int) Math.ceil(Math.random() * sides);
    }
}
