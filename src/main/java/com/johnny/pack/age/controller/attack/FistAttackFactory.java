package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.attack.attack.Punch;
import com.johnny.pack.age.controller.attack.attack.Sting;
import com.johnny.pack.age.controller.attack.berserk.Impale;
import com.johnny.pack.age.controller.attack.berserk.Pummel;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.weapon.Fist;
import com.johnny.pack.age.controller.attack.weapon.Stinger;

public class FistAttackFactory implements AttackFactory {
    @Override
    public Equipable getEquipable() {
        return Fist.getInstance();
    }

    @Override
    public Attackable getAttack() {
        return Punch.getInstance();
    }

    @Override
    public Berserkable getBerserk() {
        return Pummel.getInstance();
    }

    @Override
    public Scratchable getScratch() {
        return Scratch.getInstance();
    }
}
