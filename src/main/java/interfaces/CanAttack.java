package interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CanAttack{
    Logger logger = LoggerFactory.getLogger(CanAttack.class);

    int getAttack();

    default void hit(IWarrior opponent) {
        logger.trace("{} attacks {}", this, opponent);
        opponent.reduceHealthBy(this.getAttack());

    }
}
