package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.attack.attack.Bite;
import com.johnny.pack.age.controller.attack.berserk.Maul;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.weapon.Teeth;

public class TeethAttackFactory implements AttackFactory {
    @Override
    public Equipable getEquipable() {
        return Teeth.getInstance();
    }

    @Override
    public Attackable getAttack() {
        return Bite.getInstance();
    }

    @Override
    public Berserkable getBerserk() {
        return Maul.getInstance();
    }

    @Override
    public Scratchable getScratch() {
        return Scratch.getInstance();
    }
}
