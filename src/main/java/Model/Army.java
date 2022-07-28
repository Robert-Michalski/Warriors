package Model;

import Service.WarriorFactory;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<Warrior> troops;
    WarriorFactory warriorFactory;

    public Army() {
        troops = new ArrayList<>();
        warriorFactory = new WarriorFactory();
    }

    public List<Warrior> getTroops() {
        return troops;
    }

    public Army addUnits(String type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(warriorFactory.getInstance(type));
        }
        return this;
    }
}
