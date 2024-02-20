package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Ogre;
import com.johnny.pack.age.model.characterfactory.character.Scorpion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScorpionCreatorTest {
    ScorpionCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new ScorpionCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Scorpion.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "Scorpion{equipable=stinger, hitPoints=25, strength=8, " +
                "location=0, isAlive=true, initiative=0, name=Ogre, " +
                "damage=5, dealDamage=13}";
        assertEquals(expected, c.toString());
    }
}