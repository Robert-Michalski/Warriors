package Model;

public class Wampire extends Warrior{
    private final int INITIAL_HEALTH = 40;
    private final int ATTACK = 4;
    private final int VAMPIRISM = 50;

    public Wampire() {
        setHealth(INITIAL_HEALTH);
    }

    public void attack(Warrior enemy) {
        var actualDamage = 4;
        if(enemy instanceof Defender defender){
            if(getAttack() > (defender.getDefense())){
                enemy.setHealth(enemy.getHealth() - (getAttack() - defender.getDefense()));
                healSelfByAmount(actualDamage- defender.getDefense());
            }
        }
        else {
            enemy.setHealth(enemy.getHealth()-getAttack());
            healSelfByAmount(actualDamage);
        }
    }

    public void healSelfByAmount(int amount){
        this.setHealth(this.getHealth()+amount);
    }
}
