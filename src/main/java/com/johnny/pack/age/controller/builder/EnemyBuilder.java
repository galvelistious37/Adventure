package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.model.characterfactory.*;
import com.johnny.pack.age.model.characterfactory.character.Character;

import java.util.ArrayList;
import java.util.List;

public class EnemyBuilder {
    private List<Character> enemyList;

    private EnemyBuilder(){
        this.enemyList = populateEnemyList();
    }

    public static EnemyBuilder getInstance(){
        return new EnemyBuilder();
    }

    public List<Character> getAllEnemies() {
        return enemyList;
    }

    private List<Character> populateEnemyList(){
        int totalEnemies = 15;
        List<Character> enemyList = new ArrayList<>();
        for(int i = 0; i < totalEnemies; i++){
            Character tempEnemy = SimpleFactoryCharacter
                    .getCharacterFactory()
                    .getCharacter();
            tempEnemy.setLocation(LocationBuilder.getRandomLocation());
            enemyList.add(tempEnemy);
        }
        return enemyList;
    }
}
