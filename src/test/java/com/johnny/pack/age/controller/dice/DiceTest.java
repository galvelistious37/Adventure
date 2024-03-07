package com.johnny.pack.age.controller.dice;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    @Test
    void dice_rollTheDie_neverBelowOne(){
        Dice classUnderTest = Dice.getInstance();
        long expected = 0L;
        long actual = IntStream.rangeClosed(1, 1_000)
                .parallel()
                .map(i -> classUnderTest.rollTheDie(4))
                .filter(num -> num < 1)
                .count();
        assertEquals(expected, actual);
    }

    @Test
    void dice_rollTheDie_neverAboveTotalSides(){
        int sides = 4;
        Dice classUnderTest = Dice.getInstance();
        long expected = 0L;
        long actual = IntStream.rangeClosed(1, 1_000)
                .parallel()
                .map(i -> classUnderTest.rollTheDie(sides))
                .filter(num -> num > sides)
                .count();
        assertEquals(expected, actual);
    }
}