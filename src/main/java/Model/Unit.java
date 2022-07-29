package Model;


public interface Unit {
    public enum UnitType {
        WARRIOR, KNIGHT, DEFENDER
    }
    void attack(Warrior enemy);
}
