package com.johnny.pack.age.model.weaponabstractfactory.weaponfactory;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.baseattack.Stab;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.heavyattack.Hack;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Sword;

public class SwordFactory implements WeaponFactory{
    @Override
    public Equipable createEquipable() {
        return Sword.getInstance();
    }

    @Override
    public Scratchable createScratchable() {
        return Scratch.getInstance();
    }

    @Override
    public Attackable createAttackable() {
        return Stab.getInstance();
    }

    @Override
    public Berserkable createBerserkable() {
        return Hack.getInstance();
    }
}
