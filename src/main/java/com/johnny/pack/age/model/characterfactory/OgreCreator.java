package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Ogre;

public class OgreCreator extends CharacterFactory{

    @Override
    public Character createCharacter() {
        return Ogre.getOgre();
    }
}
