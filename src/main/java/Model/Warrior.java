package Model;

import java.util.Optional;

public class Warrior implements Unit, IWarrior {
    private final int INITIAL_HEALTH = 50;
    private int health;

    Warrior warriorBehind = null;
    Warrior warriorInFront = null;

    public Warrior() {
        setHealth(INITIAL_HEALTH);
    }


    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        health -= damage;
    }

    @Override
    public void hit(Warrior opponent) {
        IWarrior.super.hit(opponent);
        process(this);
    }

    public Warrior getWarriorBehind() {
        return warriorBehind;
    }

    public void setWarriorBehind(Warrior warriorBehind) {
        this.warriorBehind = warriorBehind;
    }

    public int getInitial_Health() {
        return INITIAL_HEALTH;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return 5;
    }

    public int getHealth() {
        return health;
    }

    public Warrior getWarriorInFront() {
        return warriorInFront;
    }

    public void setWarriorInFront(Warrior warriorInFront) {
        this.warriorInFront = warriorInFront;
    }

    @Override
    public String toString() {
        return getClass().getName().substring(6) + "{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                '}';
    }


    @Override
    public void process(Warrior warrior) {
        if (warriorBehind != null) {
            warrior.process(warrior.getWarriorBehind());
        }
    }
}
