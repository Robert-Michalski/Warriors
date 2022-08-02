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
    public Warrior getNextWarriorIfExists(IWarrior enemy) {
        var index = this.troops.indexOf(enemy);
        if((this.troops.get(index+1)!=null)){
            return this.troops.get(index + 1);
        }
        else {
            return null;
        }
    }

    public Army addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(warriorFactory.getInstance(type));
        }
        return this;
    }
}
