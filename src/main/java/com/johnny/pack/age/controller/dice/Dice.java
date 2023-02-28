package com.johnny.pack.age.controller.dice;

public final class Dice {

    private static final Dice INSTANCE = new Dice();

    public static Dice getInstance(){
        return INSTANCE;
    }

    public static int rollTheDie(int sides) {
        return (int) Math.ceil(Math.random() * sides);
    }

    public static int getRandomLocation(){
        int oneThroughFive = rollTheDie(5);
        int addLevel = 0;
        switch(rollTheDie(5)){
            case 1 :
                addLevel = 0;
                break;
            case 2 :
                addLevel = 10;
                break;
            case 3 :
                addLevel = 20;
                break;
            case 4 :
                addLevel = 30;
                break;
            case 5 :
                addLevel = 40;
                break;
        }
        return addLevel + oneThroughFive;
    }
}
