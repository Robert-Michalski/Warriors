package model;

import interfaces.HasPiercing;
import interfaces.IWarrior;
import interfaces.command.ArrowRainCommand;
import interfaces.command.ICommand;

public class Archer extends Warrior{
    private int initialHealth = 40;
    private int health;
    private int attack = 6;

    public Archer() {
        this.health=initialHealth;
    }

    @Override
    public void hit(IWarrior opponent) {
        opponent.process(new ArrowRainCommand(this), opponent);
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
}
