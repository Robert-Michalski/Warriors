package Model;

import Model.Warrior;

public class Knight extends Warrior {
    private static final int ATTACK = 7;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public String toString() {
        return "Model.Knight{" +
                "health=" + getHealth() +
                "attack=" + ATTACK +
                '}';
    }
}
