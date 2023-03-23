package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.attack.attack.Bite;
import com.johnny.pack.age.controller.attack.attack.Stab;
import com.johnny.pack.age.controller.attack.berserk.Hack;
import com.johnny.pack.age.controller.attack.berserk.Maul;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.weapon.Sword;
import com.johnny.pack.age.controller.attack.weapon.Teeth;

public class SwordAttackFactory implements AttackFactory {
    @Override
    public Equipable getEquipable() {
        return Sword.getInstance();
    }

    @Override
    public Attackable getAttack() {
        return Stab.getInstance();
    }

    @Override
    public Berserkable getBerserk() {
        return Hack.getInstance();
    }

    @Override
    public Scratchable getScratch() {
        return Scratch.getInstance();
    }
}
