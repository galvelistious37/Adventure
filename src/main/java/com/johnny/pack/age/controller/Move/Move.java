package com.johnny.pack.age.controller.Move;

import com.johnny.pack.age.model.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class Move {
    public static final Map<String, String> moveOptionsMap = createMoveOptions();

    private static Map<String, String> createMoveOptions(){
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put(Constant.QUIT, Constant.Q);
        tempMap.put(Constant.NORTH, Constant.N);
        tempMap.put(Constant.SOUTH, Constant.S);
        tempMap.put(Constant.WEST, Constant.W);
        tempMap.put(Constant.EAST, Constant.E);
        return tempMap;
    }
}
