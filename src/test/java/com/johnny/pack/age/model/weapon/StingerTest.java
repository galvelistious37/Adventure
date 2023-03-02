package com.johnny.pack.age.model.weapon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StingerTest {

    Stinger underTest;

    @BeforeEach
    void setup(){
        underTest = Stinger.getInstance();
    }

    @AfterEach
    void tearDown(){
        underTest = null;
    }

    @Test
    void weaponType() {
        String expected = "stinger";
        assertEquals(expected, underTest.weaponType());
    }

    @Test
    void getDamage() {
        int expected = 7;
        assertEquals(expected, underTest.getDamage());
    }
}