package com.johnny.pack.age.model.characterfactory;

import com.johnny.pack.age.model.characterfactory.character.*;
import com.johnny.pack.age.model.characterfactory.character.Character;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterFactoryTest {
    CharacterFactory classUnderTest;

    @BeforeEach
    void setup(){
    }

    @AfterEach
    void tearDown(){
        classUnderTest = null;
    }

    @Test
    void createAbstractBandit(){
        classUnderTest = new BanditCreator();
        CharacterFactory cf = classUnderTest;
        assertEquals(cf.getCharacter().getClass(), Bandit.class);
    }

    @Test
    void createBandit(){
        classUnderTest = new BanditCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Bandit.class);
    }

    @Test
    void getBanditToString(){
        classUnderTest = new BanditCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "Bandit{equipable=knife, hitPoints=15, strength=6, " +
                "location=0, isAlive=true, initiative=0, name=Bandit, damage=8, " +
                "dealDamage=14}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createOgre(){
        classUnderTest = new OgreCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Ogre.class);
    }

    @Test
    void getOgreToString(){
        classUnderTest = new OgreCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "Ogre{equipable=fists, hitPoints=25, strength=8, " +
                "location=0, isAlive=true, initiative=0, name=Ogre, " +
                "damage=5, dealDamage=13}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createPlayer(){
        classUnderTest= new PlayerCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Player.class);
    }

    @Test
    void getPlayerToString(){
        classUnderTest= new PlayerCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "Player{equipable=fists, hitPoints=100, " +
                "strength=5, location=1, isAlive=true, initiative=0, " +
                "name=You, damage=5, dealDamage=10}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createScorpion(){
        classUnderTest= new ScorpionCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Scorpion.class);
    }

    @Test
    void getScorpionToString(){
        classUnderTest= new ScorpionCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "Scorpion{equipable=stinger, hitPoints=15, strength=8, " +
                "location=0, isAlive=true, initiative=0, name=Scorpion, damage=7, " +
                "dealDamage=15}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createSuperBandit(){
        classUnderTest= new SuperBanditCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperBandit.class);
    }

    @Test
    void getSuperBanditToString(){
        classUnderTest= new SuperBanditCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "SuperBandit{equipable=knife, hitPoints=20, " +
                "strength=8, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Bandit, damage=8, dealDamage=16}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createSuperOgre(){
        classUnderTest= new SuperOgreCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperOgre.class);
    }

    @Test
    void getSuperOgreToString(){
        classUnderTest= new SuperOgreCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "SuperOgre{equipable=fists, hitPoints=35, " +
                "strength=10, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Ogre, damage=5, dealDamage=15}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createSuperScorpion(){
        classUnderTest = new SuperScorpionCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperScorpion.class);
    }

    @Test
    void getSuperScorpionToString(){
        classUnderTest = new SuperScorpionCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "SuperScorpion{equipable=stinger, hitPoints=20, " +
                "strength=10, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Scorpion, damage=7, dealDamage=17}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createSuperWolf(){
        classUnderTest = new SuperWolfCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), SuperWolf.class);
    }

    @Test
    void getSuperWolfToString(){
        classUnderTest = new SuperWolfCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "SuperWolf{equipable=teeth, hitPoints=12, " +
                "strength=8, location=0, isAlive=true, initiative=0, " +
                "name=Super Crazy Wolf, damage=9, dealDamage=17}";
        assertEquals(expected, c.toString());
    }

    @Test
    void createWolf(){
        classUnderTest = new WolfCreator();
        Character c = classUnderTest.createCharacter();
        assertEquals(c.getClass(), Wolf.class);
    }

    @Test
    void getWolfToString(){
        classUnderTest = new WolfCreator();
        Character c = classUnderTest.createCharacter();
        String expected = "Wolf{equipable=teeth, hitPoints=8, strength=6, " +
                "location=0, isAlive=true, initiative=0, name=Wolf, damage=9, " +
                "dealDamage=15}";
        assertEquals(expected, c.toString());
    }
}