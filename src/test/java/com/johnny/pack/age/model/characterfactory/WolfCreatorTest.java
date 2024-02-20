package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperScorpion;
import com.johnny.pack.age.model.characterfactory.character.Wolf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WolfCreatorTest {
    WolfCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new WolfCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Wolf.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "Wolf{equipable=teeth, hitPoints=8, strength=6, " +
                "location=0, isAlive=true, initiative=0, name=Wolf, damage=9, " +
                "dealDamage=15}";
        assertEquals(expected, c.toString());
    }


}