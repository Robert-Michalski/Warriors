package Model;

public class Lancer extends Warrior{
    private final int INITIAL_HEALTH = 60;
    private final int ATTACK = 6;

    public Lancer() {
        setHealth(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
