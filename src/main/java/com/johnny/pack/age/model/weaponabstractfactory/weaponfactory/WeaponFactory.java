package com.johnny.pack.age.model.weaponabstractfactory.weaponfactory;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;

public interface WeaponFactory {
    Equipable createEquipable();
    Scratchable createScratchable();
    Attackable createAttackable();
    Berserkable createBerserkable();
}
