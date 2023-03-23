package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.attack.attack.Stab;
import com.johnny.pack.age.controller.attack.attack.Sting;
import com.johnny.pack.age.controller.attack.berserk.Hack;
import com.johnny.pack.age.controller.attack.berserk.Impale;
import com.johnny.pack.age.controller.attack.scratch.Scratch;
import com.johnny.pack.age.controller.attack.weapon.Stinger;
import com.johnny.pack.age.controller.attack.weapon.Sword;

public class StingerAttackFactory implements AttackFactory {
    @Override
    public Equipable getEquipable() {
        return Stinger.getInstance();
    }

    @Override
    public Attackable getAttack() {
        return Sting.getInstance();
    }

    @Override
    public Berserkable getBerserk() {
        return Impale.getInstance();
    }

    @Override
    public Scratchable getScratch() {
        return Scratch.getInstance();
    }
}
