package Model;

public class Vampire extends Warrior {
    private final int INITIAL_HEALTH = 40;
    private final int ATTACK = 4;
    private final int VAMPIRISM = 50;

    public Vampire() {
        setHealth(INITIAL_HEALTH);
    }

    public void attack(Warrior enemy) {
        var actualDamage = 4;
        if (enemy instanceof Defender defender) {
            if (getAttack() > (defender.getDefense())) {
                enemy.setHealth(enemy.getHealth() - (getAttack() - defender.getDefense()));
                healSelfByAmount((actualDamage - defender.getDefense() * 50) / 100);
            }
        } else {
            enemy.setHealth(enemy.getHealth() - getAttack());
            healSelfByAmount((actualDamage * 50) / 100);
        }
    }

    public void healSelfByAmount(int amount) {
        this.setHealth(this.getHealth() + amount);
        if (this.getHealth() > this.INITIAL_HEALTH)
            this.setHealth(this.INITIAL_HEALTH);
    }

    public int getVAMPIRISM() {
        return VAMPIRISM;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public String toString() {
        return "Vampire{" +
                "health=" + getHealth() +
                "attack=" + getAttack() +
                "vampirism=" + getVAMPIRISM() +
                '}';
    }
}
