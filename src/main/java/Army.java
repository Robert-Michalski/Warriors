import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<Warrior> army;
    WarriorFactory warriorFactory;

    public Army() {
        army = new ArrayList<>();
        warriorFactory = new WarriorFactory();
    }

    List<Warrior> getArmy() {
        return army;
    }

    public void addUnits(String type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            army.add(warriorFactory.getInstance(type));
        }
    }
}
