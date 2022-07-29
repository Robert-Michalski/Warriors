package Model;

import Model.Warrior;

public interface Unit {
    public static enum UnitType {
        WARRIOR, KNIGHT, DEFENDER
    }
    void attack(Warrior enemy);
}
