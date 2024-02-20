package com.johnny.pack.age.controller.builder;

import com.johnny.pack.age.model.characterfactory.*;
import com.johnny.pack.age.model.characterfactory.character.Character;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EnemyBuilder {
    private List<Character> enemyList;
    private LocationBuilder locationBuilder;

    private EnemyBuilder(){
        this.locationBuilder = new LocationBuilder();
        this.enemyList = populateEnemyList();
        setLocations();
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
                        .createCharacter())
                .collect(Collectors.toList());
    }

    private void setLocations(){
        enemyList.forEach(
                e -> e.setLocation(locationBuilder.getRandomLocation()));
    }
}
