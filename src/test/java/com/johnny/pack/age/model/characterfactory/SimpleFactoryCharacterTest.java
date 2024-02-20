package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.controller.dice.Dice;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleFactoryCharacterTest {
    @Mock
    private Dice mockDice;

    @InjectMocks
    SimpleFactoryCharacter classUnderTest;

    @Test
    @Description("Get BanditCreator")
    void getBanditCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(1);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), BanditCreator.class);
    }

    @Test
    @Description("Get OgreCreator")
    void getOgreCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(2);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), OgreCreator.class);
    }

    @Test
    @Description("Get ScorpionCreator")
    void getScorpionCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(3);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), ScorpionCreator.class);
    }

    @Test
    @Description("Get WolfCreator")
    void getWolfCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(4);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), WolfCreator.class);
    }

    @Test
    @Description("Get SuperBanditCreator")
    void getSuperBanditCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(5);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), SuperBanditCreator.class);
    }

    @Test
    @Description("Get SuperOgreCreator")
    void getSuperOgreCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(6);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), SuperOgreCreator.class);
    }

    @Test
    @Description("Get SuperScorpionCreator")
    void getSuperScorpionCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(7);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), SuperScorpionCreator.class);
    }

    @Test
    @Description("Get SuperWolfCreator")
    void getSuperWolfCreator(){
        when(mockDice.rollTheDie(8)).thenReturn(8);
        CharacterFactory cf = classUnderTest.getCharacterFactory();
        assertEquals(cf.getClass(), SuperWolfCreator.class);
    }

    @Test
    @Description("Throw IllegalArgumentException on bad value")
    void throwsExceptionOnBadValue(){
        when(mockDice.rollTheDie(8)).thenReturn(9);
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> classUnderTest.getCharacterFactory());
        String expectedMessage = "Value used in switch statement not valid";
        assertEquals(e.getClass(), IllegalArgumentException.class);
        assertEquals(expectedMessage, e.getMessage());
    }
}