package model;

import interfaces.IWarrior;
import interfaces.IWeapon;
import interfaces.command.ArrowRainCommand;

public class Archer extends Warrior {
    private int initialHealth = 40;
    private int health;
    private int initialAttack = 6;
    private int attack = 6;
    private int arrowsPacks = 3;

    public Archer() {
        this.health = initialHealth;
    }

    @Override
    public void hit(IWarrior opponent) {
        if (getWarriorInFront() == null) {
            logger.debug("archer cannot attack if he has no cover");
        } else {
            if (arrowsPacks > 0) {
                opponent.process(new ArrowRainCommand(this), opponent);
                arrowsPacks -= 1;
            } else {
                logger.debug("archer has no arrows");
            }
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        addArrows(weapon.getArrowsPacks());
        super.equipWeapon(weapon);
    }
    public void addArrows(int amount){
        this.arrowsPacks += amount;
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
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
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
        return "Archer{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }

    public int getArrowsPacks() {
        return arrowsPacks;
    }

    public void setArrowsPacks(int arrowsPacks) {
        this.arrowsPacks = arrowsPacks;
    }

    public int getInitialAttack() {
        return initialAttack;
    }

    public void setInitialAttack(int initialAttack) {
        this.initialAttack = initialAttack;
    }
}
