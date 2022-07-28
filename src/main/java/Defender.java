public class Defender extends Warrior{
    private int health=60;
    private static final int ATTACK = 3;
    private static final int DEFENSE = 2;

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
                "health=" + health +
                "attack=" + ATTACK +
                "defense=" + DEFENSE +
                '}';
    }
}
