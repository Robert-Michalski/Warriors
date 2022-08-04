package Model;

public class Healer extends Warrior {
    private static final int ATTACK = 0;
    private static final int INITIAL_HEALTH = 60;
    private static final int HEAL_UNITS = 2;

    public Healer() {
        setHealth(INITIAL_HEALTH);
    }

    public void heal(Warrior warrior) {
        logger.info("Healing {} for {} units", warrior, getHealUnits());
        warrior.setHealth(warrior.getHealth() + getHealUnits());
        if (warrior.getHealth() > warrior.getInitial_Health()) {
            warrior.setHealth(warrior.getInitial_Health());
            logger.info("{} has full hp}", warrior);
        } else {
            logger.info("{} was healed and now has {} health}", warrior, warrior.getHealth());
        }
    }

    @Override
    public void process(Warrior warrior) {
        if (warriorInFront != null) {
            heal(warriorInFront);
        }
        super.process(warrior);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getHealUnits() {
        return HEAL_UNITS;
    }
}