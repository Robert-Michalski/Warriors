package Model;

import Service.WarriorFactory;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<Warrior> troops;
    private WarriorFactory warriorFactory;

    public Army() {
        troops = new ArrayList<>();
        warriorFactory = new WarriorFactory();
    }

    public List<Warrior> getTroops() {
        return troops;
    }

    public Army addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(warriorFactory.getInstance(type));
        }
        for (int i = 0; i < troops.size(); i++){
            if (i + 1 < troops.size()) {
                troops.get(i).setWarriorBehind(troops.get(i + 1));
            }
            if (i-1>=0){
                troops.get(i).setWarriorInFront(troops.get(i-1));
            }
        }

        return this;
    }

}
