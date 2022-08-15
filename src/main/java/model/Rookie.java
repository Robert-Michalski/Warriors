package model;

import interfaces.IWeapon;

public class Rookie extends Warrior{
    private int attack = 1;

    @Override
    public int getAttack() {
        return attack;
    }
    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
    }

    @Override
    public String toString() {
        return "Rookie{" +
                "health=" +getHealth()+
                " attack=" + attack +
                '}';
    }
}
