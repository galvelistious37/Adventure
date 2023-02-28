package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Scorpion;

public class ScorpionCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return Scorpion.getScorpion();
    }
}
