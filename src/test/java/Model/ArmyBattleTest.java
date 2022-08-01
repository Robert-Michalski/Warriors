package Model;

import Service.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//TODO change string types to enum types
class ArmyBattleTest {
    @Test
    @DisplayName("Smoke show test to check if code works")
    void smokeShow() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();

        var myArmy = new Army();
        myArmy.addUnits(Unit.UnitType.KNIGHT, 3);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Unit.UnitType.WARRIOR, 3);

        var army3 = new Army();
        army3.addUnits(Unit.UnitType.WARRIOR, 20);
        army3.addUnits(Unit.UnitType.KNIGHT, 5);

        var army4 = new Army();
        army4.addUnits(Unit.UnitType.WARRIOR, 30);
        Assertions.assertAll(
                () -> Assertions.assertTrue(Battle.fight(chuck, bruce)),
                () -> Assertions.assertFalse(Battle.fight(dave, carl)),
                () -> Assertions.assertTrue(chuck.isAlive()),
                () -> Assertions.assertFalse(bruce.isAlive()),
                () -> Assertions.assertTrue(carl.isAlive()),
                () -> Assertions.assertFalse(dave.isAlive()),
                () -> Assertions.assertFalse(Battle.fight(carl, mark)),
                () -> Assertions.assertFalse(carl.isAlive()),
                () -> Assertions.assertTrue(Battle.fight(myArmy, enemyArmy)),
                () -> Assertions.assertFalse(Battle.fight(army3, army4))
        );

    }

    @Test
    @DisplayName("Creating warrior creates a warrior")
    void test02() {
        Army army = new Army();
        army.addUnits(Unit.UnitType.WARRIOR, 1);
        Assertions.assertAll(
                () -> Assertions.assertSame("Model.Warrior", army.getTroops().get(0).getClass().getName()),
                () -> Assertions.assertNotSame(Unit.UnitType.KNIGHT, army.getTroops().get(0).getClass().getName())
        );
    }

    @Test
    @DisplayName("Creating knight creates a knight")
    void test03() {
        Army army = new Army();
        army.addUnits(Unit.UnitType.KNIGHT, 1);
        Assertions.assertAll(
                () -> Assertions.assertNotSame("Model.Warrior", army.getTroops().get(0).getClass().getName()),
                () -> Assertions.assertSame("Model.Knight", army.getTroops().get(0).getClass().getName())
        );

    }

    @Test
    @DisplayName("Given two armies - one having a Warrior second having a Knight WHEN army with Knight should win")
    void test04() {
        Army myArmy = new Army();
        myArmy.addUnits(Unit.UnitType.WARRIOR, 1);
        Army enemyArmy = new Army();
        enemyArmy.addUnits(Unit.UnitType.KNIGHT, 1);

        Assertions.assertFalse(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with two warriors and second with one warriors WHEN first army should win")
    void test05() {
        Army myArmy = new Army();
        myArmy.addUnits(Unit.UnitType.WARRIOR, 2);
        Army enemyArmy = new Army();
        enemyArmy.addUnits(Unit.UnitType.WARRIOR, 1);

        Assertions.assertTrue(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with one warrior and second with two warriors WHEN second army should win")
    void test06() {
        Army myArmy = new Army();
        myArmy.addUnits(Unit.UnitType.WARRIOR, 1);
        Army enemyArmy = new Army();
        enemyArmy.addUnits(Unit.UnitType.WARRIOR, 2);

        Assertions.assertFalse(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with 2 warriors second with 3 warriors WHEN first loses")
    void test07() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 2);
        army2.addUnits(Unit.UnitType.WARRIOR, 3);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors second with 7 warriors WHEN first loses")
    void test08() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 5);
        army2.addUnits(Unit.UnitType.WARRIOR, 7);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 20 warriors second with 21 warriors WHEN first wins")
    void test09() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 20);
        army2.addUnits(Unit.UnitType.WARRIOR, 21);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 10 warriors second with 11 warriors WHEN first wins")
    void test10() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 10);
        army2.addUnits(Unit.UnitType.WARRIOR, 11);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 11 warriors second with 7 warriors WHEN first wins")
    void test11() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 11);
        army2.addUnits(Unit.UnitType.WARRIOR, 7);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors second with 4 knights WHEN first loses")
    void test12() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 5);
        army2.addUnits(Unit.UnitType.KNIGHT, 7);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 11 warriors second with 7 warriors WHEN first wins")
    void test13() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 5);
        army1.addUnits(Unit.UnitType.WARRIOR, 6);
        army2.addUnits(Unit.UnitType.WARRIOR, 7);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors and a knight and second with 6 warriors and a knight WHEN first loses")
    void test14() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 5);
        army1.addUnits(Unit.UnitType.KNIGHT, 1);
        army2.addUnits(Unit.UnitType.WARRIOR, 6);
        army2.addUnits(Unit.UnitType.KNIGHT, 1);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 1 warrior and 4 knights and second with 6 warriors WHEN first wins")
    void test15() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 1);
        army1.addUnits(Unit.UnitType.KNIGHT, 4);
        army2.addUnits(Unit.UnitType.WARRIOR, 6);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("Given two armies - one with 1 warrior and 4 knights and second with 6 warriors WHEN first wins" +
            " but with fluent interface")
    void test16() {
        Army army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.KNIGHT, 4);
        Army army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 6);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }

}
