package Model;

public class Warrior implements Unit, IWarrior {
    private final int INITIAL_HEALTH = 50;
    private int health;

    public Warrior() {
        setHealth(INITIAL_HEALTH);
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        health -= damage;
    }
    Warrior warriorBehind = null;

    public Warrior getWarriorBehind(){
        return warriorBehind;
    }
    public void setWarriorBehind(Warrior warriorBehind){
        this.warriorBehind = warriorBehind;
    }
    public int getInitial_Health() {
        return INITIAL_HEALTH;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return 5;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                '}';
    }


}
