public class Warrior {
    private int health = 50;
    private static final int ATTACK = 5;

    public boolean isAlive() {
        return health > 0;
    }

    public int getAttack() {
        return ATTACK;
    }

    int getHealth() {
        return health;
    }

    public void attack(Warrior enemy) {
        enemy.health -= getAttack();
    }
}
