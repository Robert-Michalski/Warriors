package model;

public class Lancer extends Warrior {
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
            opponent.getWarriorBehind().receiveHit((getAttack()*PIERCING)/100);
        } else {
            opponent.getWarriorBehind().receiveHit(((getAttack()-defender.getDefense())*PIERCING)/100);
        }

    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
