package com.johnny.pack.age.controller;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    public static final List<Integer> gameMap = generateMapSpaces();
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int FIVE = 5;
    private static final int FORTY_FIVE = 45;

    private static List<Integer> generateMapSpaces() {
        List<Integer> locNums = new ArrayList<>();
        for(int i = ONE; i <= FORTY_FIVE; i++){
            locNums.add(i);
            if(i % FIVE == ZERO){
                i += FIVE;
            }
        }
        return locNums;
    }
}
