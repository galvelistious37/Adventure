package com.johnny.pack.age.model.weaponabstractfactory;

import com.johnny.pack.age.controller.attack.baseattack.Bite;
import com.johnny.pack.age.controller.attack.baseattack.Punch;
import com.johnny.pack.age.controller.attack.baseattack.Stab;
import com.johnny.pack.age.controller.attack.baseattack.Sting;
import com.johnny.pack.age.controller.attack.heavyattack.Hack;
import com.johnny.pack.age.controller.attack.heavyattack.Impale;
import com.johnny.pack.age.controller.attack.heavyattack.Maul;
import com.johnny.pack.age.controller.attack.heavyattack.Pummel;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponFactoryRunnerTest {

    @Test
    @Description("Determine Equipable Fist")
    void determineEquipableFist(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("fist");
        assertTrue(expected instanceof Fist);
    }

    @Test
    @Description("Determine Fist Scractchable")
    void determineFistScratchable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("fist");
        assertTrue(expected.getScratchable() instanceof Scratch);
    }

    @Test
    @Description("Determine Fist Attackable")
    void determineFistAttackable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("fist");
        assertTrue(expected.getAttackable() instanceof Punch);
    }

    @Test
    @Description("Determine Fist Berserkable")
    void determineFistBerserkable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("fist");
        assertTrue(expected.getBerserkable() instanceof Pummel);
    }

    @Test
    @Description("Determine Equipable Knife")
    void determineEquipableKnife(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("knife");
        assertTrue(expected instanceof Knife);
    }

    @Test
    @Description("Determine Knife Scractchable")
    void determineKnifeScratchable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("knife");
        assertTrue(expected.getScratchable() instanceof Scratch);
    }

    @Test
    @Description("Determine Knife Attackable")
    void determineKnifeAttackable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("knife");
        assertTrue(expected.getAttackable() instanceof Stab);
    }

    @Test
    @Description("Determine Knife Berserkable")
    void determineKnifeBersekable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("knife");
        assertTrue(expected.getBerserkable() instanceof Impale);
    }

    @Test
    @Description("Determine Equipable Stinger")
    void determineEquipableStinger(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("stinger");
        assertTrue(expected instanceof Stinger);
    }

    @Test
    @Description("Determine Stinger Scractchable")
    void determineStingerScratchable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("stinger");
        assertTrue(expected.getScratchable() instanceof Scratch);
    }

    @Test
    @Description("Determine Stinger Attackable")
    void determineStingerAttackable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("stinger");
        assertTrue(expected.getAttackable() instanceof Sting);
    }

    @Test
    @Description("Determine Stinger Berserkable")
    void determineStingerBersekable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("stinger");
        assertTrue(expected.getBerserkable() instanceof Impale);
    }

    @Test
    @Description("Determine Equipable Sword")
    void determineEquipableSword(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("sword");
        assertTrue(expected instanceof Sword);
    }

    @Test
    @Description("Determine Sword Scractchable")
    void determineSwordScratchable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("sword");
        assertTrue(expected.getScratchable() instanceof Scratch);
    }

    @Test
    @Description("Determine Sword Attackable")
    void determineSwordAttackable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("sword");
        assertTrue(expected.getAttackable() instanceof Stab);
    }

    @Test
    @Description("Determine Sword Berserkable")
    void determineSwordBersekable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("sword");
        assertTrue(expected.getBerserkable() instanceof Hack);
    }

    @Test
    @Description("Determine Equipable Teeth")
    void determineEquipableTeeth(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("teeth");
        assertTrue(expected instanceof Teeth);
    }

    @Test
    @Description("Determine Teeth Scractchable")
    void determineTeethScratchable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("teeth");
        assertTrue(expected.getScratchable() instanceof Scratch);
    }

    @Test
    @Description("Determine Teeth Attackable")
    void determineTeethAttackable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("teeth");
        assertTrue(expected.getAttackable() instanceof Bite);
    }

    @Test
    @Description("Determine Teeth Berserkable")
    void determineTeethBersekable(){
        Equipable expected = WeaponFactoryRunner.determineEquipable("teeth");
        assertTrue(expected.getBerserkable() instanceof Maul);
    }

    @Test
    @Description("Bad type throws RunTimeException")
    void badTypeRunTimeException(){
        Exception e = assertThrows(RuntimeException.class,
                () -> WeaponFactoryRunner.determineEquipable("liberty-bibberty"));
        String expectedMessage = "No Equipable weapon of type: liberty-bibberty";
        assertTrue(e instanceof RuntimeException);
        assertEquals(expectedMessage, e.getMessage());
    }
}