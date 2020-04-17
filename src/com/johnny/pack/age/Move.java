package com.johnny.pack.age;

import java.util.HashMap;
import java.util.Map;

public class Move {
    private Map<String, String> moveOptionsMap;

    public Move() {
        this.moveOptionsMap = createMoveOptions();
    }

    private Map<String, String> createMoveOptions(){
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("QUIT", "Q");
        tempMap.put("NORTH", "N");
        tempMap.put("SOUTH", "S");
        tempMap.put("WEST", "W");
        tempMap.put("EAST", "E");
        return tempMap;
    }

    public Map<String, String> getMoveOptionsMap() {
        return moveOptionsMap;
    }
}
