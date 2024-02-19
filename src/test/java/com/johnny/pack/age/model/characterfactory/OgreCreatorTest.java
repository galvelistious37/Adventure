package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Bandit;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Ogre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OgreCreatorTest {
    OgreCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new OgreCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Ogre.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "Ogre{equipable=fists, hitPoints=25, strength=8, " +
                "location=0, isAlive=true, initiative=0, name=Ogre, " +
                "damage=5, dealDamage=13}";
        assertEquals(expected, c.toString());
    }
}