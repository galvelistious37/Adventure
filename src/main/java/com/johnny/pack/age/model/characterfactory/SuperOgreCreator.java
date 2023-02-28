package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperOgre;

public class SuperOgreCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return SuperOgre.getSuperOgre();
    }
}
