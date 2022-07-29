package Model;


public class Knight extends Warrior {
    private static final int ATTACK = 7;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "health=" + getHealth() +
                "attack=" + ATTACK +
                '}';
    }
}
