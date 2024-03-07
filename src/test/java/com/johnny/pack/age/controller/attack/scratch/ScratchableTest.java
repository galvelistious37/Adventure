package com.johnny.pack.age.controller.attack.scratch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScratchableTest {

    @Test
    void scratch_whenScratch_returnsScratched(){
        Scratchable classUnderTest = Scratch.getInstance();
        String expected = "scratched";
        String actual = classUnderTest.scratch();
        assertEquals(expected, actual);
    }
}