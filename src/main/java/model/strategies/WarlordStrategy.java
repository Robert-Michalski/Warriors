package model.strategies;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarlordStrategy implements WarStrategy {
    Logger logger = LoggerFactory.getLogger(WarlordStrategy.class);

    @Override
    public void moveUnits(Army army) {
        logger.debug("Moving units as Warlord commands !");
        //4 Warlord should always be last
        for (int i = 0; i < army.getSize(); i++) {
            if (!(army.getWarrior(army.getSize()-1) instanceof Warlord warlord)) {
                logger.debug("Warlord is not last, shifting");
                army.shiftRight(army, army.getWarrior(i));
            }
        }
        //2
        if (army.hasLancers()) {
            //2. Shift healers to be behind first lancer
            //      1. Shift lancers to the front
            for (int i = 0; i < army.getSize(); i++) {
                if (army.getWarrior(i) instanceof Lancer lancer) {
                    logger.debug("{} and {} are changing places", lancer, army.getWarrior(0));
                    army.shiftRight(army, lancer);
                }
            }
//            for (int i = 0; i < army.getSize(); i++) {
//                if (army.getWarrior(i) instanceof Healer healer) {
//                    logger.debug("//2. Shift healers to be behind first lancer");
//                    logger.debug("{} and {} are changing places", healer, army.getWarrior(0));
//                    army.shiftRight(army, healer);
//                }
//            }
//
//
//            for (int i = 0; i < army.getSize(); i++) {
//                if (army.getWarrior(i) instanceof Lancer lancer) {
//                    logger.debug("{} and {} are changing places", lancer, army.getWarrior(0));
//                    army.shiftRight(army, lancer);
//                    break;
//                }
//            }
        }
        if (!army.hasLancers()) {
            //3. If no lancers are present but others who can do dmg shift healers until find someone
            Warrior warriorToFront = null;
            for (int i = 0; i < army.getSize(); i++) {
                if (army.getWarrior(0) instanceof Healer healer) {
                    warriorToFront = army.findFirstWhoCanFight();
                }
                if (warriorToFront != null)
                    logger.debug("//3. If no lancers are present but others who can do dmg shift healers until find someone");
                logger.debug("{} and {} are changing places", army.getWarrior(0), warriorToFront);
                army.shiftRight(army, warriorToFront);
            }
        }


        logger.debug("Army after shifting lancers {}", army);
    }
}
