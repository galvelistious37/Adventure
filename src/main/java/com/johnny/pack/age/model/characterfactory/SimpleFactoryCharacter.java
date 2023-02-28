package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.controller.dice.Dice;

public class SimpleFactoryCharacter {
    private static final int CHARACTER_TYPES = 8;

    public static CharacterFactory getCharacterFactory(){
        return getCharacterCreator();
    }

    private static CharacterFactory getCharacterCreator(){
        switch(Dice.rollTheDie(CHARACTER_TYPES)){
            case 1 :
                return new BanditCreator();
            case 2 :
                return new OgreCreator();
            case 3 :
                return new ScorpionCreator();
            case 4 :
                return new WolfCreator();
            case 5 :
                return new SuperBanditCreator();
            case 6 :
                return new SuperOgreCreator();
            case 7 :
                return new SuperScorpionCreator();
            case 8 :
                return new SuperWolfCreator();
            default:
                throw new IllegalArgumentException("Value used in switch statement not valid");
        }
    }
}
