package model;

import interfaces.IWarrior;
import interfaces.IWeapon;
import interfaces.command.ArrowRainCommand;
import interfaces.command.HealCommand;
import interfaces.command.ICommand;

public class Warrior implements IWarrior {
    private int attack = 5;
    private int initialHealth = 50;
    private int health;
    Warrior warriorBehind = null;
    Warrior warriorInFront = null;

    public Warrior() {
        this.health = initialHealth;
    }

    @Override
    public void hit(IWarrior opponent) {
        IWarrior.super.hit(opponent);
        process(new HealCommand(), this);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        logger.info("{} equipped {} weapon", this, weapon);
        growInitialHealthByAmount(weapon.getHealth());
        growAttackByAmount(weapon.getAttack());
        logger.info("new statistics {}", this);
    }

    @Override
    public void process(ICommand command, IWarrior warrior) {
        if (getWarriorBehind() != null) {
            if(!(command instanceof ArrowRainCommand))
            warrior.process(command, warrior.getWarriorBehind());
            if(command instanceof ArrowRainCommand arrowRainCommand){
                logger.debug("{} attacks {}", arrowRainCommand.getArcher(), warrior);
                warrior.reduceHealthBy(arrowRainCommand.getArcher().getAttack());
                if (getWarriorBehind() != null) {
                    if(arrowRainCommand.getArcher().getAttack()>0) {
                        arrowRainCommand.getArcher().setAttack(arrowRainCommand.getArcher().getAttack() - 1);
                    }
                    if(arrowRainCommand.getArcher().getArrowsPacks()>0 && arrowRainCommand.getArcher().getAttack()>0)
                    warrior.process(new ArrowRainCommand(arrowRainCommand.getArcher()), warrior.getWarriorBehind());
                    else {
                        arrowRainCommand.getArcher().setAttack(arrowRainCommand.getArcher().getInitialAttack());
                    }
                }
            }
        }

    }



    @Override
    public void reduceHealthBy(int attack) {
        int healthBeforeAttack = this.getHealth();
        health -= attack;
        int healthAfterAttack = this.getHealth();
        int amount = healthBeforeAttack - healthAfterAttack;
        logger.trace("{} took {} damage", this, amount);
    }

    public void growInitialHealthByAmount(int amount) {
        setInitialHealth(getInitialHealth() + amount);
        setHealth(getInitialHealth());
    }

    public void growAttackByAmount(int amount) {
        setAttack(getAttack() + amount);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public IWarrior getWarriorBehind() {
        return warriorBehind;
    }

    public void setWarriorBehind(Warrior warriorBehind) {
        this.warriorBehind = warriorBehind;
    }

    public IWarrior getWarriorInFront() {
        return warriorInFront;
    }

    public void setWarriorInFront(Warrior warriorInFront) {
        this.warriorInFront = warriorInFront;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "health = " + health +
                " attack = " + attack +
                '}';
    }
}
