package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.characterfactory.character.Wolf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCreatorTest {
    PlayerCreator classUnderTest;

    @BeforeEach
    void setup(){
        classUnderTest = new PlayerCreator();
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createBandit(){
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Player.class);
    }

    @Test
    void getBanditToString(){
        Character c = classUnderTest.createCharacter();
        String expected = "Player{equipable=fists, hitPoints=100, " +
                "strength=5, location=1, isAlive=true, initiative=0, " +
                "name=You, damage=5, dealDamage=10}";
        assertEquals(expected, c.toString());
    }



}