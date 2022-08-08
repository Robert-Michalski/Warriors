package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface IWarrior extends HasHealth, CanAttack {
    Logger logger = LoggerFactory.getLogger(IWarrior.class);

    public abstract void process(Warrior warrior);

    default void hit(Warrior opponent) {
        logger.trace("{} hits {}", this, opponent);
        opponent.receiveHit(this.getAttack());
    }

    default void receiveHit(int damage) {
        reduceHealthBasedOnDamage(damage);
        logger.trace("{} took {} damage", this, damage);
    }
   void equipWeapon(IWeapon weapon);
}

interface HasHealth {
    int getHealth();

    default boolean isAlive() {
        return getHealth() > 0 ? true : false;
    }

    void reduceHealthBasedOnDamage(int damage);
}

interface CanAttack {
    int getAttack();
}