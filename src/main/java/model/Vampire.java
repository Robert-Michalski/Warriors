package model;

import interfaces.HasVampirism;
import interfaces.IWarrior;
import interfaces.IWeapon;

public class Vampire extends Warrior implements HasVampirism {
    private int initialHealth = 40;
    private int attack = 4;
    private int vampirism = 50;
    private int health;

    public Vampire() {
        health = initialHealth;
    }

    @Override
    public void hit(IWarrior opponent) {
        int healthBeforeAttack = opponent.getHealth();
        super.hit(opponent);
        int healthAfterAttack = opponent.getHealth();
        int amount = healthBeforeAttack - healthAfterAttack;
        healSelfBy(amount * vampirism / 100);
    }

    @Override
    public void healSelfBy(int amount) {
        if (amount + health > initialHealth) {
            health = initialHealth;
            logger.trace("{} has recovered to full hp", this);
        } else {
            logger.trace("Vampire heals for {}", amount * vampirism / 100);
            this.health += amount;
            logger.trace("Vampire now has {} health", getHealth());
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        growVampirismBy(weapon.getVampirism());
        super.equipWeapon(weapon);

    }
    private void growVampirismBy(int amount) {
        vampirism += amount;
    }

    @Override
    public void reduceHealthBy(int attack) {
        this.health -= attack;
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
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }

    public void setVampirism(int vampirism) {
        this.vampirism = vampirism;
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
        return "Vampire{" +
                "initialHealth=" + initialHealth +
                ", attack=" + attack +
                ", vampirism=" + vampirism +
                ", health=" + health +
                '}';
    }
}
