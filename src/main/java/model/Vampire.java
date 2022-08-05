package model;

public class Vampire extends Warrior {
    private final int INITIAL_HEALTH = 40;
    private final int ATTACK = 4;
    private final int VAMPIRISM = 50;

    public Vampire() {
        setHealth(INITIAL_HEALTH);
    }

    @Override
    public void hit(Warrior opponent) {
        int x1 = opponent.getHealth();
        int healthBeforeAttack = getHealth();
        super.hit(opponent);
        int x2 = opponent.getHealth();
        healSelfByAmount(((x1-x2)*VAMPIRISM)/100);
        int healthAfterAttack = getHealth();
        logger.info("{} heals for {} units", this,healthAfterAttack-healthBeforeAttack);
    }

    public void healSelfByAmount(int amount) {
        this.setHealth(this.getHealth() + amount);
        if (this.getHealth() > this.INITIAL_HEALTH) {
            this.setHealth(this.INITIAL_HEALTH);
            logger.info("{} will not overheal, his health is full", this);
        }

    }

    public int getVAMPIRISM() {
        return VAMPIRISM;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
    public int getInitial_Health(){
        return INITIAL_HEALTH;
    }

}
