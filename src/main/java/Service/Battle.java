package Service;

import Model.Army;
import Model.Warrior;

public class Battle {
    private Battle() {
        throw new IllegalArgumentException();
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        return warrior1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        int army1Length = army1.getTroops().size();
        int army2Length = army2.getTroops().size();

        int army1WarriorIndex = 0;
        int army2WarriorIndex = 0;

        Warrior army1LastWarrior = army1.getTroops().get(army1Length - 1);
        Warrior army2LastWarrior = army2.getTroops().get(army2Length - 1);

        while (army1LastWarrior.isAlive() && army2LastWarrior.isAlive()) {
            if(fight(army1.getTroops().get(army1WarriorIndex), army2.getTroops().get(army2WarriorIndex))){
                army2WarriorIndex++;
            }
            else {
                army1WarriorIndex++;
            }
        }

        return army1LastWarrior.isAlive();
    }
}