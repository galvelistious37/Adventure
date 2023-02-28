package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Bandit;
import com.johnny.pack.age.model.characterfactory.character.Character;

public class BanditCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return Bandit.getBandit();
    }
}
