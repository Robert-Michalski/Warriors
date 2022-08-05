package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.WarriorFactory;

import java.util.ArrayList;
import java.util.List;

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


    public Army removeDeadWarriors(){
        for(int i = 0; i< troops.size(); i++){
            if (!troops.get(i).isAlive()){
                logger.info("removing {}", getTroops().get(i));
                troops.remove(getTroops().get(i));
                logger.info("Now army is {}", getTroops());
            }

        }
        return this;
    }

    public Army addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(warriorFactory.getInstance(type));
        }
        return this;
    }
}
