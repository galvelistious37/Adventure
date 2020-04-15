package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyBuilder {

    protected Map<Integer, List<Enemy>> getIntegerListMap() {
        LocationNumberBuilder locationNumberBuilder = new LocationNumberBuilder();
        Map<Integer, List<Enemy>> tempEnemyMap = new HashMap<>();
        List<Enemy> enemyList = populateEnemyList();
        List<Integer> locationNumbers = locationNumberBuilder.getLocationNumbersList();

        for(Integer number : locationNumbers){
            List<Enemy> tempList = new ArrayList<>();
            for(Enemy enemy : enemyList){
                if(enemy.getLocation() == number){
                    tempList.add(enemy);
                }
            }
            if(tempList.size() > 0){
                tempEnemyMap.put(number, tempList);
            }

        }
        return tempEnemyMap;
    }

    private List<Enemy> populateEnemyList(){
        List<Enemy> enemyList = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            Enemy tempEnemy = getEnemy();
            tempEnemy.setLocation(getRandomLocation());
            enemyList.add(tempEnemy);
        }
        return enemyList;
    }

    private Enemy getEnemy(){
        switch(getRandomEnemy()){
            case 1 :
                return new Ogre();
            case 2 :
                return new Wolf();
            case 3 :
                return new Scorpion();
            case 4 :
                return new Bandit();
        }
        return null;
    }

    private int getRandomEnemy(){
        return (int) ((Math.random() * 4) + 1);
    }

    private int getRandomLocation(){
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
