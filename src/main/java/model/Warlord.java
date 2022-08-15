package model;

import interfaces.IWeapon;
import model.strategies.WarStrategy;
import model.strategies.WarlordStrategy;

import java.util.Objects;

public class Warlord extends Defender{
    private int initialHealth = 100;
    private int attack = 4;
    private int defense = 2;
    private int health;
//    private static Warlord instance;
    private final WarStrategy strategy = new WarlordStrategy();
    public Warlord(){
        health = initialHealth;
    }
//    public static Warlord getInstance(){
//        if(instance == null){
//            instance = new Warlord();
//        }
//        return instance;
//    }


    @Override
    public void reduceHealthBy(int attack) {
        if(attack > getDefense()){
            logger.trace("{} takes {} damage", this, attack - getDefense());
            this.health -= attack - getDefense();
        }
        else {
            logger.trace("Too little dmg to go pass through defense");
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public WarStrategy getStrategy() {
        return strategy;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warlord Warlord = (Warlord) o;
        return initialHealth == Warlord.initialHealth && attack == Warlord.attack && defense == Warlord.defense && Objects.equals(strategy, Warlord.strategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialHealth, attack, defense, strategy);
    }

    @Override
    public String toString() {
        return "Warlord{" +
                "health=" + getHealth() +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}
