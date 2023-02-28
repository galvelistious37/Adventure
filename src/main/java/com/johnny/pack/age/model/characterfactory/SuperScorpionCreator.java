package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperScorpion;

public class SuperScorpionCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return SuperScorpion.getSuperScorpion();
    }
}
