package Model;


public interface Unit {
    public enum UnitType {
        WARRIOR, KNIGHT, DEFENDER, VAMPIRE
    }
    void attack(Warrior enemy);
}
