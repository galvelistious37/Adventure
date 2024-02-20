package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Scorpion;
import com.johnny.pack.age.model.characterfactory.character.SuperBandit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperBanditCreatorTest {
    SuperBanditCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new SuperBanditCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperBandit.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "SuperBandit{equipable=knife, hitPoints=20, " +
                "strength=8, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Bandit, damage=8, dealDamage=16}";
        assertEquals(expected, c.toString());
    }

}