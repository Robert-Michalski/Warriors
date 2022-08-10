package model;

public class Defender extends Warrior {
    private int initialHealth = 60;
    private int attack = 3;
    private int defense = 2;

    public Defender() {
        setHealth(initialHealth);
        setAttack(attack);
        setDefense(defense);
    }

    @Override
    public void receiveHit(int damage) {
        if (damage < getDefense()) {
            reduceHealthBasedOnDamage(0);
            logger.trace("Too little damage to go through defense");
        } else {
            super.reduceHealthBasedOnDamage(damage - getDefense());
            logger.trace("{} took {} damage", this, damage - getDefense());
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        growDefenseByAmount(weapon.getDefense());
        super.equipWeapon(weapon);
    }

    public void growDefenseByAmount(int amount) {
        setDefense(getDefense() + amount);
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
        if (this.defense < 0) {
            this.defense = 0;
        }
    }

    @Override
    public String toString() {
        return "Defender{" +
                "health=" + getHealth() +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}
