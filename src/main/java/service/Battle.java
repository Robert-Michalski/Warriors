package service;

import model.Army;
import model.Warrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Battle {
    private Battle() {
        throw new IllegalArgumentException();
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        if (warrior2 == null) {
            return true;
        }
        if (warrior1 == null) {
            return false;
        }
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
        if (army1.getTroops().isEmpty()) {
            return false;
        }
        if (army2.getTroops().isEmpty()) {
            return true;
        }
        Logger logger = LoggerFactory.getLogger("Straight Fight");
        logger.info("Straight Fight started !");
        int round = 1;
        while (!army1.getTroops().isEmpty() && !army2.getTroops().isEmpty()) {
        //army.isEmpty()
            logger.info("Round {}", round);
            for (int i = 0; i < army1.getTroops().size() && i<army2.getTroops().size(); i++) {
                fight(army1.getWarrior(i), army2.getWarrior(i));
            }
            round++;
            army1.removeDeadWarriors();
            army2.removeDeadWarriors();
            logger.info("Army 1 after round {} : {}", round, army1.getTroops());
            logger.info("Army 2 after round {} : {}", round, army2.getTroops());
        }


        return army2.getTroops().isEmpty();
    }
}