package model;

public class Warlorld extends Defender{
    private int initialHealth = 60;
    private int attack = 3;
    private int defense = 2;
    public Warlorld() {
        setHealth(initialHealth);
        setAttack(attack);
        setDefense(defense);
    }

    @Override
    public void receiveHit(int damage) {
        super.receiveHit(damage);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
    }

    @Override
    public void growDefenseByAmount(int amount) {
        super.growDefenseByAmount(amount);
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
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Warlorld{" +
                "health=" + getHealth() +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}
