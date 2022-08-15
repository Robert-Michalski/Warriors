package model;

import interfaces.IWarrior;
import interfaces.IWeapon;

public class Knight extends Warrior {
    private int attack = 7;

    public Knight() {
    }

    @Override
    public int getAttack() {
        return attack;
    }


    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
    }


    @Override
    public void growAttackByAmount(int amount) {
        super.growAttackByAmount(amount);
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "health = " + getHealth() +
                ", attack = " + attack +
                '}';
    }
}
