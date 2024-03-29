package com.johnny.pack.age.model.characterfactory.character;

import com.johnny.pack.age.controller.attack.baseattack.Attackable;
import com.johnny.pack.age.controller.attack.heavyattack.Berserkable;
import com.johnny.pack.age.controller.attack.scratch.Scratchable;
import com.johnny.pack.age.model.weaponabstractfactory.weapon.Equipable;

public abstract class Character {
    public abstract String getName();
    public abstract int getHitPoints();
    public abstract void setHitPoints(int hitPoints);
    public abstract int getStrength();
    public abstract void setStrength(int strength);
    public abstract int getDamage();
    public abstract void setDamage(int damage);
    public abstract Equipable getEquipable();
    public abstract void setEquipable(Equipable equipable);
    public abstract int getLocation();
    public abstract void setLocation(int location);
    public abstract boolean getIsAlive();
    public abstract void setIsAlive(boolean isAlive);
    public abstract int getInitiative();
    public abstract void setInitiative(int initiative);
    public abstract int dealDamage();
}
