package com.johnny.pack.age.controller.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LocationBuilderTest {
    private LocationBuilder underTest;
    private Method getY;

    @BeforeEach
    void setup() throws NoSuchMethodException {
        underTest = LocationBuilder.createLocationBuilder();
        getY = LocationBuilder.class.getDeclaredMethod("getY", int.class);
        getY.setAccessible(true);
    }

    @Test
    @DisplayName("Get the first digit of an integer")
    void testGetY() throws InvocationTargetException, IllegalAccessException {
        assertEquals(1, getY.invoke(underTest, 13));
        assertEquals(2, getY.invoke(underTest, 23));
        assertEquals(3, getY.invoke(underTest, 33));
        assertEquals(4, getY.invoke(underTest, 43));
    }

}