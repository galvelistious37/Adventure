package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.model.characterfactory.*;
import com.johnny.pack.age.model.characterfactory.character.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        return IntStream.rangeClosed(1, 15)
                .mapToObj(num -> new SimpleFactoryCharacter()
                        .getCharacterFactory()
                        .getCharacter())
                .collect(Collectors.toList());

//        int totalEnemies = 15;
//        List<Character> enemyList = new ArrayList<>();
//        for(int i = 0; i < totalEnemies; i++){
//            Character tempEnemy = new SimpleFactoryCharacter()
//                    .getCharacterFactory()
//                    .getCharacter();
//            tempEnemy.setLocation(LocationBuilder.getRandomLocation());
//            enemyList.add(tempEnemy);
//        }
//        return enemyList;
    }
}
