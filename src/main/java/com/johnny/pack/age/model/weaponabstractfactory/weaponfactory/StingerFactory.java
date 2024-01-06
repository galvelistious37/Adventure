package com.johnny.pack.age.model.weaponabstractfactory.weaponfactory;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.baseattack.Sting;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.heavyattack.Impale;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Stinger;

public class StingerFactory implements WeaponFactory{
    @Override
    public Equipable createEquipable() {
        return Stinger.getInstance();
    }

    @Override
    public Scratchable createScratchable() {
        return Scratch.getInstance();
    }

    @Override
    public Attackable createAttackable() {
        return Sting.getInstance();
    }

    @Override
    public Berserkable createBerserkable() {
        return Impale.getInstance();
    }
}
