package com.johnny.pack.age;

final class Dice {

    private static final Dice INSTANCE = new Dice();
    private Dice(){};
    public static final Dice getInstance(){
        return INSTANCE;
    }

    int rollTheDie(int sides) {
        return (int) Math.ceil(Math.random() * sides);
    }

    int getRandomLocation(){
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
