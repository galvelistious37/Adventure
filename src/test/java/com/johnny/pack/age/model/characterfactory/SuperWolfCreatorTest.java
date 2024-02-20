package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.SuperScorpion;
import com.johnny.pack.age.model.characterfactory.character.SuperWolf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperWolfCreatorTest {
    SuperWolfCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new SuperWolfCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperWolf.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "SuperWolf{equipable=teeth, hitPoints=12, " +
                "strength=8, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Wolf, damage=9, dealDamage=17}";
        assertEquals(expected, c.toString());
    }


}