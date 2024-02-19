package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.controller.dice.Dice;

public class SimpleFactoryCharacter {
    private static final int CHARACTER_TYPES = 8;
    private Dice dice = Dice.getInstance();

    public SimpleFactoryCharacter(){}

    /**
     * Call simple factory to generate a new character type
     * @return - CharacterFactory object of an enemy creator
     */
    public CharacterFactory getCharacterFactory(){
        return getCharacterCreator();
    }

    /**
     * @return - A random enemy creator
     */
    private CharacterFactory getCharacterCreator(){
        return switch (dice.rollTheDie(CHARACTER_TYPES)) {
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
