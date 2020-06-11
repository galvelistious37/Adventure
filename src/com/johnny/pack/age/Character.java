package com.johnny.pack.age;

public abstract class Character {
//    Equipable equipable;
//    Attackable attackable;
//    Berserkable berserkable;
//    int hitpoints;
//    int strength;
//    int location;
//    boolean isAlive;
//    int initiative;

    public abstract String displayCharacter();
    public abstract String weaponType();
    public abstract void setEquipable(Equipable equipable);
    public abstract String performAttack();
    public abstract void setAttackable(Attackable attackable);
    public abstract String performBersek();
    public abstract void setBerserkable(Berserkable berserkable);
    public abstract int getHitpoints();
    public abstract void setHitpoints(int hitpoints);
    public abstract int getStrength();
    public abstract void setStrength(int strength);
    public abstract int getLocation();
    public abstract void setLocation(int location);
    public abstract boolean getIsAlive();
    public abstract void setIsAlive(boolean isAlive);
    public abstract int getInitiative();
    public abstract void setInitiative(int initiative);
}
