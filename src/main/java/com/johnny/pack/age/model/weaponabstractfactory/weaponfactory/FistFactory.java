package com.johnny.pack.age.model.weaponabstractfactory.weaponfactory;

import com.johnny.pack.age.controller.attack.baseattack.Punch;
import com.johnny.pack.age.controller.attack.heavyattack.Pummel;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Fist;

public class FistFactory implements WeaponFactory {
    @Override
    public Fist createEquipable() {
        return Fist.getInstance();
    }

    @Override
    public Scratchable createScratchable() {
        return Scratch.getInstance();
    }

    @Override
    public Punch createAttackable() {
        return Punch.getInstance();
    }

    @Override
    public Pummel createBerserkable() {
        return Pummel.getInstance();
    }
}
