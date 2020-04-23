package com.johnny.pack.age;

public class Dice {

    public int rollATwenty() {
        return (int) ((Math.random() * 20) + 1);
    }

    public int getRandomNumberFromListSize(int size){
        return (int) ((Math.random() * size) + 1);
    }
}
