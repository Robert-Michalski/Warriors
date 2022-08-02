package Model;

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
    public void reduceHealthBasedOnDamage(int damage) {
        if (damage < getDefense())
            super.reduceHealthBasedOnDamage(0);
        else
            super.reduceHealthBasedOnDamage(damage-getDefense());
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
