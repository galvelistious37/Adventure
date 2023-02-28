package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;

public class PlayerCreator extends CharacterFactory {

    @Override
    public Character createCharacter() {
        return Player.getInstance();
    }
}
