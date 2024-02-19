package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Bandit;
import com.johnny.pack.age.model.characterfactory.character.Character;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BanditCreatorTest {
    BanditCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new BanditCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Bandit.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "Bandit{equipable=knife, hitPoints=15, strength=6, " +
                "location=0, isAlive=true, initiative=0, name=Bandit, damage=8, " +
                "dealDamage=14}";
        assertEquals(expected, c.toString());
    }
}