package com.johnny.pack.age.model.weaponabstractfactory.weapon;

import com.johnny.pack.age.model.weaponabstractfactory.weapon.Sword;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

    Sword underTest;

    @BeforeEach
    void setup(){
        underTest = Sword.getInstance();
    }

    @AfterEach
    void tearDown(){
        underTest = null;
    }

    @Test
    void weaponType() {
        String expected = "sword";
        assertEquals(expected, underTest.weaponType());
    }

    @Test
    void getDamage() {
        int expected = 14;
        assertEquals(expected, underTest.getDamage());
    }

    @Test
    void getLocation() {
        int expected = 5;
        underTest.setLocation(expected);
        assertEquals(expected, underTest.getLocation());
    }
}