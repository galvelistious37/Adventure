package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperWolf;

public class SuperWolfCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return SuperWolf.getSuperWolf();
    }
}
