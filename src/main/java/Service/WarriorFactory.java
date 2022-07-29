package Service;

import Model.Defender;
import Model.Knight;
import Model.Unit;
import Model.Warrior;

public class WarriorFactory {

    public Warrior getInstance(Unit.UnitType type) {
        switch (type) {
            case WARRIOR -> {
                return new Warrior();
            }
            case KNIGHT -> {
                return new Knight();
            }
            case DEFENDER -> {
                return new Defender();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
