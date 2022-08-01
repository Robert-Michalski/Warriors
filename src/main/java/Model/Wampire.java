package Model;

public class Wampire extends Warrior{
    private final int INITIAL_HEALTH = 40;
    private final int ATTACK = 4;
    private final int VAMPIRISM = 50;

    public Wampire() {
        setHealth(INITIAL_HEALTH);
    }
}
