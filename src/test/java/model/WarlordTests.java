package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.Battle;

public class WarlordTests {
    @Test
    @DisplayName("Warlord smoke test")
    void test35() {
        var ronald = new Warlord();
        var heimdall = new Knight();
        var myArmy = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 2)
                .addUnits(Unit.UnitType.HEALER, 2)
                .lineUp();
        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.WARLORD, 3)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.HEALER, 2)
                .addUnits(Unit.UnitType.KNIGHT, 2)
                .lineUp();
        myArmy.processStrategy();
        enemyArmy.processStrategy();

        Assertions.assertAll(
                () -> Assertions.assertFalse(Battle.fight(heimdall, ronald)),
                () -> Assertions.assertTrue(myArmy.getWarrior(0) instanceof Lancer),
                () -> Assertions.assertTrue(myArmy.getWarrior(1) instanceof Healer),
                () -> Assertions.assertTrue(myArmy.getWarrior(myArmy.getSize() - 1) instanceof Warlord),
                () -> Assertions.assertTrue(enemyArmy.getWarrior(0) instanceof Vampire),
                () -> Assertions.assertTrue(enemyArmy.getWarrior(enemyArmy.getSize() - 1) instanceof Warlord),
                () -> Assertions.assertTrue(enemyArmy.getWarrior(enemyArmy.getSize() - 2) instanceof Knight),
                () -> Assertions.assertSame(enemyArmy.getSize(), 6),
                () -> Assertions.assertTrue(Battle.fight(myArmy, enemyArmy))
        );
    }

    @Test
    @DisplayName("Given no lancers are present in army then first warrior that can fight will be in front")
    void GivenNoLancersArePresentInArmyThenFirstWarriorThatCanFightWillBeInFront() {
        //GIVEN
        var army = new Army()
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        //WHEN
        army.processStrategy();
        //THEN
        Assertions.assertTrue(army.getWarrior(0) instanceof Warrior);

    }

    @Test
    @DisplayName("Given Lancer and Healer in an Army then Lancer is first and Healer is behind him")
    void GivenLancerAndHealerInAnArmyThenLancerIsFirstAndHealerIsBehindHim() {
        var army = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        army.processStrategy();
        Assertions.assertAll(
                () -> Assertions.assertTrue(army.getWarrior(0) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(1) instanceof Healer),
                () -> Assertions.assertTrue(army.getWarrior(2) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(army.getSize() - 1) instanceof Warlord)
        );
    }

    @Test
    @DisplayName("Given army with at least 1 lancer then lancer is in front of the army")
    void GivenArmyWithAtLeast1LancerThenLancerIsInFrontOfTheArmy() {
        //GIVEN
        var army = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .lineUp();
        //WHEN
        army.processStrategy();
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertTrue(army.getWarrior(0) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(1) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(army.getSize() - 1) instanceof Warlord)
        );
    }
    @Test
    @DisplayName("Given an army then Warlord should be in last position")
    void GivenAnArmyThenWarlordShouldBeInLastPosition() {
        //GIVEN
        var army = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        //WHEN
        army.processStrategy();
        army2.processStrategy();
        System.out.println("after shift " + army2.getTroops());
        //THEN
        Assertions.assertTrue(army.getWarrior(army.getSize() - 1) instanceof Warlord);
        Assertions.assertTrue(army2.getWarrior(army2.getSize() - 1) instanceof Warlord);
    }
}
