package model;

import interfaces.IWarrior;
import interfaces.IWeapon;
import interfaces.command.HealCommand;

public class Lancer extends Warrior {
    private int initialHealth = 50;
    private int attack = 6;
    private int piercing = 50;
    private int health;

    public Lancer() {
        this.health = initialHealth;
    }

    @Override
    public void hit(IWarrior opponent) {
        logger.debug("{} hits {}", this, opponent);
        int healthBeforeAttack = opponent.getHealth();
        opponent.reduceHealthBy(getAttack());
        int healthAfterAttack = opponent.getHealth();
        if (opponent.getWarriorBehind() != null) {
            logger.debug("{} pierces and attacks {} for {} damage", this, opponent.getWarriorBehind(), getAttack() * piercing / 100);
            int total = healthBeforeAttack - healthAfterAttack;
            opponent.getWarriorBehind().reduceHealthBy(total * piercing / 100);
        }
        process(new HealCommand(), this);
    }
    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
    }

    @Override
    public void reduceHealthBy(int attack) {
        this.health -= attack;
        logger.debug("{} took {} damage", this, attack);
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

    public int getPiercing() {
        return piercing;
    }

    public void setPiercing(int piercing) {
        this.piercing = piercing;
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
        return "Lancer{" +
                "initialHealth=" + initialHealth +
                ", attack=" + attack +
                ", piercing=" + piercing +
                ", health=" + health +
                '}';
    }
}
