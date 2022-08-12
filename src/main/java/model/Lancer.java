package model;

public class Lancer extends Warrior {

    private int initialHealth = 50;
    private int attack = 6;
    private int piercing = 50;

    public Lancer() {
        setHealth(initialHealth);
    }

    @Override
    public void hit(Warrior opponent) {
        super.hit(opponent);
        if (opponent.getWarriorBehind() != null) {
            logger.trace("{} pierces and hits {}", this, opponent.getWarriorBehind());
            hitWithPenalty(opponent);
        }
    }

    public void hitWithPenalty(Warrior opponent) {
        if (!(opponent instanceof Defender defender)) {
            opponent.getWarriorBehind().receiveHit((getAttack()*piercing)/100);
        } else {
            opponent.getWarriorBehind().receiveHit(((getAttack()-defender.getDefense())*piercing)/100);
        }

    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
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
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPiercing() {
        return piercing;
    }

    @Override
    public String toString() {
        return "Lancer{" +
                "health=" + getHealth() +
                ", attack=" + getAttack() +
                ", PIERCING=" + getPiercing() +
                '}';
    }
}
