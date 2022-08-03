package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lancer extends Warrior {
    Logger logger = LoggerFactory.getLogger(Lancer.class);
    private final int INITIAL_HEALTH = 60;
    private final int ATTACK = 6;
    private final int PIERCING = 50;

    public Lancer() {
        setHealth(INITIAL_HEALTH);
    }

    @Override
    public void hit(Warrior opponent) {
        super.hit(opponent);
        if (opponent.getWarriorBehind() != null) {
            logger.info("{} pierces and hits {}", this, opponent.getWarriorBehind());
            hitWithPenalty(opponent);
        }
    }

    public void hitWithPenalty(Warrior opponent) {
        if (!(opponent instanceof Defender defender)) {
            opponent.getWarriorBehind().setHealth((opponent.getWarriorBehind().getHealth() - (getAttack() * 50) / 100));
        } else {
            opponent.getWarriorBehind().setHealth((opponent.getWarriorBehind().getHealth() - ((getAttack()- defender.getDefense()) * 50) / 100));
        }

    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
