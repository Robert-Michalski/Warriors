package Service;

import Model.Army;
import Model.Healer;
import Model.Lancer;
import Model.Warrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Battle {
    private Battle() {
        throw new IllegalArgumentException();
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        Logger logger = LoggerFactory.getLogger("FIGHT");
        logger.debug("Fight between {} and {} has started", warrior1, warrior2);
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        logger.debug("{} won fight", warrior1.isAlive() ? warrior1 : warrior2);
        return warrior1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        Logger logger = LoggerFactory.getLogger("FIGHT");
        logger.debug("Fight between {} and {} has started", army1, army2);
        int army1Length = army1.getTroops().size();
        int army2Length = army2.getTroops().size();

        int army1WarriorIndex = 0;
        int army2WarriorIndex = 0;

        Warrior army1LastWarrior = army1.getTroops().get(army1Length - 1);
        Warrior army2LastWarrior = army2.getTroops().get(army2Length - 1);

        while (army1LastWarrior.isAlive() && army2LastWarrior.isAlive()) {
            if (fight(army1.getTroops().get(army1WarriorIndex), army2.getTroops().get(army2WarriorIndex))) {
                army2WarriorIndex++;
            } else {
                army1WarriorIndex++;
            }
        }
        logger.debug("{} won fight", army1LastWarrior.isAlive() ? army1 : army2);
        return army1LastWarrior.isAlive();
    }

    public static boolean straightFight(Army army1, Army army2) {
        if(army1.getTroops().isEmpty()){
            return false;
        }
        if(army2.getTroops().isEmpty()){
            return true;
        }
        Logger logger = LoggerFactory.getLogger("Straight Fight");
        logger.info("Straight Fight started !");
        Warrior army1FirstWarrior;
        if(army1.getTroops().get(0)!=null) {
            army1FirstWarrior = army1.getTroops().get(0);
        }
        else {
            return false;
        }
        while(!army1.getTroops().isEmpty()){
            for(int i = 0; i<army1.getTroops().size(); i++){
                if(!(army1.getTroops().get(i)instanceof Healer) && !(army2.getTroops().get(i) instanceof  Healer))
                fight(army1.getTroops().get(i), army2.getTroops().get(0));
            }
            army1.removeDeadWarriors();
            army2.removeDeadWarriors();
            if(!army1.getTroops().isEmpty() && !army2.getTroops().isEmpty()) {
                straightFight(army1,army2);
            }
            if(army1.getTroops().isEmpty()){
                return false;
            }
            if(army2.getTroops().isEmpty()){
                return true;
            }
        }


        return army1.getTroops().get(0).isAlive();
    }
}