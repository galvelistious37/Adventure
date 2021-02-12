package com.johnny.pack.age;

final class Dice {

    private static final Dice INSTANCE = new Dice();

    public static Dice getInstance(){
        return INSTANCE;
    }

    int rollTheDie(int sides) {
        return (int) Math.ceil(Math.random() * sides);
    }

    int getRandomLocation(){
        int oneThroughFive = rollTheDie(5);
        int addLevel = 0;
        switch(rollTheDie(5)){
            case 1 -> addLevel = 0;
            case 2 -> addLevel = 10;
            case 3 -> addLevel = 20;
            case 4 -> addLevel = 30;
            case 5 -> addLevel = 40;
        }
        return addLevel + oneThroughFive;
    }
}
