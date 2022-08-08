package model;

public class Rookie extends Warrior {
    private int attack = 1;

    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
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
    public String toString() {
        return "Rookie{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                '}';
    }
}