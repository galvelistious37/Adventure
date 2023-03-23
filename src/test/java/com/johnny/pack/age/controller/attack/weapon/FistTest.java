package com.johnny.pack.age.controller.attack.weapon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FistTest {

    Fist underTest;

    @BeforeEach
    void setup(){
        underTest = Fist.getInstance();
    }

    @AfterEach
    void tearDown(){
        underTest = null;
    }

    @Test
    void weaponType() {
        String expected = "fists";
        assertEquals(expected, underTest.weaponType());
    }

    @Test
    void getDamage() {
        int expected = 5;
        assertEquals(expected, underTest.getDamage());
    }
}