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
        myArmy.addUnits("Knight", 3);

        var enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 3);

        var army3 = new Army();
        army3.addUnits("Warrior", 20);
        army3.addUnits("Knight", 5);

        var army4 = new Army();
        army4.addUnits("Warrior", 30);
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
        army.addUnits("Warrior", 1);
        Assertions.assertAll(
                () -> Assertions.assertSame("Model.Warrior", army.getTroops().get(0).getClass().getName()),
                () -> Assertions.assertNotSame("Model.Knight", army.getTroops().get(0).getClass().getName())
        );
    }

    @Test
    @DisplayName("Creating knight creates a knight")
    void test03() {
        Army army = new Army();
        army.addUnits("Knight", 1);
        Assertions.assertAll(
                () -> Assertions.assertNotSame("Model.Warrior", army.getTroops().get(0).getClass().getName()),
                () -> Assertions.assertSame("Model.Knight", army.getTroops().get(0).getClass().getName())
        );

    }

    @Test
    @DisplayName("Given two armies - one having a Model.Warrior second having a Model.Knight then army with Model.Knight should win")
    void test04() {
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 1);
        Army enemyArmy = new Army();
        enemyArmy.addUnits("Knight", 1);

        Assertions.assertFalse(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with two warriors and second with one warriors then first army should win")
    void test05() {
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 2);
        Army enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 1);

        Assertions.assertTrue(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with one warrior and second with two warriors then second army should win")
    void test06() {
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 1);
        Army enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 2);

        Assertions.assertFalse(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with 2 warriors second with 3 warriors then first loses")
    void test07() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 2);
        army2.addUnits("Warrior", 3);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors second with 7 warriors then first loses")
    void test08() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 5);
        army2.addUnits("Warrior", 7);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 20 warriors second with 21 warriors then first wins")
    void test09() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 20);
        army2.addUnits("Warrior", 21);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 10 warriors second with 11 warriors then first wins")
    void test10() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 10);
        army2.addUnits("Warrior", 11);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 11 warriors second with 7 warriors then first wins")
    void test11() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 11);
        army2.addUnits("Warrior", 7);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors second with 4 knights then first loses")
    void test12() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 5);
        army2.addUnits("Knight", 7);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 11 warriors second with 7 warriors then first wins")
    void test13() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 5);
        army1.addUnits("Warrior", 6);
        army2.addUnits("Warrior", 7);
        Assertions.assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors and a knight and second with 6 warriors and a knight then first loses")
    void test14() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 5);
        army1.addUnits("Knight", 1);
        army2.addUnits("Warrior", 6);
        army2.addUnits("Knight", 1);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 1 warrior and 4 knights and second with 6 warriors then first wins")
    void test15() {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 1);
        army1.addUnits("Knight", 4);
        army2.addUnits("Warrior", 6);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("Given two armies - one with 1 warrior and 4 knights and second with 6 warriors then first wins" +
            " but with fluent interface")
    void test16() {
        Army army1 = new Army()
                .addUnits("Warrior", 1)
                .addUnits("Knight", 4);
        Army army2 = new Army()
                .addUnits("Warrior", 6);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }

}
