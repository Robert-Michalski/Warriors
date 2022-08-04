package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defender extends Warrior {
    private final int INITIAL_HEALTH = 60;
    private static final int ATTACK = 3;
    private static final int DEFENSE = 2;

    public Defender() {
        setHealth(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefense() {
        return DEFENSE;
    }

    public int getInitial_Health() {
        return INITIAL_HEALTH;
    }

    @Override
    public void receiveHit(int damage) {
        if (damage < getDefense()) {
            reduceHealthBasedOnDamage(0);
            logger.info("Too little damage to go through defense");
        } else {
            super.reduceHealthBasedOnDamage(damage - getDefense());
            logger.info("Defender took {} damage", damage - getDefense());
        }
    }

    @Override
    public String toString() {
        return "Defender{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                "defense=" + getDefense() +
                '}';
    }
}
