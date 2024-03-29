package com.johnny.pack.age.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.johnny.pack.age.model.characterfactory.character.Character;


@ExtendWith(MockitoExtension.class)
class DisplayTest {

    @Mock
    Character mockPlayer;
    @Mock
    Character mockEnemy;
    @InjectMocks
    Display classUnderTest;

    @Test
    @DisplayName("Get a list of acceptable numbers")
    void getListOfAccept1ableNumbers(){
        String zero = "0";
        String one = "1";
        String two = "2";
        String three = "3";
        String four = "4";
        String ninetyNine = "99";
        List<String> actualList = Display.getAcceptableNumbers();
        assertEquals(zero, actualList.get(0));
        assertEquals(one, actualList.get(1));
        assertEquals(two, actualList.get(2));
        assertEquals(three, actualList.get(3));
        assertEquals(four, actualList.get(4));
        assertEquals(ninetyNine, actualList.get(5));
    }

    @Test
    @DisplayName("Dispaly available exits")
    void displayExits(){
        Map<String, Integer> actualMap = new HashMap<>();
        actualMap.put("N", 1);
        actualMap.put("E", 2);
        actualMap.put("S", 3);
        actualMap.put("W", 4);
        assertTrue(classUnderTest.showExits(actualMap));
    }

}