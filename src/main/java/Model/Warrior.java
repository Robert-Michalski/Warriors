package Model;

public class Warrior implements Unit {
    private final int INITIAL_HEALTH = 50;
    private int health;
    private static final int ATTACK = 5;

    public Warrior() {
        setHealth(INITIAL_HEALTH);
    }


    public boolean isAlive() {
        return getHealth() > 0;
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }
    public int getInitial_Health(){
        return INITIAL_HEALTH;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void attack(Warrior enemy) {
        if(enemy instanceof Defender defender){
            if(getAttack() > (defender.getDefense())){
                enemy.setHealth(enemy.getHealth() - (getAttack() - defender.getDefense()));
            }
        }
        else {
            enemy.setHealth(enemy.getHealth()-getAttack());
        }
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "health=" + health +
                "attack=" + ATTACK +
                '}';
    }
}
