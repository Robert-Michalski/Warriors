package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public interface IWarrior extends HasHealth, CanAttack {
    Logger logger = LoggerFactory.getLogger(IWarrior.class);
    default void hit(IWarrior opponent) {
        opponent.receiveHit(this);

    }

    default void receiveHit(CanAttack damageDealer) {
        reduceHealthBasedOnDamage(damageDealer.getAttack());
        logger.trace("{} received hit from {} with {} damage", this, damageDealer, damageDealer.getAttack());
    }

}

interface HasHealth {
    int getHealth();

    default boolean isAlive() {
        if (getHealth() > 0) {
            return true;
        } else {
            return false;
        }
    }

    void reduceHealthBasedOnDamage(int damage);
}

interface CanAttack {
    int getAttack();
}