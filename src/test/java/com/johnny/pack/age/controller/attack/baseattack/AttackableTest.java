package com.johnny.pack.age.controller.attack.baseattack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackableTest {

    @Test
    void bite_attack_returnBit(){
        Attackable classUnderTest = Bite.getInstance();
        String expected = "bit";
        String actual = classUnderTest.attack();
        assertEquals(expected, actual, "Should have returned 'bit'");
    }

    @Test
    void punch_attack_returnPunched(){
        Attackable classUnderTest = Punch.getInstance();
        String expected = "punched";
        String actual = classUnderTest.attack();
        assertEquals(expected, actual, "Should have returned 'bit'");
    }

    @Test
    void stab_attack_returnStabbed(){
        Attackable classUnderTest = Stab.getInstance();
        String expected = "stabbed";
        String actual = classUnderTest.attack();
        assertEquals(expected, actual, "Should have returned 'bit'");
    }

    @Test
    void string_attack_returnStung(){
        Attackable classUnderTest = Sting.getInstance();
        String expected = "stung";
        String actual = classUnderTest.attack();
        assertEquals(expected, actual, "Should have returned 'bit'");
    }
}