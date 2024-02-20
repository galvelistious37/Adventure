package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperBandit;
import com.johnny.pack.age.model.characterfactory.character.SuperOgre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperOgreCreatorTest {
    SuperOgreCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new SuperOgreCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperOgre.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "SuperOgre{equipable=fists, hitPoints=35, " +
                "strength=10, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Ogre, damage=5, dealDamage=15}";
        assertEquals(expected, c.toString());
    }


}