package model;

import model.strategies.WarStrategy;
import model.strategies.WarlordStrategy;

import java.util.Objects;

public class Warlord extends Defender{
    private int initialHealth = 100;
    private int attack = 4;
    private int defense = 2;
//    private static Warlord instance;
    private final WarStrategy strategy = new WarlordStrategy();
    public Warlord(){
        setHealth(initialHealth);
        setAttack(attack);
        setDefense(defense);
    }
//    public static Warlord getInstance(){
//        if(instance == null){
//            instance = new Warlord();
//        }
//        return instance;
//    }

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

    public WarStrategy getStrategy() {
        return strategy;
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