package com.johnny.pack.age;

import com.johnny.pack.age.controller.GamePlay;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GamePlayTest {

    private GamePlay gp;
    private int validLocation;
    private int invalidLocation;

    @Before
    public void setUp(){
        gp = GamePlay.getInstance();
        validLocation = 1;
        invalidLocation = 100;
    }

    @Test
    public void testClassIsGamePlayClass(){
        assertEquals("GamePlay", gp.getClass().getSimpleName());
    }

    @Test
    public void testDisplayGreeting(){
        String testString = "Welcome to the Greatest Adventure Game Ever!!!";
        assertEquals("displayGreeting does not match expected text", testString, gp.displayGreeting());
    }

    @Test
    public void testValidLocationReturnsTrue(){
        assertTrue(gp.displayLocation(validLocation));
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidLocationThrowsNullPointerException() {
        gp.displayLocation(invalidLocation);
    }
}