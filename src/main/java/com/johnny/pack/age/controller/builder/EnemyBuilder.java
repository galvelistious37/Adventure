package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.model.characterfactory.*;
import com.johnny.pack.age.model.characterfactory.character.Character;

import java.util.ArrayList;
import java.util.List;

public class EnemyBuilder {
    private int totalEnemies;
    private List<Character> enemyList;

    private EnemyBuilder(int totalEnemies){
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
            Character tempEnemy = SimpleFactoryCharacter
                    .getCharacterFactory()
                    .getCharacter();
            tempEnemy.setLocation(Dice.getRandomLocation());
            enemyList.add(tempEnemy);
        }
        return enemyList;
    }
}
