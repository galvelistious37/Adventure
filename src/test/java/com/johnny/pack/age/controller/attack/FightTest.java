package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.model.characterfactory.BanditCreator;
import com.johnny.pack.age.model.characterfactory.character.Bandit;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Ogre;
import com.johnny.pack.age.model.characterfactory.character.Wolf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FightTest {

    Fight classUnderTest;
    Dice mockDice;

    @BeforeEach
    void setUp() {
        mockDice = Mockito.mock(Dice.class);
        classUnderTest = new Fight(getEnemies(), mockDice);
    }

    @AfterEach
    void tearDown() {
    }

    private List<Character> getEnemies(){
        Bandit mockBandit = Mockito.mock(Bandit.class);
        List<Character> enemies = new ArrayList<>();
        enemies.add(mockBandit);
        return enemies;
    }
}