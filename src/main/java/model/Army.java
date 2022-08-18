package model;

import interfaces.IWeapon;
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

    public Warrior getWarrior(int index) {
        return troops.get(index);
    }

    public Army removeDeadWarriors() {
        for (ListIterator<Warrior> iterator = troops.listIterator(); iterator.hasNext(); ) {
            Warrior warrior = iterator.next();
            if (!warrior.isAlive()) {
                iterator.remove();
            }
        }
        return this;
    }

    public void equipWarriorAtPosition(int position, IWeapon weapon) {
        getWarrior(position).equipWeapon(weapon);
    }

    public Army addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(warriorFactory.getInstance(type));
        }
        return this;
    }

    private void removeRedundantWarlords() {
        Warlord warlord = lookForWarlord();
        int warlordIndex = troops.indexOf(warlord);
        for (ListIterator<Warrior> iterator = troops.listIterator(); iterator.hasNext(); ) {
            Warrior warrior = iterator.next();
            if (warrior instanceof Warlord) {
                if (warlordIndex != troops.indexOf(warrior))
                    iterator.remove();
            }
        }
    }

    public void processStrategy() {
        Warlord warlord = lookForWarlord();
        removeRedundantWarlords();
        if (warlord != null) {
            warlord.getStrategy().moveUnits(this);
        }
    }

    public void shiftRight(Warrior warrior) {
        Warrior behind = troops.get(troops.indexOf(warrior)+1);
        if(behind==null)
            return;

        int warriorIndex = troops.indexOf(warrior);
        int behindIndex = troops.indexOf(behind);
        troops.add(++behindIndex, warrior);
        troops.remove(warriorIndex);
    }
    public void moveToFront(Warrior warrior){
        int warriorIndex = troops.indexOf(warrior);
        for(int i=0; i< troops.size(); i++){
            logger.trace("Unit at {} is {}",i, troops.get(i));
        }
        troops.add(0, warrior);
        for(int i=0; i< troops.size(); i++){
            logger.trace("Unit at {} is {}",i, troops.get(i));
        }
        logger.debug("Removing {}", troops.get(warriorIndex++));
        troops.remove(warriorIndex);
    }
    public void moveBehind(Warrior unitToPutBehind){
        int warriorIndex = troops.indexOf(unitToPutBehind);
        troops.add(1, unitToPutBehind);
        warriorIndex++;
        troops.remove(warriorIndex);
    }
    public void moveFirstAttackerToTheFront(Army army){

    }
    public Warrior findFirstWhoCanFight() {
        for (Warrior troop : troops) {
            if (!(troop instanceof Healer)) {
                return troop;
            }
        }
        return null;
    }
    public int getSize() {
        return troops.size();
    }

    private Warlord lookForWarlord() {
        for (Warrior troop : troops) {
            if (troop instanceof Warlord warlord) {
                return warlord;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Army{" +
                "troops=" + troops +
                '}';
    }

}
