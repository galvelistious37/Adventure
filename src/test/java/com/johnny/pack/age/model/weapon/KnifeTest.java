package com.johnny.pack.age.model.weapon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnifeTest {

    Knife underTest;

    @BeforeEach
    void setup(){
        underTest = Knife.getInstance();
    }

    @AfterEach
    void tearDown(){
        underTest = null;
    }

    @Test
    void weaponType() {
        String expected = "knife";
        assertEquals(expected, underTest.weaponType());
    }

    @Test
    void getDamage() {
        int expected = 8;
        assertEquals(expected, underTest.getDamage());
    }

    @Test
    void getLocation() {
        int expected = 3;
        underTest.setLocation(expected);
        assertEquals(expected, underTest.getLocation());
    }
}