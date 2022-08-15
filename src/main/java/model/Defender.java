package model;

import interfaces.HasDefense;
import interfaces.IWeapon;

public class Defender extends Warrior implements HasDefense {
    private int attack = 4;
    private int initialHealth = 60;
    private int defense = 2;
    private int health;

    public Defender() {
        health = initialHealth;
    }

    @Override
    public void reduceHealthBy(int attack) {
        if (attack > getDefense()) {
            logger.trace("{} takes {} damage", this, attack - getDefense());
            this.health -= attack - getDefense();
        } else {
            logger.trace("Too little dmg to go pass through defense");
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        this.growDefenseBy(weapon.getDefense());
        super.equipWeapon(weapon);
    }

    private void growDefenseBy(int amount) {
        setDefense(getDefense()+amount);
        if(getDefense()<0)
            setDefense(0);
    }


    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Defender{" +
                "attack=" + attack +
                ", initialHealth=" + initialHealth +
                ", defense=" + defense +
                ", health=" + health +
                '}';
    }
}
