package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.List;

public class EnemyBuilder {
    private Dice diceRoll;
    private int totalEnemies;
    private List<Character> enemyList;

    private EnemyBuilder(int totalEnemies){
        diceRoll = Dice.getInstance();
        this.totalEnemies = totalEnemies;
        this.enemyList = populateEnemyList();
    }

    public static EnemyBuilder totalEnemiesList(int totalEnemies){
        return new EnemyBuilder(totalEnemies);
    }

    public List<Character> getEnemyList() {
        return enemyList;
    }

    private List<Character> populateEnemyList(){
        List<Character> enemyList = new ArrayList<>();
        for(int i = 0; i < this.totalEnemies; i++){
            Character tempEnemy = getEnemy();
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
        return diceRoll.rollTheDie(4);
    }

    private boolean isSuperCrazy(){
        return diceRoll.rollTheDie(2) > 1;
    }
}
