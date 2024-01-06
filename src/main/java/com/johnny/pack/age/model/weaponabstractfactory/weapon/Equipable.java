package com.johnny.pack.age.model.weaponabstractfactory.weapon;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;

public interface Equipable {
    String weaponType();
    int getDamage();
    void attachScratchable(Scratchable scratchable);
    void attachAttackable(Attackable attackable);
    void attachBerserkable(Berserkable berserkable);
    Scratchable getScratchable();
    Attackable getAttackable();
    Berserkable getBerserkable();
}
