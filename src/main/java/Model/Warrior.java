package Model;

public class Warrior implements Unit, IWarrior {
    private final int INITIAL_HEALTH = 50;
    private int health;
    public Warrior() {
        setHealth(INITIAL_HEALTH);
    }
    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        health-=damage;
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

    @Override
    public String toString() {
        return "Warrior{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                '}';
    }


}
