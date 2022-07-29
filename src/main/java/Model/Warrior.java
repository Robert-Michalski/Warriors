package Model;

public class Warrior implements Unit {
    private int health = 50;
    private static final int ATTACK = 5;

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    //TODO override to Defender
    public void attack(Warrior enemy) {
        if(enemy instanceof Defender){
            if(getAttack() > ((Defender) enemy).getDefense()){
                enemy.setHealth(enemy.getHealth() - (getAttack() - ((Defender) enemy).getDefense()));
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
