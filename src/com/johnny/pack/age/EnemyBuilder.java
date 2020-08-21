package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyBuilder {
    Dice diceRoll;

    public EnemyBuilder(){
        diceRoll = new Dice();
    }

    protected List<Character> getEnemyList() {
        List<Character> enemyList = populateEnemyList();
        return enemyList;
    }

    private List<Character> populateEnemyList(){
        List<Character> enemyList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Character tempEnemy = getEnemy();
            tempEnemy.setName(tempEnemy.getName());
            tempEnemy.setLocation(diceRoll.getRandomLocation());
            enemyList.add(tempEnemy);
        }
        return enemyList;
    }

    private Character getEnemy(){
        switch(getRandomEnemy()){
            case 1 :
                return isSuperCrazy() ? Ogre.superOgre() : Ogre.normalOgre();
            case 2 :
                return isSuperCrazy() ? Wolf.superWolf() : Wolf.normalWolf();
            case 3 :
                return isSuperCrazy() ? Scorpion.superScorpion() : Scorpion.normalScorpion();
            case 4 :
                return isSuperCrazy() ? Bandit.superBandit() : Bandit.normalBandit();
        }
        return null;
    }

    private int getRandomEnemy(){
        return (int) ((Math.random() * 4) + 1);
    }

    private boolean isSuperCrazy(){
        return Math.floor(Math.random() * 2) > 0;
    }
}
