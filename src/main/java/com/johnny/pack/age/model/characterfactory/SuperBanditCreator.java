package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperBandit;

public class SuperBanditCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return SuperBandit.getSuperBandit();
    }
}
