package com.johnny.pack.age.controller.attack;

// Abstract factory with methods defined for each object type.
public interface AttackFactory {
    Equipable getEquipable();
    Attackable getAttack();
    Berserkable getBerserk();
    Scratchable getScratch();
}
