package model.strategies;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarlordStrategy implements WarStrategy {
    Logger logger = LoggerFactory.getLogger(WarlordStrategy.class);

    @Override
    public void moveUnits(Army army) {
        logger.debug("Moving units as Warlord commands !");
        moveWarlordToLastPosition(army);
        moveLancersToTheFront(army);
        moveFirstAttackerToTheFront(army);
        moveHealersBehindAttacker(army);
        logger.debug("Army after shifting {}", army);
    }


    private void moveWarlordToLastPosition(Army army) {
        //4 Warlord should always be last
        for (int i = 0; i < army.getSize(); i++) {
            if(army.getWarrior(i) instanceof Warlord warlord)
            while (!(army.getWarrior(army.getSize() - 1) instanceof Warlord))
                army.shiftRight(warlord);
        }
    }

    private void moveLancersToTheFront(Army army) {
        for (int i = 0; i < army.getSize(); i++) {
            if (army.getWarrior(i) instanceof Lancer lancer && i!=0) {
                army.moveToFront(lancer);
            }
        }
    }

    private void moveHealersBehindAttacker(Army army) {
        for (int i = 0; i < army.getSize(); i++) {
            if(army.getWarrior(i) instanceof Healer healer){
                army.moveBehind(healer);
            }
        }
    }
    private void moveFirstAttackerToTheFront(Army army){
        Warrior warrior = army.findFirstWhoCanFight();
        if(army.getWarrior(0) instanceof Healer healer)
        army.moveToFront(warrior);

    }
}
