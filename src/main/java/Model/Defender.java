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



    @Override
    public String toString() {
        return "Defender{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                "defense=" + getDefense() +
                '}';
    }
}
