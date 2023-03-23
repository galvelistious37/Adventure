package com.johnny.pack.age.controller.attack.weapon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeethTest {

    Teeth underTest;

    @BeforeEach
    void setup(){
        underTest = Teeth.getInstance();
    }

    @AfterEach
    void tearDown(){
        underTest = null;
    }

    @Test
    void weaponType() {
        String expected = "teeth";
        assertEquals(expected, underTest.weaponType());
    }

    @Test
    void getDamage() {
        int expected = 9;
        assertEquals(expected, underTest.getDamage());
    }
}