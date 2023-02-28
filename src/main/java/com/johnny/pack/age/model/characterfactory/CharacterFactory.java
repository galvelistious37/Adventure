package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;

/**
 * This is our abstract "creator".
 * The abstract method createCharacter() has to be implemented by
 * its subclasses.
 */
public abstract class CharacterFactory {

    public Character getCharacter(){
        return createCharacter();
    }

    public abstract Character createCharacter();
}
