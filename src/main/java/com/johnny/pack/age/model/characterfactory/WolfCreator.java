package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Wolf;

public class WolfCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return Wolf.getWolf();
    }
}
