package com.johnny.pack.age.model;

import com.johnny.pack.age.controller.Attackable;
import com.johnny.pack.age.controller.Berserkable;
import com.johnny.pack.age.controller.Scratchable;

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
    public abstract Attackable getAttackable();
    public abstract void setAttackable(Attackable attackable);
    public abstract Berserkable getBerserkable();
    public abstract void setBerserkable(Berserkable berserkable);
    public abstract int getLocation();
    public abstract void setLocation(int location);
    public abstract boolean getIsAlive();
    public abstract void setIsAlive(boolean isAlive);
    public abstract int getInitiative();
    public abstract void setInitiative(int initiative);
    public abstract int dealDamage();
    public abstract Scratchable getScratchable();
}
