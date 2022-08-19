package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.Battle;

public class ArcherTests {
    @Test
    @DisplayName("Given ")
    void test48() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.ARCHER, 1).lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.KNIGHT, 10).lineUp();
        Battle.fight(army1, army2);
        System.out.println(army2);
    }

    @Test
    @DisplayName("Given archer has no cover he cannot attack")
    void GivenArcherHasNoCoverThenHeCannotAttack() {
        //GIVEN
        var unit1 = new Archer();
        var unit2 = new Warrior();
        //WHEN
        boolean result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertFalse(result),
                () -> Assertions.assertSame(unit2.getInitialHealth(), unit2.getHealth())
        );
    }

    @Test
    @DisplayName("Given an army of 1 Warrior and an army of 1 Archer and 1 Warrior then first army wins")
    void GivenAnArmyOf1WarriorAndAnArmyOf1ArcherAnd1WarriorThenFirstArmyWins() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.ARCHER, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        boolean result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }
    @Test
    @DisplayName("Given archer attacks 3 times he cannot attack anymore")
    void GivenArcherAttacks3TimesHeCannotAttackAnymore(){
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR,1)
                .addUnits(Unit.UnitType.ARCHER, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5)
                .lineUp();
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        var expectedHealth = army2.getWarrior(0).getHealth();
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        Assertions.assertSame(expectedHealth, army2.getWarrior(0).getHealth());
    }
    @Test
    @DisplayName("Given archer with a quiver he can attack 6 times")
    void GivenArcherWithAQuiverHeCanAttack6Times() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.ARCHER, 1).lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR,5)
                .lineUp();
        army1.equipWarriorAtPosition(1, Weapon.newQuiver());
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        var expectedHealth = army2.getWarrior(0).getHealth();
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        army1.getWarrior(1).hit(army2.getWarrior(0));
        Assertions.assertSame(expectedHealth, army2.getWarrior(0).getHealth());
    }
}
