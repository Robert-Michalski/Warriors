package model;

public class Warrior implements Unit, IWarrior {
    private int initialHealth = 50;
    private int attack = 5;
    private int health;

    Warrior warriorBehind = null;
    Warrior warriorInFront = null;

    public Warrior() {
        setHealth(getInitialHealth());
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        health -= damage;
    }

    @Override
    public void hit(Warrior opponent) {
        IWarrior.super.hit(opponent);
        process(this);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        logger.info("{} equipped {} weapon", this, weapon);
        growInitialHealthByAmount(weapon.getHealth());
        growAttackByAmount(weapon.getAttack());
        logger.info("new statistics {}", this);
    }

    public Warrior getWarriorBehind() {
        return warriorBehind;
    }

    public void setWarriorBehind(Warrior warriorBehind) {
        this.warriorBehind = warriorBehind;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void growInitialHealthByAmount(int amount) {
        setInitialHealth(getInitialHealth() + amount);
        setHealth(getInitialHealth());
    }

    public void growAttackByAmount(int amount) {
        setAttack(getAttack() + amount);
    }

    public int getHealth() {
        return health;
    }

    public Warrior getWarriorInFront() {
        return warriorInFront;
    }

    public void setWarriorInFront(Warrior warriorInFront) {
        this.warriorInFront = warriorInFront;
    }

    @Override
    public String toString() {
        return getClass().getName().substring(6) + "{" +
                "health=" + getHealth() +
                " attack=" + getAttack() +
                '}';
    }


    @Override
    public void process(Warrior warrior) {
        if (warriorBehind != null) {
            warrior.process(warrior.getWarriorBehind());
        }
    }
}
