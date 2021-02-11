package com.johnny.pack.age;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GamePlayTest {

    private GamePlay gp;

    @Before
    public void setUp(){
        gp = GamePlay.getInstance();
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

}