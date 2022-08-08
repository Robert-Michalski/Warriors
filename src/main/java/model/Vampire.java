package model;

public class Vampire extends Warrior {
    private int initialHealth = 40;
    private int attack = 4;
    private int vampirism = 50;

    public Vampire() {
        setHealth(initialHealth);
    }

    @Override
    public void hit(Warrior opponent) {
        int x1 = opponent.getHealth();
        int healthBeforeAttack = getHealth();
        super.hit(opponent);
        int x2 = opponent.getHealth();
        healSelfByAmount(((x1 - x2) * vampirism) / 100);
        int healthAfterAttack = getHealth();
        logger.trace("{} heals for {} units", this, healthAfterAttack - healthBeforeAttack);
    }

    public void healSelfByAmount(int amount) {
        this.setHealth(this.getHealth() + amount);
        if (this.getHealth() > this.initialHealth) {
            this.setHealth(this.initialHealth);
            logger.trace("{} will not overheal, his health is full", this);
        }

    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        growVampirismByAmount(weapon.getVampirism());
        super.equipWeapon(weapon);
    }

    public void growVampirismByAmount(int amount) {
        setVampirism(getVampirism() + amount);
    }

    public void setVampirism(int vampirism) {
        this.vampirism = vampirism;
        if (this.vampirism < 0) {
            this.vampirism = 0;
        }
    }

    public int getVampirism() {
        return vampirism;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    @Override
    public String toString() {
        String toPrint = super.toString().substring(0, super.toString().length() - 1);
        return toPrint +
                " vampirism=" + getVampirism()
                + "}";
    }
}
