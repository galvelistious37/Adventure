package com.johnny.pack.age.model.weaponabstractfactory.weaponfactory;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.baseattack.Bite;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.heavyattack.Maul;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Teeth;

public class TeethFactory implements WeaponFactory{
    @Override
    public Equipable createEquipable() {
        return Teeth.getInstance();
    }

    @Override
    public Scratchable createScratchable() {
        return Scratch.getInstance();
    }

    @Override
    public Attackable createAttackable() {
        return Bite.getInstance();
    }

    @Override
    public Berserkable createBerserkable() {
        return Maul.getInstance();
    }
}
