package model;

import interfaces.CanHeal;
import interfaces.HasHealth;
import interfaces.IWarrior;
import interfaces.IWeapon;
import interfaces.command.HealCommand;
import interfaces.command.ICommand;

public class Healer extends Warrior implements CanHeal {
    private int initialHealth = 60;
    private int attack = 0;
    private int health;
    private int healPower = 2;

    public Healer() {
        health = initialHealth;
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public void heal(HasHealth warrior) {
        logger.trace("Healing {} for {} units", warrior, getHealPower());
        warrior.setHealth(warrior.getHealth() + getHealPower());
        if (warrior.getHealth() > warrior.getInitialHealth()) {
            warrior.setHealth(warrior.getInitialHealth());
            logger.trace("{} has full hp}", warrior);
        } else {
            logger.trace("{} was healed and now has {} health}", warrior, warrior.getHealth());
        }
    }

    @Override
    public void process(ICommand command, IWarrior warrior) {
        if (command instanceof HealCommand) {
            if (warriorInFront != null) {
                heal(warriorInFront);
            }
        }
        if (getWarriorBehind() != null)
            super.process(command, warrior);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        growHealPowerBy(weapon.getHealPower());
        super.equipWeapon(weapon);

    }

    private void growHealPowerBy(int amount) {
        healPower += amount;
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
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    @Override
    public String toString() {
        return "Healer{" +
                "initialHealth=" + initialHealth +
                ", attack=" + attack +
                ", health=" + health +
                ", healPower=" + healPower +
                '}';
    }
}
