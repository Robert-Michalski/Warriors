package model.strategies;

import model.Army;

public class WarlordStrategy implements WarStrategy{

    @Override
    public void moveUnits() {
        System.out.println("strategy warlord moving units now !");
    }
}
