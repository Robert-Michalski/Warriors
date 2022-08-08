package model;

public class Healer extends Warrior {
    private int initialHealth = 60;
    private int attack = 0;
    private int healPower = 2;
    private static final int HEAL_UNITS = 2;

    public Healer() {
        setHealth(initialHealth);
    }

    public void heal(Warrior warrior) {
        logger.trace("Healing {} for {} units", warrior, getHealPower());
        warrior.setHealth(warrior.getHealth() + getHealPower());
        if (warrior.getHealth() > warrior.getInitialHealth()) {
            warrior.setHealth(warrior.getInitialHealth());
            logger.trace("{} has full hp}", warrior);
        } else {
            logger.trace("{} was healed and now has {} health}", warrior, warrior.getHealth());
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        growHealPowerByAmount(weapon.getHealPower());
        super.equipWeapon(weapon);
    }
    public void growHealPowerByAmount(int amount){
        setHealPower(getHealPower()+amount);
    }
    @Override
    public void hit(Warrior opponent) {
        logger.trace("Healer does not hit");
    }

    @Override
    public void process(Warrior warrior) {
        if (warriorInFront != null) {
            heal(warriorInFront);
        }
        super.process(warrior);
    }

    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealPower() {
        return healPower;
    }

    @Override
    public String toString() {
        return "Healer{" +
                "health=" + getHealth() +
                ", attack=" + getAttack() +
                ", healPower=" + getHealPower() +
                '}';
    }
}
