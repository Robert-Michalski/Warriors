package Service;

import Model.*;

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
            case VAMPIRE -> {
                return new Vampire();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
