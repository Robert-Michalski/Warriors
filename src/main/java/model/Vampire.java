package model;

import interfaces.HasVampirism;
import interfaces.IWarrior;
import interfaces.IWeapon;
import interfaces.command.HealCommand;

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
        int x1 = opponent.getHealth();
        super.hit(opponent);
        int x2 = opponent.getHealth();
        healSelfByAmount(((x1 - x2) * vampirism) / 100);
        logger.debug("{} heals himself for {} units", this, ((x1 - x2) * vampirism) / 100);
        //process(new HealCommand(), this);
    }

    public void healSelfByAmount(int amount) {
        this.setHealth(this.getHealth() + amount);
        if (this.getHealth() > this.initialHealth) {
            this.setHealth(this.initialHealth);
            logger.debug("{} will not overheal, his health is full", this);
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
