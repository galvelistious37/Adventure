package com.johnny.pack.age.model.weaponabstractfactory;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weaponfactory.*;

public class WeaponFactoryRunner {


    /**
     * Simple factory method to determine an Equipable weapon type based on
     * a given String input.
     * @param weapon - String input weapon type
     * @return - Equipable object of weapon type
     */
    public static Equipable determineEquipable(String weapon){
        return switch (weapon) {
            case Constant.FIST -> createEquipable(new FistFactory());
            case Constant.KNIFE -> createEquipable(new KnifeFactory());
            case Constant.STINGER -> createEquipable(new StingerFactory());
            case Constant.SWORD -> createEquipable(new SwordFactory());
            case Constant.TEETH -> createEquipable(new TeethFactory());
            default -> throw new RuntimeException("No Equipable weapon of type: " + weapon);
        };
    }

    /**
     * An abstract factory which creates an Equipable object and sets
     * the corresponding Scratchable, Attackable, and Berserkable object
     * values based on WeaponFactory type input.
     * @param factory - Type of factory item to build out
     * @return - an Equipable object with corresponding Scratchable,
     * Attackable, and Berserkable object values.
     */
    public static Equipable createEquipable(WeaponFactory factory){
        Equipable equipable = factory.createEquipable();
        Scratchable scratchable = factory.createScratchable();
        Attackable attackable = factory.createAttackable();
        Berserkable berserkable = factory.createBerserkable();
        equipable.attachScratchable(scratchable);
        equipable.attachAttackable(attackable);
        equipable.attachBerserkable(berserkable);
        return equipable;
    }
}
