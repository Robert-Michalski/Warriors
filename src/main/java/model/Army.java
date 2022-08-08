package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Army {
    Logger logger = LoggerFactory.getLogger(Army.class);
    private final List<Warrior> troops;
    private final WarriorFactory warriorFactory;

    public Army() {
        troops = new ArrayList<>();
        warriorFactory = new WarriorFactory();
    }

    public List<Warrior> getTroops() {
        return troops;
    }

    public Army lineUp() {
        for (int i = 0; i < troops.size(); i++) {
            if (i + 1 < troops.size()) {
                troops.get(i).setWarriorBehind(troops.get(i + 1));
            }
            if (i - 1 >= 0) {
                troops.get(i).setWarriorInFront(troops.get(i - 1));
            }
        }
        return this;
    }
    public Warrior getWarrior(int index){
        return troops.get(index);
    }

    public Army removeDeadWarriors(){
        for(ListIterator<Warrior> iterator = troops.listIterator(); iterator.hasNext();){
            Warrior warrior = iterator.next();
            if(!warrior.isAlive()){
                iterator.remove();
            }
        }
        return this;
    }
    public void equipWarriorAtPosition(int position, IWeapon weapon){
        getWarrior(position).equipWeapon(weapon);
    }
    public Army addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(warriorFactory.getInstance(type));
        }
        return this;
    }

    @Override
    public String toString() {
        return "Army{" +
                "troops=" + troops +
                '}';
    }
}
