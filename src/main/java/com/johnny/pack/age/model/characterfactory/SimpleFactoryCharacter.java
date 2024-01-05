package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.controller.dice.Dice;

public class SimpleFactoryCharacter {
    private static final int CHARACTER_TYPES = 8;

    /**
     * Call simple factory to genereate a new character type
     * @return - CharacterFactory object of an enemy creator
     */
    public static CharacterFactory getCharacterFactory(){
        return getCharacterCreator();
    }

    /**
     * @return - A random enemy creator
     */
    private static CharacterFactory getCharacterCreator(){
        return switch (Dice.rollTheDie(CHARACTER_TYPES)) {
            case 1 -> new BanditCreator();
            case 2 -> new OgreCreator();
            case 3 -> new ScorpionCreator();
            case 4 -> new WolfCreator();
            case 5 -> new SuperBanditCreator();
            case 6 -> new SuperOgreCreator();
            case 7 -> new SuperScorpionCreator();
            case 8 -> new SuperWolfCreator();
            default -> throw new IllegalArgumentException("Value used in switch statement not valid");
        };
    }
}
