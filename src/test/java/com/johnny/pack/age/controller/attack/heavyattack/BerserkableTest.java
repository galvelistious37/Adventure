package com.johnny.pack.age.controller.attack.heavyattack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BerserkableTest {

    @Test
    void hack_goBerserk_returnsHacked(){
        Berserkable classUnderTest = Hack.getInstance();
        String expected = "hacked";
        String actual = classUnderTest.goBerserk();
        assertEquals(expected, actual);
    }

    @Test
    void impale_goBerserk_returnsImpaled(){
        Berserkable classUnderTest = Impale.getInstance();
        String expected = "impaled";
        String actual = classUnderTest.goBerserk();
        assertEquals(expected, actual);
    }

    @Test
    void maul_goBersek_returnsMauled(){
        Berserkable classUnderTest = Maul.getInstance();
        String expected = "mauled";
        String actual = classUnderTest.goBerserk();
        assertEquals(expected, actual);
    }

    @Test
    void pummel_goBerserk_returnsPummeled(){
        Berserkable classUnderTest = Pummel.getInstance();
        String expected = "pummeled";
        String actual = classUnderTest.goBerserk();
        assertEquals(expected, actual);
    }
}