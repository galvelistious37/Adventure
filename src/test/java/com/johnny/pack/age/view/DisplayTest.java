package com.johnny.pack.age.view;

import com.johnny.pack.age.model.character.Ogre;
import com.johnny.pack.age.model.character.Player;
import com.johnny.pack.age.model.weapon.Sword;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import com.johnny.pack.age.model.character.Character;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DisplayTest {

    Player player;
    Display classUnderTest;

    @BeforeEach
    void setup(){
        player = new Player();
        classUnderTest = new Display();
    }

    @AfterEach
    void tearDOwn(){
        player = null;
        classUnderTest = null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test1", "Test2", "Test3"})
    @DisplayName("Display text")
    void displayText_displaysText(String parm){
        assertTrue(classUnderTest.displayText(parm));
    }

    @Test
    @DisplayName("Show displays method")
    void showDisplays_showsDisplays(){
        List<Character> listTest = new ArrayList<>();
        listTest.add(Ogre.superOgre());
//        when(player.getHitPoints()).thenReturn(100);
//        when(player.getEquipable()).thenReturn(Sword.getInstance());
        assertTrue(classUnderTest.showDisplays(player, listTest));
    }

}