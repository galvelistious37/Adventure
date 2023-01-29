package com.johnny.pack.age.controller.builder;

import java.util.ArrayList;
import java.util.List;

public class LocationNumberBuilder {

    protected List<Integer> getLocationNumbersList(){
        List<Integer> locNums = new ArrayList<>();
        for(int i = 1; i <= 45; i++){
            locNums.add(i);
            if(i % 5 == 0){
                i += 5;
            }
        }
        return locNums;
    }
}
