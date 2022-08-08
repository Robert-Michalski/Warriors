package model;


public class Knight extends Warrior {
    private int attack = 7;

    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "health=" + getHealth() +
                "attack=" + attack +
                '}';
    }
}
