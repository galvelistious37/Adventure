package com.johnny.pack.age;

public class Dice {

    public int rollATwenty() {
        return (int) ((Math.random() * 20) + 1);
    }

    public int getRandomNumberFromListSize(int size){
        return (int) ((Math.random() * size) + 1);
    }

    protected int getRandomLocation(){
        int oneThroughFive = (int) ((Math.random() * 5) + 1);
        int increase = (int) ((Math.random() * 5) + 1);
        int addLevel = 0;
        switch(increase){
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
            default :
                addLevel = 0;
        }
        return oneThroughFive + addLevel;
    }
}
