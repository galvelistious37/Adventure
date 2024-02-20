package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperBandit;
import com.johnny.pack.age.model.characterfactory.character.SuperScorpion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperScorpionCreatorTest {
    SuperScorpionCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new SuperScorpionCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperScorpion.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "SuperScorpion{equipable=stinger, hitPoints=20, " +
                "strength=10, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Scorpion, damage=7, dealDamage=17}";
        assertEquals(expected, c.toString());
    }

}