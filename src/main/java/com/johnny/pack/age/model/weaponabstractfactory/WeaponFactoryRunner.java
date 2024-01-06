package com.johnny.pack.age.model.weaponabstractfactory;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weaponfactory.*;

public class WeaponFactoryRunner {


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
//
//    public static void main(String[] args) {
//        Equipable equipable = createEquipable(new FistFactory());
//        System.out.println("*****");
//        System.out.println(equipable.getScratchable().scratch());
//        System.out.println(equipable.getAttackable().attack());
//        System.out.println(equipable.getBerserkable().goBerserk());
//
//        equipable = createEquipable(new KnifeFactory());
//        System.out.println("*****");
//        System.out.println(equipable.getScratchable().scratch());
//        System.out.println(equipable.getAttackable().attack());
//        System.out.println(equipable.getBerserkable().goBerserk());
//
//        equipable = createEquipable(new StingerFactory());
//        System.out.println("*****");
//        System.out.println(equipable.getScratchable().scratch());
//        System.out.println(equipable.getAttackable().attack());
//        System.out.println(equipable.getBerserkable().goBerserk());
//
//        equipable = createEquipable(new SwordFactory());
//        System.out.println("*****");
//        System.out.println(equipable.getScratchable().scratch());
//        System.out.println(equipable.getAttackable().attack());
//        System.out.println(equipable.getBerserkable().goBerserk());
//
//        equipable = createEquipable(new TeethFactory());
//        System.out.println("*****");
//        System.out.println(equipable.getScratchable().scratch());
//        System.out.println(equipable.getAttackable().attack());
//        System.out.println(equipable.getBerserkable().goBerserk());
//    }
}
